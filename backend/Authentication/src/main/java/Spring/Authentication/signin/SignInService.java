package Spring.Authentication.signin;

import Spring.Authentication.appuser.AppUser;
import Spring.Authentication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {
    private final AppUserService appUserService;

    public String signin(AppUser appUser){
       return appUserService.SignInUser(appUser);
    }
}
