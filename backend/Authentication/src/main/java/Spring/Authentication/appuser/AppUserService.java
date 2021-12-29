package Spring.Authentication.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService{
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appuserrepository;
    public AppUser SignUpUser(AppUser appUser){
        boolean present = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present){
            throw new IllegalStateException("this email already exist!");
        }
        appuserrepository.save(appUser);
        return appUser;
    }
    public String SignInUser(AppUser appUser){
        boolean present1 = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present1){
            AppUser value = appuserrepository.findByEmail(appUser.getEmail()).get();
            if(value.getPassword().equals(appUser.getPassword())){
                return "you have access";
            }else{
                return "email or password invalid";
            }
        }else {
            return "email or password invalid";
        }
    }
}