package Spring.backend.Authentication.signin;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.ResponseUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping(path="api/v1/signin")
@AllArgsConstructor
public class SignInController {
    private SignInService signInService;
    @PostMapping
    public ResponseUser signin(@RequestBody AppUser appUser){
        if(appUser.getPassword()=="" || appUser.getEmail()==""){
            throw new IllegalStateException("Please Fill All Inputs!");
        }
        return signInService.signin(appUser);
    }
}
