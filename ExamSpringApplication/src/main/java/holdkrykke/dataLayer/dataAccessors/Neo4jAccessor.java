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
    private String _URI = "neo4j://localhost:7687";
    private String _user = "neo4j";
    private String _password = "softdbd";
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

        //load config file
    }

    //TODO:
    //connect to DB
    //https://neo4j.com/docs/cypher-manual/current/administration/constraints/
    //create test data and put it in a file
    //createUser

    /**
     * @param userName
     * @return User or null if no record
     */
    public User getUserByUserName(String userName) {
        try (Session session = driver.session()) {
            User result = session.readTransaction(new TransactionWork<User>() {
                @Override
                public User execute(Transaction tx) {
                    String query = "MATCH (u:User {userName:$userName}) RETURN u;";
                    try {
                        var result = tx.run(query, parameters("userName", userName)).single().get("u");
                        String userEmail = result.get("userEmail").toString();
                        String userName = result.get("userName").toString();
                        //String password = result.get("userPassHash").toString();
                        String userID = result.get("userID").toString();
                        return new User(userName, userEmail, userID);
                    } catch (NoSuchRecordException e) {
                        System.out.println("getUser error: " + e);
                        return null;
                    }

                }
            });
            return result;
        }
    }

    /**
     * @param userID
     * @return User or null if no record
     */
    public User getUserByUserID(String userID) {
        try (Session session = driver.session()) {
            User result = session.readTransaction(new TransactionWork<User>() {
                @Override
                public User execute(Transaction tx) {
                    String query = "MATCH (u:User {userID:$userID}) RETURN u;";
                    try {
                        var result = tx.run(query, parameters("userID", userID)).single().get("u");
                        String userEmail = result.get("userID").toString();
                        String userName = result.get("userID").toString();
                        //String password = result.get("userPassHash").toString();
                        String userID = result.get("userID").toString();
                        return new User(userName, userEmail, userID);
                    } catch (NoSuchRecordException e) {
                        System.out.println("getUser error: " + e);
                        return null;
                    }

                }
            });
            return result;
        }
    }

    //editUser

    /**
     * Updates user based on their userName with passed values
     *
     * @param user User to update. Pass null for non-changed values
     */
    public User updateUser(User user) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            User result = session.writeTransaction(new TransactionWork<User>() {
                @Override
                public User execute(Transaction tx) {
                    String query = String.format("MATCH (u:User {userName: \"%s\"}) ", user.getUserName());
                    if (user.getUserMail() != null && !user.getUserMail().isEmpty())
                        query = query.concat(String.format("SET u.userEmail = \"%s\" ", user.getUserMail()));
                    if (user.getPassword() != null && !user.getPassword().isEmpty())
                        query = query.concat(String.format("SET u.userPassHash = \"%s\" ", PasswordUtil.hashpw(user.getPassword()))); //todo correct place to hash?
                    //change username? Must be passed seperately
                    query = query.concat("RETURN u;");
                    //System.out.println(query);
                    try {
                        var result = tx.run(query).single().get("u");
                        String userEmail = result.get("userEmail").toString();
                        String userName = result.get("userName").toString();
                        //String password = result.get("userPassHash").toString();
                        String userID = result.get("userID").toString();
                        return new User(userName, userEmail, userID);
                    } catch (NoSuchRecordException e) {
                        System.out.println("updateUser error: " + e);
                        return null;
                    }
                }
            });
            return result;
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
            Boolean result = session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction tx) {
                    String query = "MATCH (n:User {userName: $userName}) DELETE n RETURN COUNT(n)";
                    try {
                        var result = tx.run(query, parameters("userName", userName)).single().get("COUNT(n)").asInt();
                        return result == 1; //delete count = 1 -> successfully deleted. Otherwise not.
                    } catch (NoSuchRecordException e) {
                        System.out.println("deleteUserByEmail error: " + e);
                        return false; //could be null. if changed, update javadoc
                    }
                }
            });
            return result;
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
            Boolean result = session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction tx) {
                    String query = "MATCH (n:User {userID: $userID}) DELETE n RETURN COUNT(n)";
                    try {
                        var result = tx.run(query, parameters("userID", userID)).single().get("COUNT(n)").asInt();
                        return result == 1; //delete count = 1 -> successfully deleted. Otherwise not.
                    } catch (NoSuchRecordException e) {
                        System.out.println("deleteUserByEmail error: " + e);
                        return false; //could be null
                    }
                }
            });
            return result;
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
            User result = session.writeTransaction(new TransactionWork<User>() {
                @Override
                public User execute(Transaction tx) {
                    String query = "CREATE (u:User {userID: $userID, userName: $userName, userPassHash: $userPassHash, userEmail: $userEmail}) RETURN u;";
                    System.out.println(query);
                    try {
                        var result = tx.run(query, parameters(
                                "userID", CreateUUID.getID(),
                                "userName", user.getUserName(),
                                "userPassHash", PasswordUtil.hashpw(user.getPassword()), //todo correct place to hash?
                                "userEmail", user.getUserMail()
                        )).single().get("u");
                        String userEmail = result.get("userEmail").toString();
                        String userName = result.get("userName").toString();
                        //String password = result.get("userPassHash").toString();
                        String userID = result.get("userID").toString();
                        return new User(userName, userEmail, userID);
                    } catch (ClientException e) {//can either hit constraint or not be targeting leader
                        System.out.println("createUser error: " + e);
                        return null;
                    }
                }

            });
            return result;
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
            UserSession result = session.writeTransaction(new TransactionWork<>() {
                @Override
                public UserSession execute(Transaction tx) {
                    String query = String.format("MATCH (u:User {userID:$userID})\n" +
                            "       WITH u\n" +
                            "       CREATE (ses:Session {sessionID:$sessionID, userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)\n" +
                            "       WITH ses\n" +
                            "       call apoc.ttl.expireIn(ses, %s, '%s') RETURN ses;", _expireIn, _timeUnit);
                    System.out.println(query);
                    try {
                        var result = tx.run(query, parameters(
                                "userID", userID,
                                "sessionID", CreateUUID.getID()
                        )).single().get("ses");
                        String sessionID = result.get("sessionID").toString();
                        LocalDateTime ttl = DateConverter.EpochToLocalDateTime(result.get("ttl").asLong());
                        LocalDateTime timestamp = result.get("timestamp").asLocalDateTime();
                        String userID = result.get("userID").toString();
                        return new UserSession(sessionID, userID, timestamp, ttl);
                    } catch (ClientException e) {//can either hit constraint or not be targeting leader
                        System.out.println("createSession error: " + e);
                        return null;
                    }
                }

            });
            return result;
        }
    }

    /**
     * Returns a list of sessions belonging to given userName
     *
     * @param userName userName in question
     * @return
     */
    public List<UserSession> getUserSessions(String userName) {
        try (Session session = driver.session()) {
            List<UserSession> result = session.readTransaction(new TransactionWork<>() {
                @Override
                public List<UserSession> execute(Transaction tx) {
                    String query = "MATCH (ses:Session)-[:BELONGS_TO]->(u:User)\n" +
                            "WHERE u.userName = $userName\n" +
                            "RETURN ses;";
                    try {
                        var queryResult = tx.run(query, parameters("userName", userName)).list();
                        List<UserSession> result = new ArrayList();
                        for (var res : queryResult) {
                            var node = res.get("ses");
                            String sessionID = node.get("sessionID").toString();
                            String userID = node.get("userID").toString();
                            LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                            LocalDateTime ttl = DateConverter.EpochToLocalDateTime(node.get("ttl").asLong());
                            result.add(new UserSession(sessionID, userID, timestamp, ttl));
                        }
                        return result;
                    } catch (NoSuchRecordException e) {
                        System.out.println("getUser error: " + e);
                        return null;
                    }

                }
            });
            return result;
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
            UserSession result = session.writeTransaction(new TransactionWork<>() {
                @Override
                public UserSession execute(Transaction tx) {
                    String query = String.format(
                            "MATCH (ses:Session {sessionID: $sessionID})\n" +
                                    "WITH ses\n" +
                                    "CALL apoc.ttl.expireIn(ses, %s, '%s')\n" +
                                    "RETURN ses;", _expireIn, _timeUnit);
                    try {
                        var res = tx.run(query, parameters("sessionID", sessionID)).single().get("ses");
                        String sessionID = res.get("sessionID").toString();
                        String userID = res.get("userID").toString();
                        LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                        LocalDateTime ttl = DateConverter.EpochToLocalDateTime(res.get("ttl").asLong());
                        return new UserSession(sessionID, userID, timestamp, ttl);
                    } catch (NoSuchRecordException e) {
                        System.out.println("updateUser error: " + e);
                        return null;
                    }
                }
            });
            return result;
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
            var result = session.writeTransaction(tx -> {
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
            return result;
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
            List<Message> result = session.readTransaction(new TransactionWork<>() {
                @Override
                public List<Message> execute(Transaction tx) {
                    String query = "MATCH (ch:Chat {chatID:$chatID})<-[:CHAT_PARENT]-(msg) RETURN msg ORDER BY msg.timestamp";
                    try {
                        var queryResult = tx.run(query, parameters("chatID", chatID)).list();
                        List<Message> result = new ArrayList();
                        for (var res : queryResult) {
                            var node = res.get("msg");
                            String resMessageID = node.get("messageID").toString();
                            String senderUserID = node.get("senderUserID").toString();
                            String content = node.get("content").toString();
                            LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                            result.add(new Message(resMessageID, senderUserID, content, timestamp));
                        }
                        return result;
                    } catch (NoSuchRecordException e) {
                        System.out.println("getChatMessages error: " + e);
                        return null;
                    }

                }
            });
            return result;
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
            var result = session.writeTransaction(tx -> {
                String query =
                        "MATCH (msg:Message {messageID: $messageID})\n" +
                                "SET msg.content = " + null + "\n" +
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
            return result;
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
            var result = session.writeTransaction(tx -> {
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
            return result;
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
            List<Chat> result = session.readTransaction(new TransactionWork<>() {
                @Override
                public List<Chat> execute(Transaction tx) {
                    String query = "MATCH (u:User {userName: $userName})-[:PARTICIPATES_IN]-(ch) RETURN ch ORDER BY ch.timestamp";
                    try {
                        var queryResult = tx.run(query, parameters("userName", userName)).list();
                        List<Chat> result = new ArrayList();
                        for (var res : queryResult) {
                            var node = res.get("ch");
                            String chatID = node.get("chatID").toString();
                            LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                            result.add(new Chat(chatID, timestamp));
                        }
                        return result;
                    } catch (NoSuchRecordException e) {
                        System.out.println("getUserChats error: " + e);
                        return null;
                    }

                }
            });
            return result;
        }
    }


//    private Message createMessage(Message message) {
//        //check if chat obj
//        //if not, create & use
//        //if, use
//        //append message to chat (see illustration)
//    }

    //getRelationshipX


    /**
     * Method from AutoCloseable
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        driver.close();
    }

    public Driver getDriver() {
        return driver;
    }

    public static void main(String[] args) {
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

        //test if updateUser: email changed into unique email
    }
}
