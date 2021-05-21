package dataLayer.dataAccessors;


import models.dataModels.*;
import util.DateConverter;
import util.StringManipulation;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
            stmt = conn.prepareStatement("CALL public.insert_userID(?)");
            stmt.setString(1, user.getUserID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSubreddit(Connection conn, SubReddit subreddit) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.insert_subreddit(?, ?)");
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
            stmt = conn.prepareStatement("CALL public.insert_user_subreddit(?, ?)");
            stmt.setString(1, user.getUserID());
            stmt.setString(2, subreddit.getSubRedditID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getAllUserID(Connection conn) {
        PreparedStatement stmt;
        List<String> allIDs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from public.all_userIDs();");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                allIDs.add(rs.getString("user_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allIDs;
    }

    public void insertPost(Connection conn, Post post, User user, SubReddit subreddit) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.insert_post(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, post.getPostID());
            stmt.setString(2, StringManipulation.generateRandomString(5));
            stmt.setString(3, post.getPostTitle());
            stmt.setTimestamp(4, DateConverter.LocalDateTimeToJavaTimestamp(post.getTimestamp()));
            stmt.setString(5, post.getPostContent());
            stmt.setInt(6, post.getPostKarmaCount());
            stmt.setString(7,user.getUserID());
            stmt.setString(8, subreddit.getSubRedditID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertComment(Connection conn, Post post, User user, Comment comment) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.insert_postcomment(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, comment.getCommentID());
            stmt.setString(2, comment.getCommentParentID());
            stmt.setTimestamp(3, DateConverter.LocalDateTimeToJavaTimestamp(comment.getTimestamp()));
            stmt.setString(4, comment.getCommentContent());
            stmt.setInt(5, comment.getCommentKarmaCount());
            stmt.setString(6, post.getPostID());
            stmt.setString(7, user.getUserID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
// StringManipulation.generateRandomString(10)
    public List<FPitem> getFrontPageItems(Connection conn){
        List<FPitem> fpitems = new ArrayList<>();

        FPitem item = new FPitem("","", "", "", LocalDateTime.now(), 0, 0, "");
//"609f1f9f-dba7-44c8-838b-c00bb5d3e7ac"
        PreparedStatement stmt;
        List<String> allIDs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from public.get_FPitem('609f1f9f-dba7-44c8-838b-c00bb5d3e7ac');");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                allIDs.add(rs.getString("user_id"));
                allIDs.add(rs.getString("subreddit_name"));
                allIDs.add(rs.getString("comments"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(Arrays.deepToString(allIDs.toArray()));


        return fpitems;
    }



    public static void main(String[] args) throws SQLException {
//        LocalDateTime date = LocalDateTime.now();
//        SubReddit sub = new SubReddit("3", "wsbtester");
//        User user = new User("dfv", "p@b.com", "7");
//        Post post = new Post("eb69a0b7-74df-4162-9550-4e1961f5f644", StringManipulation.generateRandomString(10), date,"tyl","3","7",0,"hellohellohellohell");
//        Comment comment = new Comment("2", date, 0, "the parent");
//        Comment commentchild = new Comment("21", date, 0, "the child",comment.getCommentID());
        PostgresAccessor pgr = new PostgresAccessor();
//        pgr.insertUserId(pgr.getConnection(), user);
//        pgr.insertSubreddit(pgr.getConnection(), sub);
//        pgr.insertUser_Subreddit(pgr.getConnection(), sub, user);
//        pgr.insertPost(pgr.getConnection(), post, user, sub);
//        pgr.insertComment(pgr.getConnection(), post, user, comment);
//        pgr.insertComment(pgr.getConnection(), post, user, commentchild);
        System.out.println(pgr.getAllUserID(pgr.getConnection()));
        pgr.getFrontPageItems(pgr.getConnection());
    }
}
