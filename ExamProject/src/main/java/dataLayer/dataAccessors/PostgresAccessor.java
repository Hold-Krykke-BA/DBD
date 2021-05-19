package dataLayer.dataAccessors;


import models.dataModels.User;

import java.sql.*;
import java.util.Properties;

public class PostgresAccessor {
    Connection connection;
    String conStr;


    public PostgresAccessor(){
        this.connection = null;
        this.conStr = "jdbc:postgresql://localhost:5433/soft2021";
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

    public Connection getConnection(){
        if(connection == null) connection = connectToDB(conStr);
            return connection;
    }

    public void insertUserId(Connection conn, User user)throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO public.reddit_user (user_id) VALUES (?);");
        stmt.setString(1, user.getUserID());
        stmt.execute();
    }

    public void updateUserId(Connection conn, User user, String newID)throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update public.reddit_user set user_id = ? where user_id = ?;");
        stmt.setString(1, newID);
        stmt.setString(2, user.getUserID());
        stmt.executeUpdate();
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
        User user = new User("tretor", "t@b.com", "0000");
        PostgresAccessor pgr = new PostgresAccessor();
        pgr.test(pgr.getConnection());
        pgr.insertUserId(pgr.getConnection(), user);
        pgr.updateUserId(pgr.getConnection(), user, "0");


    }
}
