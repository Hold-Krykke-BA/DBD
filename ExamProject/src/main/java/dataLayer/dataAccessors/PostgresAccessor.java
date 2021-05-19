package dataLayer.dataAccessors;


import models.dataModels.Post;
import models.dataModels.SubReddit;
import models.dataModels.User;
import util.DateConverter;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection(){
        if(connection == null) connection = connectToDB(conStr);
            return connection;
    }

    public void insertUserId(Connection conn, User user) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO public.reddit_user (user_id) VALUES (?);");
            stmt.setString(1, user.getUserID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSubreddit(Connection conn, SubReddit subreddit) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO public.subreddit (subreddit_id, subreddit_name) VALUES (?, ?);");
            stmt.setString(1, subreddit.getSubRedditID());
            stmt.setString(2, subreddit.getSubRedditName());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertUser_Subreddit(Connection conn, SubReddit subreddit, User user) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO public.user_subreddit (user_id, subreddit_id) VALUES (?, ?);");
            stmt.setString(1, user.getUserID());
            stmt.setString(2, subreddit.getSubRedditID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateUserId(Connection conn, User user, String newID) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("update public.reddit_user set user_id = ? where user_id = ?;");
            stmt.setString(1, newID);
            stmt.setString(2, user.getUserID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getAllUserID(Connection conn) {
        PreparedStatement stmt;
        List<String> allIDs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from public.reddit_user;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                allIDs.add(rs.getString("user_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allIDs;
    }
        // post_id, post_title, post_timestamp, post_content, post_karma, user_id, subreddit_id 2021-05-19 20:34:52
    public void insertPost(Connection conn, Post post, User user, SubReddit subreddit) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO public.post (post_id, post_title, post_timestamp, post_content, post_karma, user_id, subreddit_id) VALUES (?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, post.getPostID());
            stmt.setString(2, post.getPostTitle());
            stmt.setTimestamp(3, DateConverter.LocalDateTimeToJavaTimestamp(post.getTimestamp()));
            stmt.setString(4, post.getPostContent());
            stmt.setInt(5, post.getPostKarmaCount());
            stmt.setString(6,user.getUserID());
            stmt.setString(7, subreddit.getSubRedditID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        SubReddit sub = new SubReddit("3", "wsbtester");
        User user = new User("dfv", "p@b.com", "7");
        Post post = new Post("9", date,"tyl","3","7",0,"hellohellohellohell");
        PostgresAccessor pgr = new PostgresAccessor();
        pgr.insertUserId(pgr.getConnection(), user);
        //pgr.updateUserId(pgr.getConnection(), user, "00000");
       // System.out.println(pgr.getAllUserID(pgr.getConnection()).toString());
        pgr.insertSubreddit(pgr.getConnection(), sub);
        pgr.insertUser_Subreddit(pgr.getConnection(), sub, user);
        pgr.insertPost(pgr.getConnection(), post, user, sub);

    }
}
