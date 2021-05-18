package dataLayer.dataAccessors;


import java.sql.*;
import java.util.Properties;

public class PostgresAccessor {
    Connection connection;


    public PostgresAccessor(){
        connection = connectToDB("jdbc:postgresql://localhost:5433/soft2021");

    }
    public Connection getConnection(){
        return connection;
    }

    private Connection connectToDB(String url){
        Connection connection = null;
        Properties props = new Properties();
        props.setProperty("password", "softdbd");
        props.setProperty("user", "softdbd");
        try {
            connection = DriverManager.getConnection(url,props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void test(Connection conn) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        try (ResultSet tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" })) {
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        PostgresAccessor pgr = new PostgresAccessor();
        pgr.test(pgr.getConnection());

    }
}
