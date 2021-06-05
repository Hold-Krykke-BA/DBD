package holdkrykke.dataLayer.dataAccessors;

import holdkrykke.models.dataModels.Chat;
import holdkrykke.models.dataModels.Message;
import holdkrykke.models.dataModels.User;

import holdkrykke.models.dataModels.UserSession;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import holdkrykke.util.CreateUUID;
import holdkrykke.util.DateConverter;
import holdkrykke.util.PasswordUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

/**
 * Driver documentation:
 * https://neo4j.com/docs/api/java-driver/current/
 */
public class Neo4jAccessor implements AutoCloseable {

    private Driver driver;
    private final String _URI = "neo4j://localhost:7687";
    private final String _user = "neo4j";
    private final String _password = "softdbd";
    private final int _expireIn = 24; //used for ttl on session
    private final String _timeUnit = "h"; //used for ttl on session: ms, s, m, h, d

    public Neo4jAccessor() {
        System.out.println("Connecting to Neo4j at: " + _URI);
        connectToDB(_URI, _user, _password);
        driver.verifyConnectivity(); //if no exception, all is good
        System.out.println("Neo4j successfully connected");
    }

    private void connectToDB(String URI, String user, String password) {
        driver = GraphDatabase.driver(URI, AuthTokens.basic(user, password));
    }

