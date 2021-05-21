package dataLayer.dataAccessors;

import models.dataModels.User;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.graphdb.RelationshipType;

import static org.neo4j.driver.Values.parameters;

/**
 * Driver documentation:
 * https://neo4j.com/docs/api/java-driver/current/
 */
public class Neo4jAccessor implements AutoCloseable {

    private Driver driver;
    private String _URI = "bolt://localhost:7687";

    public Neo4jAccessor() {
        System.out.println("Connecting to Neo4j at:" + _URI);
        connectToDB(_URI);
        System.out.println("Neo4j successfully connected");
    }

    private void connectToDB(String URI) {
        driver = GraphDatabase.driver(URI);
    }
    //TODO:
    //connect to DB
    //create test data and put it in a file
    //createUser
    //getUser
    //editUser
    //deleteUser

    private void createUser(User user) {

        try (Session session = driver.session())
        {
            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            session.writeTransaction(tx -> tx.run("MERGE (a:Person {name: $x})", parameters("x", name)));
        }
    }

    //createMessage
    //getMessage
    //editMessage
    //deleteMessage

    //createSession
    //getSession
    //editSession
    //deleteSession


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
    }
}
