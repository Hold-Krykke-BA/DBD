package holdkrykke.models.dataModels;

import java.util.List;

public class UserContainer {
    User user;
    UserKarma karma;
    List<SubReddit> followedSubs;

   public UserContainer(User _user, UserKarma userkarma, List<SubReddit> subreddits){
       this.user = _user;
       this.karma = userkarma;
       this.followedSubs = subreddits;
   }

    public User getUser() {
        return user;
    }

    public UserKarma getKarma() {
        return karma;
    }

    public List<SubReddit> getFollowedSubs() {
        return followedSubs;
    }

    @Override
    public String toString() {
        return "UserContainer{" +
                "user=" + user +
                ", karma=" + karma +
                ", followedSubs=" + followedSubs +
                '}';
    }
}
