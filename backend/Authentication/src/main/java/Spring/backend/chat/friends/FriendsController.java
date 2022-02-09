
package Spring.backend.chat.friends;

import Spring.backend.Authentication.appuser.AppUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/friends/friendship")
public class FriendsController {
    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping("/{receiverId}")
    public List<AppUser> getPendingInvitations(@PathVariable String receiverId){
        return friendsService.getPendingInvitations(receiverId);
    }

    @PostMapping
    public Friends inviteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.sendRequestFriendship(friendsKey);
    }
    @PutMapping
    public Friends acceptFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.acceptRequestFriendship(friendsKey);
    }
    @PutMapping(path = "/delete")
    public String deleteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.deleteRequestFriendship(friendsKey);
    }
}
