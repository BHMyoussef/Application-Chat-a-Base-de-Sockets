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
    public List<AppUser> getUsers(){
        return appUserService.findUsers();
    }

    @PutMapping
    public AppUser updateUser(@RequestBody AppUser appUser){
         return appUserService.updateUser(appUser);
    }
}
