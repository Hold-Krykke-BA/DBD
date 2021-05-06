package contract;

import dto.UserCreation;
import dto.UserOverview;
import dto.UserUpdate;

import java.util.Set;

public interface UserManagement {
    boolean createUser(UserCreation userCreation);
    UserOverview getUserOverview(String username);
    boolean updateUser(UserUpdate userUpdate);
    boolean followUser(String username, String usernameToFollow);
    boolean unfollowUser(String username, String usernameToUnfollow);
    Set<String> getFollowedUsers(String username);
    Set<String> getUsersFollowing(String username);
}

