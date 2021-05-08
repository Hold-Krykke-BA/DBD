import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

/**
 * https://neo4j.com/developer/java/
 */
public class DBConnection implements AutoCloseable {
    private final Driver driver;

    public DBConnection(String uri) {
        driver = GraphDatabase.driver(uri);
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void getSingleNode(String nodeName) {
        try (Session session = driver.session()) {
            String getResult = session.readTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    var result = tx.run("match(n:NodeType{name:$nodeName}) return n;", parameters("nodeName", nodeName)).single().get("n");
                    String description = result.get("description").toString();
                    String name = result.get("name").toString();
                    return name + ": " + description;
                }
            });
            System.out.println("getSingleNode result: " + getResult);
        }
    }

    public void getMvcView() {
        try (Session session = driver.session()) {
            List getResult = session.readTransaction(new TransactionWork<List>() {
                @Override
                public List execute(Transaction tx) {
                    List result = new ArrayList();
                    tx.run("match(m:MvcView ) return m;").list().forEach(e -> result.add(e.get("m").get("name").toString()));
                    return result;
                }
            });
            System.out.println("Nodes in MvcView: " + getResult);
            System.out.println("Size of MvcView: " + getResult.size());
        }
    }

    public void updateSingleNode(String nodeName, String nodeDescription) {
        try (Session session = driver.session()) {
            String getResult = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    var result = tx.run("match(n:NodeType{name:$nodeName}) SET n.description = $nodeDescription return n;",
                            parameters("nodeName", nodeName, "nodeDescription", nodeDescription)).single().get("n");
                    String description = result.get("description").toString();
                    String name = result.get("name").toString();
                    return name + ": " + description;
                }
            });
            System.out.println("updateSingleNode result: " + getResult);
        }
    }

    public void getSoftwareDeveloper() {

        try (Session session = driver.session()) {
            final String[] nodeParent = {""};
            List getResult = session.readTransaction(new TransactionWork<List>() {
                @Override
                public List execute(Transaction tx) {
                    List result = new ArrayList();
                    tx.run("match (n:NodeModel)<-[:PART_OF]-(p) return n,p;").list().forEach(e -> {
                        String res = "name: " + e.get("p").get("name").toString() + "\t";
                        res += "description: " + e.get("p").get("description").toString();
                        result.add(res);
                        nodeParent[0] = e.get("n").get("name").toString(); //lambda workaround
                    });
                    return result;
                }
            });
            System.out.println("Parent Node: " + nodeParent[0]);
            System.out.println("Parent relations: " + getResult);
            System.out.println("Size of Parent relations: " + getResult.size());
        }
    }

    public void getNodeDomainRelations(String nodeDomainName) {
        try (Session session = driver.session()) {
            List getResult = session.readTransaction(new TransactionWork<List>() {
                @Override
                public List execute(Transaction tx) {
                    List result = new ArrayList();
                    tx.run("MATCH (n)-[relatedTo]-(d:NodeDomain {name: $nodeDomainName}) \n" +
                            "RETURN n, Type(relatedTo) as relation", parameters("nodeDomainName", nodeDomainName)).list().forEach(e -> {
                        String res = "name: " + e.get("n").get("name").toString() + "\t";
                        res += "description: " + e.get("relation").toString();
                        result.add(res);
                    });
                    return result;
                }
            });
            System.out.println("Relations to " + nodeDomainName + ": " + getResult);
            System.out.println("Size of relations: " + getResult.size());
        }
    }

    /**
     * Pass the relation to filter by or an empty String to filter by no relations
     *
     * @param relationsFilter
     */
    public void getNodesWithoutRelations(String relationsFilter) {
        if (!relationsFilter.isEmpty()) relationsFilter = ":" + relationsFilter;
        try (Session session = driver.session()) {
            String finalRelationsFilter = relationsFilter; //workaround
            List getResult = session.readTransaction(new TransactionWork<List>() {
                @Override
                public List execute(Transaction tx) {
                    List result = new ArrayList();
                    String query = String.format("MATCH (n) WHERE NOT (n)-[%s]-() RETURN n;", finalRelationsFilter); //parameters don't work!
                    tx.run(query).list().forEach(e -> {
                        String res = "name: " + e.get("n").get("name").toString();
                        result.add(res);
                    });
                    return result;
                }
            });
            System.out.println("Nodes filtered by getNodesWithoutRelations: " + getResult);
            System.out.println("Size: " + getResult.size());
        }
    }

    public static void main(String... args) throws Exception {
        try (DBConnection connection = new DBConnection("bolt://localhost:7687")) {
            System.out.println("1. Which, and how many, nodes are there in to \"MvcView\"?");
            connection.getMvcView();
            System.out.println("**********************");
            System.out.println("2. What, and how many, relations are there to \"Software Developer\"?");
            connection.getSoftwareDeveloper();
            System.out.println("**********************");
            System.out.println("3. Getting node by name \"Term\"");
            connection.getSingleNode("Term");
            System.out.println("**********************");
            System.out.println("4. Updating node by name \"Term\"");
            connection.updateSingleNode("Term", String.valueOf(Math.random()));
            System.out.println("**********************");
            System.out.println("5. Getting node relations to nodeDomain \"Technical\"");
            connection.getNodeDomainRelations("Technical");
            System.out.println("**********************");
            System.out.println("6. Find nodes with no relations");
            connection.getNodesWithoutRelations(""); //no filter
            System.out.println("**********************");
            System.out.println("7. Find nodes with no \"PART_OF\" relations");
            connection.getNodesWithoutRelations("PART_OF"); //with filter
        }
    }
}

