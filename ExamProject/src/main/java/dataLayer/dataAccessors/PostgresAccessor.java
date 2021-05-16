package dataLayer.dataAccessors;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresAccessor {
    Connection connection;


    public PostgresAccessor(){
        connection = connectToDB("jdbc:postgresql://localhost:5433/soft2021");

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

    public static void main(String[] args) {
        PostgresAccessor pgr = new PostgresAccessor();

    }
}
