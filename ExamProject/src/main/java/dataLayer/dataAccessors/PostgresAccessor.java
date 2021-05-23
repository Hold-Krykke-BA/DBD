package dataLayer.dataAccessors;


import dataLayer.controller.DataControllerImpl;
import models.dataModels.*;
import util.DateConverter;
import util.StringManipulation;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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

    public void insertUserId(User user) {
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.insert_userID(?)");
            stmt.setString(1, user.getUserID());
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSubreddit(SubReddit subreddit) {
        Connection conn = getConnection();
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

    public void insertUser_Subreddit(SubReddit subreddit, User user) {
        Connection conn = getConnection();
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

    public List<String> getAllUserID() {
        Connection conn = getConnection();
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

    public void insertPost(Post post, User user, SubReddit subreddit) {
        Connection conn = getConnection();
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

    public Post getPost(String subredditName, String postURLidentifier){
    Post post = null;
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from public.get_Post(?, ?);");
            stmt.setString(1, subredditName);
            stmt.setString(2, postURLidentifier);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                post = new Post(rs.getString("post_id"), rs.getString("post_url_identifier"),
                        DateConverter.getDateFromString(rs.getString("post_timestamp")),
                        rs.getString("post_title"), rs.getString("subreddit_id"),
                        rs.getString("user_id"),rs.getInt("post_karma"), rs.getString("post_content"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return post;
    }

    public List<Comment> getComments(String postID){
        List<Comment> comments = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from public.get_Comments(?);");
            stmt.setString(1, postID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                comments.add(new Comment(rs.getString("comment_id"), DateConverter.getDateFromString(rs.getString("comment_timestamp")),
                        rs.getInt("comment_karma"), rs.getString("comment_content"), rs.getString("parent_id"),
                        rs.getString("post_id"), rs.getString("user_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

    public void insertComment(Post post, User user, Comment comment) {
        Connection conn = getConnection();
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

    public List<Map<String, Object>> getFrontPageItemsBySubRedditID(String subredditID){
        Connection conn = getConnection();
        List<Map<String, Object>> fpitems = new ArrayList<>();
        PreparedStatement stmt;
        try { //'609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'
            stmt = conn.prepareStatement("select * from public.get_FPitem(?);");
            stmt.setString(1, subredditID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("post_title", rs.getString("post_title"));
                map.put("post_id", rs.getString("post_id"));
                map.put("post_url_identifier", rs.getString("post_url_identifier"));
                map.put("post_timestamp", DateConverter.getDateFromString(rs.getString("post_timestamp")));
                map.put("post_user_id", rs.getString("user_id"));
                map.put("subreddit_name", rs.getString("subreddit_name"));
                map.put("comments", rs.getInt("comments"));
                map.put("post_karma", rs.getInt("post_karma"));
                fpitems.add(map);
            }
            for(Map map : fpitems){
                System.out.println(map.keySet() + "\n" + map.values());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fpitems;
    }

    public void updatePost(String postId, String updatedContent) {
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.update_postt(?, ?)");
            stmt.setString(1, postId);
            stmt.setString(2, updatedContent);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePost(String postID) {
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.delete_post(?)");
            stmt.setString(1, postID);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateComment(String commentID, String updatedContent) {
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.update_comment(?, ?)");
            stmt.setString(1, commentID);
            stmt.setString(2, updatedContent);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteComment(String commentID) {
        Connection conn = getConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("CALL public.delete_comment(?)");
            stmt.setString(1, commentID);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        System.out.println(pgr.getAllUserID());
        pgr.getFrontPageItemsBySubRedditID("609f1f9f-dba7-44c8-838b-c00bb5d3e7ac");
        System.out.println(pgr.getPost("funny", "1YjAR").toString());
        System.out.println(pgr.getComments("8772e835-c2fa-46de-bd52-816afa8ae9bb").toString());
    }
}