    /**
     * @param userName
     * @return User or null if no record
     */
    public User getUserByUserName(String userName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String query = "MATCH (u:User {userName:$userName}) RETURN u;";
                try {
                    var result1 = tx.run(query, parameters("userName", userName)).single().get("u");
                    String userEmail = result1.get("userEmail").toString();
                    String userName1 = result1.get("userName").toString();
                    //String password = result.get("userPassHash").toString();
                    String userID = result1.get("userID").toString();
                    return new User(userName1, userEmail, userID);
                } catch (NoSuchRecordException e) {
                    System.out.println("getUser error: " + e);
                    return null;
                }

            });
        }
    }

    /**
     * @param userID
     * @return User or null if no record
     */
    public User getUserByUserID(String userID) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String query = "MATCH (u:User {userID:$userID}) RETURN u;";
                try {
                    var result1 = tx.run(query, parameters("userID", userID)).single().get("u");
                    String userEmail = result1.get("userID").toString();
                    String userName = result1.get("userID").toString();
                    //String password = result.get("userPassHash").toString();
                    String userID1 = result1.get("userID").toString();
                    return new User(userName, userEmail, userID1);
                } catch (NoSuchRecordException e) {
                    System.out.println("getUser error: " + e);
                    return null;
                }

            });
        }
    }

    /**
     * Updates user based on their userName with passed values
     *
     * @param user User to update. Pass null for non-changed values
     */
    public User updateUser(User user) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = String.format("MATCH (u:User {userName: \"%s\"}) ", user.getUserName());
                if (user.getUserMail() != null && !user.getUserMail().isEmpty())
                    query = query.concat(String.format("SET u.userEmail = \"%s\" ", user.getUserMail()));
                if (user.getPassword() != null && !user.getPassword().isEmpty())
                    query = query.concat(String.format("SET u.userPassHash = \"%s\" ", PasswordUtil.hashpw(user.getPassword())));
                //change username? Must be passed separately
                query = query.concat("RETURN u;");
                //System.out.println(query);
                try {
                    var result1 = tx.run(query).single().get("u");
                    String userEmail = result1.get("userEmail").toString();
                    String userName = result1.get("userName").toString();
                    //String password = result.get("userPassHash").toString();
                    String userID = result1.get("userID").toString();
                    return new User(userName, userEmail, userID);
                } catch (NoSuchRecordException e) {
                    System.out.println("updateUser error: " + e);
                    return null;
                }
            });
        }
    }


    /**
     * Deletes a user but not their relationships
     *
     * @param userName users username
     * @return true if deleted, otherwise False.
     */
    public Boolean deleteUserByUserName(String userName) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = "MATCH (n:User {userName: $userName}) DELETE n RETURN COUNT(n)";
                try {
                    var result1 = tx.run(query, parameters("userName", userName)).single().get("COUNT(n)").asInt();
                    return result1 == 1; //delete count = 1 -> successfully deleted. Otherwise not.
                } catch (NoSuchRecordException e) {
                    System.out.println("deleteUserByEmail error: " + e);
                    return false; //could be null. if changed, update javadoc
                }
            });
        }
    }

    /**
     * Deletes a user but not their relationships
     *
     * @param userID
     * @return
     */
    public Boolean deleteUserByUserID(String userID) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = "MATCH (n:User {userID: $userID}) DETACH DELETE n RETURN COUNT(n)";
                try {
                    var result1 = tx.run(query, parameters("userID", userID)).single().get("COUNT(n)").asInt();
                    return result1 == 1; //delete count = 1 -> successfully deleted. Otherwise not.
                } catch (NoSuchRecordException e) {
                    System.out.println("deleteUserByID error: " + e);
                    return false; //could be null
                }
            });
        }
    }

    /**
     * Creates a user if not already exists
     *
     * @param user
     * @return
     */
    public User createUser(User user) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = "CREATE (u:User {userID: $userID, userName: $userName, userPassHash: $userPassHash, userEmail: $userEmail}) RETURN u;";
                System.out.println(query);
                try {
                    var result1 = tx.run(query, parameters(
                            "userID", CreateUUID.getID(),
                            "userName", user.getUserName(),
                            "userPassHash", PasswordUtil.hashpw(user.getPassword()), //todo correct place to hash?
                            "userEmail", user.getUserMail()
                    )).single().get("u");
                    String userEmail = result1.get("userEmail").toString();
                    String userName = result1.get("userName").toString();
                    //String password = result.get("userPassHash").toString();
                    String userID = result1.get("userID").toString();
                    return new User(userName, userEmail, userID);
                } catch (ClientException e) {//can either hit constraint or not be targeting leader
                    System.out.println("createUser error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Creates a session for a user
     *
     * @param userID ID of user to create session for.
     * @return
     */
    public UserSession createSession(String userID) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = String.format("MATCH (u:User {userID:$userID})\n" +
                        "       WITH u\n" +
                        "       CREATE (ses:Session {sessionID:$sessionID, userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)\n" +
                        "       WITH ses\n" +
                        "       call apoc.ttl.expireIn(ses, %s, '%s') RETURN ses;", _expireIn, _timeUnit);
                System.out.println(query);
                try {
                    var result1 = tx.run(query, parameters(
                            "userID", userID,
                            "sessionID", CreateUUID.getID()
                    )).single().get("ses");
                    String sessionID = result1.get("sessionID").toString();
                    LocalDateTime ttl = DateConverter.EpochToLocalDateTime(result1.get("ttl").asLong());
                    LocalDateTime timestamp = result1.get("timestamp").asLocalDateTime();
                    String userID1 = result1.get("userID").toString();
                    return new UserSession(sessionID, userID1, timestamp, ttl);
                } catch (ClientException e) {//can either hit constraint or not be targeting leader
                    System.out.println("createSession error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Returns a list of sessions belonging to given userID
     *
     * @param userID userID in question
     * @return
     */
    public List<UserSession> getUserSessions(String userID) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String query = "MATCH (ses:Session)-[:BELONGS_TO]->(u:User)\n" +
                        "WHERE u.userID = $userID\n" +
                        "RETURN ses;";
                try {
                    var queryResult = tx.run(query, parameters("userID", userID)).list();
                    List<UserSession> result1 = new ArrayList<>();
                    for (var res : queryResult) {
                        var node = res.get("ses");
                        String sessionID = node.get("sessionID").toString();
                        String userID1 = node.get("userID").toString();
                        LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                        LocalDateTime ttl = DateConverter.EpochToLocalDateTime(node.get("ttl").asLong());
                        result1.add(new UserSession(sessionID, userID1, timestamp, ttl));
                    }
                    return result1;
                } catch (NoSuchRecordException e) {
                    System.out.println("getUser error: " + e);
                    return null;
                }

            });
        }
    }

    /**
     * Updates a session's TTL based on the ID. Values follow globals expireIn, timeUnit.
     *
     * @param sessionID ID of the session to update
     * @return new session if success; otherwise null
     */
    public UserSession updateSession(String sessionID) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query = String.format(
                        "MATCH (ses:Session {sessionID: $sessionID})\n" +
                                "WITH ses\n" +
                                "CALL apoc.ttl.expireIn(ses, %s, '%s')\n" +
                                "RETURN ses;", _expireIn, _timeUnit);
                try {
                    var res = tx.run(query, parameters("sessionID", sessionID)).single().get("ses");
                    String sessionID1 = res.get("sessionID").toString();
                    String userID = res.get("userID").toString();
                    LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                    LocalDateTime ttl = DateConverter.EpochToLocalDateTime(res.get("ttl").asLong());
                    return new UserSession(sessionID1, userID, timestamp, ttl);
                } catch (NoSuchRecordException e) {
                    System.out.println("updateUser error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Creates a message with passed parameters.
     * <p>
     * Needed info is: chatID, senderUserID and content
     * <p>
     * Timestamp will not go through, it is generated by the database.
     * <p>
     * ID will be created by method
     *
     * @param message Message to create
     * @return created message
     */
    public Message createMessage(String chatID, Message message) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query =
                        "MATCH(ch:Chat {chatID: $chatID})\n" +
                                "WITH ch\n" +
                                "CREATE\n" +
                                "  (msg:Message {messageID: $messageID," +
                                " senderUserID: $senderUserID, " +
                                "content: $content, " +
                                "timestamp: localdatetime()})-[:CHAT_PARENT]->(ch) RETURN msg;";
                try {
                    var res = tx.run(query, parameters(
                            "chatID", chatID,
                            "messageID", CreateUUID.getID(),
                            "senderUserID", message.getSenderUserID(),
                            "content", message.getContent()))
                            .single().get("msg");
                    String resMessageID = res.get("messageID").toString();
                    String senderUserID = res.get("senderUserID").toString();
                    String content = res.get("content").toString();
                    LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                    return new Message(resMessageID, senderUserID, content, timestamp);
                } catch (NoSuchRecordException e) {
                    System.out.println("createMessage error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Returns a list of messages belonging to given chat
     *
     * @param chatID id of chat in question
     * @return
     */
    public List<Message> getChatMessages(String chatID) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String query = "MATCH (ch:Chat {chatID: $chatID})<-[:CHAT_PARENT]-(msg) RETURN msg ORDER BY msg.timestamp";
                try {
                    var queryResult = tx.run(query, parameters("chatID", chatID)).list();
                    List<Message> result1 = new ArrayList<>();
                    for (var res : queryResult) {
                        var node = res.get("msg");
                        String resMessageID = node.get("messageID").toString();
                        String senderUserID = node.get("senderUserID").toString();
                        String content = node.get("content").toString();
                        LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                        result1.add(new Message(resMessageID, senderUserID, content, timestamp));
                    }
                    return result1;
                } catch (NoSuchRecordException e) {
                    System.out.println("getChatMessages error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * "Deletes" a message by settings it's content to null
     *
     * @param messageID ID of message to "delete"
     * @return message that was deleted
     */
    public Message deleteMessage(String messageID) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query =
                        "MATCH (msg:Message {messageID: $messageID})\n" +
                                "SET msg.content = \"Message was deleted\"\n" +
                                "RETURN msg;";
                try {
                    var res = tx.run(query, parameters("messageID", messageID)).single().get("msg");
                    String resMessageID = res.get("messageID").toString();
                    String senderUserID = res.get("senderUserID").toString();
                    String content = res.get("content").toString();
                    LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                    return new Message(resMessageID, senderUserID, content, timestamp);
                } catch (NoSuchRecordException e) {
                    System.out.println("updateUser error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Attempts to retrieve a chat between two users. If not found, one will be created.
     *
     * @param userNameOne first users name
     * @param userNameTwo second users name
     * @return retrieved or created Chat
     */
    public Chat GetOrCreateChat(String userNameOne, String userNameTwo) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query =
                        "MATCH\n" +
                                "(u1:User),\n" +
                                "(u2:User)\n" +
                                "WHERE u1.userName = $userNameOne AND u2.userName = $userNameTwo \n" +
                                "MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) \n" +
                                "ON CREATE\n" +
                                "SET ch.timestamp = localdatetime(),\n" +
                                "ch.chatID = $chatID \n" +
                                "RETURN ch;";
                try {
                    var res = tx.run(query, parameters(
                            "userNameOne", userNameOne,
                            "userNameTwo", userNameTwo,
                            "chatID", CreateUUID.getID())).single().get("ch");
                    String chatID = res.get("chatID").toString();
                    LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                    return new Chat(chatID, timestamp);
                } catch (NoSuchRecordException e) {
                    System.out.println("updateUser error: " + e);
                    return null;
                }
            });
        }
    }

    /**
     * Returns a list of chats belonging to given user
     *
     * @param userName users userName
     * @return list of chats belonging to $userName
     */
    public List<Chat> getUserChats(String userName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String query = "MATCH (u:User {userName: $userName})-[:PARTICIPATES_IN]-(ch) RETURN ch ORDER BY ch.timestamp";
                try {
                    var queryResult = tx.run(query, parameters("userName", userName)).list();
                    List<Chat> result1 = new ArrayList<>();
                    for (var res : queryResult) {
                        var node = res.get("ch");
                        String chatID = node.get("chatID").toString();
                        LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                        result1.add(new Chat(chatID, timestamp));
                    }
                    return result1;
                } catch (NoSuchRecordException e) {
                    System.out.println("getUserChats error: " + e);
                    return null;
                }

            });
        }
    }

    /**
     * Utilizes MERGE to get or create a :FOLLOWS relationship between two users.
     * <p>
     * The relationship will be "directional" so it is important to pass properties properly.
     *
     * @param userID     The ID of the user who initiates the follow
     * @param followerID The ID of the user to be followed
     * @return returns true if relationship exists or was created. Returns null if userID does not exist. Returns false if exception.
     */
    public Boolean getOrCreateUserFollowing(String userID, String followerID) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String query =
                        "MATCH\n" +
                                "  (u:User {userID: $userID }),\n" +
                                "  (followee:User {userID: $followerID })\n" +
                                "MERGE (u)-[r:FOLLOWS]->(followee)\n" +
                                "RETURN EXISTS((u)-[:FOLLOWS]-(followee)) AS follows;";
                try {
                    return tx.run(query, parameters("userID", userID, "followerID", followerID)).single().get("follows").asBoolean();
                } catch (NoSuchRecordException e) {
                    System.out.println("getOrCreateFollowing error: " + e);
                    return null;
                } catch (Exception e) {
                    System.out.println("getOrCreateFollowing general error: " + e);
                    return false;
                }
            });
        }
    }


    /**
     * Method from AutoCloseable
     */
    @Override
    public void close() {
        driver.close();
    }

    public Driver getDriver() {
        return driver;
    }

    public static void main(String[] args) throws InterruptedException {
        Neo4jAccessor n4jA = new Neo4jAccessor();
        //var user = n4jA.getUserByUserName("rvn");
        //System.out.println(user);
        //var user = n4jA.updateUser(new User("rvn", "yet@hotmail.com", "yeet", null));
        //var user = n4jA.createUser(new User("rvn19", "yeeeeeeeeet@hotmail.com", "yeet", null));
        //System.out.println(user);
        //System.out.println("Created user" + user.getUserName());
        //var x = n4jA.deleteUserByUserName("yeeeeeeeeet@hotmail.com");
        //System.out.println(x);
        //System.out.println("Deleted user");

        //var ses = n4jA.createSession("1");
        //System.out.println(ses);
        // res = n4jA.getUserSessions("cvs");
        //System.out.println(res);

        //var res = n4jA.updateSession("235252");
        //System.out.println(res);

        //var res = n4jA.createMessage("1", new Message("1","test message from java"));
        //System.out.println(res);

        //var res = n4jA.getChatMessages("1");
        //System.out.println(res);

        //var res = n4jA.GetOrCreateChat("cvs", "rvn");
        //System.out.println(res);

        //var res = n4jA.getUserChats("rvn");
        //System.out.println(res);

        //var res = n4jA.getOrCreateUserFollowing("aben", "gikentur");
        //System.out.println(res);

        var res = n4jA.deleteMessage("2");
        System.out.println(res);

        ArrayList<Chat> res2 =  (ArrayList) n4jA.getUserChats("rvn");
        System.out.println(res2);

        System.out.println("chat here " + res2.get(0));
        System.out.println(res2.get(0).getChatID());
        System.out.println(res2.get(0).getChatID().getClass());
        String yeet = res2.get(0).getChatID();

        var res3 = n4jA.getChatMessages("1");
        for (int i = 0; i < res3.size(); i++) {
            System.out.println(res3.get(i));
            System.out.println(res3.get(i).getContent().length());
        }
    }
}
