package Spring.backend.socket.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.Set;
import Spring.backend.Authentication.*;

@RestController
@CrossOrigin
public class userController {
    //just for testing the connection between online users later we need to implement getOnlyFriendsUser
    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll(){
        Set<String> users = new HashSet<>();
        users.add("ossama");
        return users;
    }
}
