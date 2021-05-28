package dataLayer.dataAccessors;

import models.dataModels.User;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.neo4j.graphdb.RelationshipType;

import static org.neo4j.driver.Values.parameters;

/**
 * Driver documentation:
 * https://neo4j.com/docs/api/java-driver/current/
 */
public class Neo4jAccessor implements AutoCloseable {

    private Driver driver;
    private String _URI = "bolt://localhost:7687";
    private String _user = "neo4j";
    private String _password = "softdbd";

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
    private User getUser(String userName) {
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
                        query = query.concat(String.format("SET u.userPassHash = \"%s\" ", user.getPassword())); //todo hash?
                    //change username? Must be passed seperately
                    query = query.concat("RETURN u;");
                    System.out.println(query);
                    try {
                        var result = tx.run(query).single().get("u");
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
            //neo4j identity
            //custom userID
            return result;
        }
    }

    //deleteUser

    private boolean createUser(User user) {

        try (Session session = driver.session()) {

            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            //session.writeTransaction(tx -> tx.run("MERGE (a:Person {name: $x})", parameters("x", name)));
        }
        return false;
    }

    //getRelationshipX

    //createMessage
    //getMessage
    //editMessage
    //deleteMessage
    //getRelationshipX

    //createSession
    //getSession
    //editSession
    //deleteSession
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

    private enum RelTypes implements RelationshipType {
        FOLLOWS
    }

    public static void main(String[] args) {
        Neo4jAccessor n4jA = new Neo4jAccessor();
        var user = n4jA.getUser("rvn");
        System.out.println(user);
        //var user = n4jA.updateUser(new User("rvn", "yet@hotmail.com", "yeet", null));

        //test if changed into unique email
    }
}
