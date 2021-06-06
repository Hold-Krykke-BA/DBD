package holdkrykke.rest;

import com.google.gson.*;
import holdkrykke.dataLayer.IDataController;
import holdkrykke.dataLayer.controller.DataControllerImpl;
import holdkrykke.models.dataModels.*;
import holdkrykke.models.viewModels.CommentUpdater;
import holdkrykke.models.viewModels.PostUpdater;
import holdkrykke.util.LocalDateTimeAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
@RestController
public class ExamSpringAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExamSpringAppApplication.class, args);
    }

    private static final Gson GSON = new GsonBuilder().
            registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).
            setPrettyPrinting().
            create();
    IDataController datactr = DataControllerImpl.getInstance();


    @GetMapping("/alluserids")
    public String getAllUserIDs() {
        return GSON.toJson(datactr.getUserIDs());
    }

    @GetMapping("/postwithcomments/{urlID}/{subName}/{postID}")
    @ResponseBody
    public String postWithComments(@PathVariable String urlID, @PathVariable String subName, @PathVariable String postID) {
        return GSON.toJson(datactr.getPostWithComments(urlID, subName, postID));
    }

    @GetMapping("/postwithcommentssorted/{urlID}/{subName}/{postID}")
    @ResponseBody
    public String postWithCommentsSorted(@PathVariable String urlID, @PathVariable String subName, @PathVariable String postID) {
        return GSON.toJson(datactr.getPostWithCommentsSorted(urlID, subName, postID));
    }

    @GetMapping("/userinfo/{userid}")
    @ResponseBody
    public String getUserInfo(@PathVariable String userid) {
        return GSON.toJson(datactr.getUserInfo(userid));
    }

    @GetMapping("/downvotepost/{postid}")
    @ResponseBody
    public void downvotePost(@PathVariable String postid) {
        datactr.downvotePost(postid);
    }

    @GetMapping("/upvotepost/{postid}")
    @ResponseBody
    public void upvotePost(@PathVariable String postid) {
        datactr.upvotePost(postid);
    }

    @GetMapping("/upvotecomment/{commentid}")
    @ResponseBody
    public void upvoteComment(@PathVariable String commentid) {
        datactr.upvoteComment(commentid);
    }

    @GetMapping("/downvotecomment/{commentid}")
    @ResponseBody
    public void downvoteComment(@PathVariable String commentid) {
        datactr.downvoteComment(commentid);
    }

    @GetMapping("/subscribe/{subredditid}/{subredditname}/{userid}")
    @ResponseBody
    public void followSubreddit(@PathVariable String subredditid, @PathVariable String subredditname, @PathVariable String userid) {
        datactr.followSubreddit(new SubReddit(subredditid, subredditname), userid);
    }

    @GetMapping("/unsubscribe/{subredditid}/{userid}")
    @ResponseBody
    public void unFollowSubreddit(@PathVariable String subredditid, @PathVariable String userid) {
        datactr.unfollowSubreddit(subredditid, userid);
    }

    @PostMapping(value = "/post", consumes = "application/json")
    @ResponseBody
    public void createPost(@RequestBody Post post) {
        datactr.createPost(post);
    }

    @PutMapping(value = "/updatepost", consumes = "application/json")
    @ResponseBody
    public void updatePost(@RequestBody PostUpdater postupdate) {
        datactr.updatePost(postupdate);
    }

    @DeleteMapping(value = "/deletepost/{postID}")
    public void deletePost(@PathVariable String postID) {
        datactr.deletePost(postID);
    }

    @GetMapping("/fpitems/{userid}/{subid}")
    @ResponseBody
    public String FPItems(@PathVariable String userid, @PathVariable String subid) {
        return GSON.toJson(datactr.getFrontPageItems(userid, subid));
    }

    @PostMapping(value = "/subreddit", consumes = "application/json")
    @ResponseBody
    public void createSubreddit(@RequestBody SubReddit subreddit) {
        datactr.createSubreddit(subreddit);
    }

    @GetMapping("/subscribedto/{userid}")
    @ResponseBody
    public String SubscribedTo(@PathVariable String userid) {
        return GSON.toJson(datactr.getSubRedditsByUser(userid));
    }

    @PostMapping(value = "/comment", consumes = "application/json")
    @ResponseBody
    public void createComment(@RequestBody Comment comment) {
        datactr.createComment(comment);
    }

    @PutMapping(value = "/updatecomment", consumes = "application/json")
    @ResponseBody
    public void updateComment(@RequestBody CommentUpdater commentupdater) {
        datactr.updateComment(commentupdater);
    }

    @DeleteMapping(value = "/deletecomment/{commentID}")
    public void deleteComment(@PathVariable String commentID) {
        datactr.deleteComment(commentID);
    }

    @DeleteMapping(value = "/deletemessage/{messageID}", produces = "application/json")
    @ResponseBody
    public String deleteMessage(@PathVariable String messageID) {
        return GSON.toJson(datactr.deleteMessage(messageID));
    }

    @GetMapping(value = "/chats/{userName}", produces = "application/json")
    @ResponseBody
    public String getUserChats(@PathVariable String userName) {
        return GSON.toJson(datactr.getUserChats(userName));
    }

    @GetMapping(value = "/messages/{chatID}", produces = "application/json")
    @ResponseBody
    public String getChatMessages(@PathVariable String chatID) {
        return GSON.toJson(datactr.getChatMessages(chatID));
    }

    /**
     * Timestamp is set by DB
     * <p>
     * {
     * "senderUserID": "30f18a0b-e052-44eb-8c24-23a032b97af3",
     * "content": "message from the API",
     * "userIDReceiver": "62eabb1d-10e1-4e43-ae48-6889835a678d"
     * }
     *
     * @param message message to include (use MessageDTO to include the userID of the receiver)
     * @return
     */
    @PostMapping(value = "/message", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createMessage(@RequestBody MessageDTO message) {
        return GSON.toJson(datactr.createMessage(message, message.getUserIDReceiver()));
    }

    @GetMapping(value = "/authenticate/{userID}", produces = "application/json")
    @ResponseBody
    public String authenticateUser(@PathVariable String userID) {
        return GSON.toJson(datactr.authenticateUser(userID));
    }


    /**
     * {
     * "userName": "API test",
     * "userMail": "apitest@api.com",
     * "password": "MyPasswordUnhashed"
     * }
     *
     * @param user user details. ID will be set by database
     * @return
     */
    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String createUser(@RequestBody User user) {
        return GSON.toJson(datactr.createUser(user));
    }

    /**
     * userName and ID cannot be changed. It matches on username, so make sure to pass it:
     * {
     * "userName": "rvn",
     * "userMail": "UPDATEDEMAIL",
     * "password": "UPDATEDPASSWORD"
     * }
     *
     * @param user
     * @return
     */
    @PutMapping(value = "/updateuser", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        return GSON.toJson(datactr.updateUser(user));
    }

    @DeleteMapping(value = "/deleteuser/{userID}", produces = "application/json")
    @ResponseBody
    public String deleteUser(@PathVariable String userID) {
        return GSON.toJson(datactr.deleteUserByUserID(userID));
    }

    @GetMapping(value = "/follow/{userID}/{followerID}", produces = "application/json")
    @ResponseBody
    public String getOrCreateUserFollowing(@PathVariable String userID, @PathVariable String followerID) {
        return GSON.toJson(datactr.getOrCreateUserFollowing(userID, followerID));
    }

}
