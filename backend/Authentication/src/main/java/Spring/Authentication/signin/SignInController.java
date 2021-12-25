package Spring.Authentication.signin;

import Spring.Authentication.appuser.AppUser;
import Spring.Authentication.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/signin")
@AllArgsConstructor
public class SignInController {
    private SignInService signInService;
    @PostMapping
    public String signin(@RequestBody AppUser appUser){
        return signInService.signin(appUser);
    }
    @GetMapping
    public String getusers(){
        return "sign in page";
    }
}
