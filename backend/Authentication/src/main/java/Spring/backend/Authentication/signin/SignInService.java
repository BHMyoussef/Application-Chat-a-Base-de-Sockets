package Spring.backend.Authentication.signin;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignInService {
    private final AppUserService appUserService;

    public String signin(AppUser appUser){
       return appUserService.SignInUser(appUser);
    }
    public Optional<AppUser> getUserByEmail(String email) { return appUserService.getByEmail(email); }
}
