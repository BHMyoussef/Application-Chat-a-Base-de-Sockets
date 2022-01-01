package Spring.backend.Authentication.signin;

import Spring.backend.Authentication.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping(path="api/v1/signin")
@AllArgsConstructor
public class SignInController {
    private SignInService signInService;
    @PostMapping
    public String signin(@RequestBody AppUser appUser){
        return signInService.signin(appUser);
    }
    @GetMapping(path = "/users/{UserEmail}")
    public Optional<AppUser> getusers(@PathVariable("UserEmail") String UserEmail) {
        return this.signInService.getUserByEmail(UserEmail);
    }
}
