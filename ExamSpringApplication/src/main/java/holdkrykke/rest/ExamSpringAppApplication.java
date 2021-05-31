package holdkrykke.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import holdkrykke.dataLayer.IDataController;
import holdkrykke.dataLayer.controller.DataControllerImpl;
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

//    @GetMapping("/hello")
//    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
//        return GSON.toJson(datactr.getUserIDs());
//    }

    @GetMapping("/alluserids")
    public String getAllUserIDs() {
        return GSON.toJson(datactr.getUserIDs());
    }
//   VwuU3/legaladvice/5104c346-25c3-421d-befb-3b9df51d7639

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



}
