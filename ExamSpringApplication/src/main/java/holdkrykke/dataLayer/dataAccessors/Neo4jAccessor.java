package holdkrykke.dataLayer.dataAccessors;

import holdkrykke.models.dataModels.Message;
import holdkrykke.models.dataModels.User;

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
    private User getUserByUserName(String userName) {
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
    private User getUserByUserID(String userID) {
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
    private User updateUser(User user) {
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
    private Boolean deleteUserByUserName(String userName) {
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
    private Boolean deleteUserByUserID(String userID) {
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
    private User createUser(User user) {
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
    private holdkrykke.models.dataModels.Session createSession(String userID) {
        try (Session session = driver.session()) {
            holdkrykke.models.dataModels.Session result = session.writeTransaction(new TransactionWork<>() {
                @Override
                public holdkrykke.models.dataModels.Session execute(Transaction tx) {
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
                        return new holdkrykke.models.dataModels.Session(sessionID, userID, timestamp, ttl);
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
    private List<holdkrykke.models.dataModels.Session> getUserSessions(String userName) {
        try (Session session = driver.session()) {
            List<holdkrykke.models.dataModels.Session> result = session.readTransaction(new TransactionWork<>() {
                @Override
                public List<holdkrykke.models.dataModels.Session> execute(Transaction tx) {
                    String query = "MATCH (ses:Session)-[:BELONGS_TO]->(u:User)\n" +
                            "WHERE u.userName = $userName\n" +
                            "RETURN ses;";
                    try {
                        var queryResult = tx.run(query, parameters("userName", userName)).list();
                        List<holdkrykke.models.dataModels.Session> result = new ArrayList();
                        for (var res : queryResult) {
                            var node = res.get("ses");
                            String sessionID = node.get("sessionID").toString();
                            String userID = node.get("userID").toString();
                            LocalDateTime timestamp = node.get("timestamp").asLocalDateTime();
                            LocalDateTime ttl = DateConverter.EpochToLocalDateTime(node.get("ttl").asLong());
                            result.add(new holdkrykke.models.dataModels.Session(sessionID, userID, timestamp, ttl));
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
    private holdkrykke.models.dataModels.Session updateSession(String sessionID) {
        //todo check for correct user before query
        try (Session session = driver.session()) {
            holdkrykke.models.dataModels.Session result = session.writeTransaction(new TransactionWork<>() {
                @Override
                public holdkrykke.models.dataModels.Session execute(Transaction tx) {
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
                        return new holdkrykke.models.dataModels.Session(sessionID, userID, timestamp, ttl);
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
     * "Deletes" a message by settings it's content to a generic 'Message was deleted'
     *
     * @param messageID ID of message to "delete"
     * @return message that was deleted
     */
    private Message deleteMessage(String messageID) {
        try (Session session = driver.session()) {
            var result = session.writeTransaction(tx -> {
                String query = "" +
                        "MATCH (msg:Message {messageID: $messageID})\n" +
                        "SET msg.content = \"Message was deleted\" \n" +
                        "RETURN msg;";
                try {
                    var res = tx.run(query, parameters("messageID", messageID)).single().get("msg");
                    String resMessageID = res.get("messageID").toString();
                    String senderUserID = res.get("senderUserID").toString();
                    String receiverUserID = res.get("receiverUserID").toString();
                    String content = res.get("content").toString();
                    LocalDateTime timestamp = res.get("timestamp").asLocalDateTime();
                    return new Message(resMessageID, senderUserID, receiverUserID, content, timestamp);
                } catch (NoSuchRecordException e) {
                    System.out.println("updateUser error: " + e);
                    return null;
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

        var res = n4jA.updateSession("235252");
        System.out.println(res);

        //test if updateUser: email changed into unique email
    }
}
