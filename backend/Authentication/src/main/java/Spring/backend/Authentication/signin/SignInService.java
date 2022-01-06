package Spring.backend.Authentication.signin;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserService;
import Spring.backend.Authentication.appuser.ResponseUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignInService {
    private final AppUserService appUserService;

    public ResponseUser signin(AppUser appUser){
       return appUserService.SignInUser(appUser);
    }
    public Optional<AppUser> getUserByEmail(String email) { return appUserService.getByEmail(email); }
}
