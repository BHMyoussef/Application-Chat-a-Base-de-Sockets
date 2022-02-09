package Spring.backend.Authentication.appuser;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getConnectedUsers(){
        return appUserService.findUsers();
    }

    @GetMapping(path = "/all")
    public List<AppUser> getAllUsers(){
        return appUserService.findAllUsers();
    }
    @GetMapping(path = "/{userId}")
    public AppUser getSingleUser(@PathVariable String userId){
        return appUserService.findById(userId);
    }
    @GetMapping(path = "/{userId}/friends")
    public List<AppUser> getUserFriends(@PathVariable String userId){
        return appUserService.findUserFriends(userId);
    }

    @PutMapping
    public AppUser updateUser(@RequestBody AppUser appUser){
         return appUserService.updateUser(appUser);
    }
}
