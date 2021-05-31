package holdkrykke.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import holdkrykke.dataLayer.IDataController;
import holdkrykke.dataLayer.controller.DataControllerImpl;
import holdkrykke.models.dataModels.Post;
import holdkrykke.models.dataModels.SubReddit;
import holdkrykke.models.viewModels.PostUpdater;
import holdkrykke.models.viewModels.PostWithCommentsContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class ExamSpringAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExamSpringAppApplication.class, args);
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
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

    @PostMapping(value ="/post", consumes = "application/json")
    @ResponseBody
    public void createPost(@RequestBody Post post) {
        datactr.createPost(post);
    }

    @PutMapping (value ="/updatepost", consumes = "application/json")
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
    public String FPItems(@PathVariable String userid,@PathVariable String subid) {
        return GSON.toJson(datactr.getFrontPageItems(userid,subid));
    }
}
