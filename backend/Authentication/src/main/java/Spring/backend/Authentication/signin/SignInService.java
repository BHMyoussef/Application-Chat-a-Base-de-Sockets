package Spring.backend.Authentication.signin;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserService;
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
