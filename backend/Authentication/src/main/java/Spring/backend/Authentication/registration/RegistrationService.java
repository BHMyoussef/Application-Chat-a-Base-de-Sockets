package Spring.backend.Authentication.registration;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserService;
import Spring.backend.Authentication.appuser.ResponseUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public boolean test(String s) {
        // TODO: Regex to validate email
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public ResponseUser register(AppUser request){
        boolean is_valid_email = test(request.getEmail());
        if(!is_valid_email){
            throw new IllegalStateException("email entred is not valid");
        }

        return appUserService.SignUpUser(
                new AppUser(
                        request.getName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }
}
