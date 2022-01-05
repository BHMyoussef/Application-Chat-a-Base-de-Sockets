/*
package Spring.backend.chat.friends;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/friends/friendship")
public class FriendsController {
    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping
    public Friends inviteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.sendRequestFriendship(friendsKey);
    }
    @PutMapping
    public Friends acceptFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.acceptRequestFriendship(friendsKey);
    }
    @DeleteMapping
    public String deleteFriend(@RequestBody FriendsKey friendsKey){
        return friendsService.deleteRequestFriendship(friendsKey);
    }
}
*/