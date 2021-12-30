package Spring.backend.chat.friends;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/friends")
public class FriendsController {
    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping(path = "/invite")
    public Friends inviteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.sendRequestFriendship(friendsKey);
    }
    @PostMapping(path = "/accept")
    public Friends acceptFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.acceptRequestFriendship(friendsKey);
    }
    @PostMapping(path = "/delete")
    public String deleteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.deleteRequestFriendship(friendsKey);
    }
}
