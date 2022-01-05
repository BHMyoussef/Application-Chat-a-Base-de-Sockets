package Spring.backend.Authentication.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appuserrepository;
    public AppUser SignUpUser(AppUser appUser){
        boolean present = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present){
            throw new IllegalStateException("This email Is already exist!");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appuserrepository.save(appUser);
        return appUser;
    }
    public Optional<AppUser> getByEmail(String email) {
        if(!appuserrepository.findByEmail(email).isPresent()) throw new IllegalStateException("User with email "+email+" doesnt exists!");
        else
            return appuserrepository.findByEmail(email);
    }
    public AppUser SignInUser(AppUser appUser){
        boolean present1 = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present1){
            AppUser value = appuserrepository.findByEmail(appUser.getEmail()).get();
            if(value.getPassword().equals(appUser.getPassword())){
                return value;
            }else{
                throw new IllegalStateException("email or password invalid");
            }
        }else {
            throw new IllegalStateException("email or password invalid");
        }
    }
}
