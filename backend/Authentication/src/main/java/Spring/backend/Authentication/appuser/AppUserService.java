package Spring.backend.Authentication.appuser;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appuserrepository;

    @Transactional
    public List<AppUser> findUsers(){
        return appuserrepository.findAllConnected();
    }

    @Transactional
    public List<AppUser> findAllUsers(){
        return appuserrepository.findAll();
    }
    public boolean userExistById(String id){ return appuserrepository.findById(id).isPresent();}
    public AppUser findById(String id){ return appuserrepository.findById(id).get();}


    public ResponseUser SignUpUser(AppUser appUser){
        boolean present = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present){
            throw new IllegalStateException("This email Is already exist!");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appuserrepository.save(appUser);
        ResponseUser responseUser = new ResponseUser();
        BeanUtils.copyProperties(appUser, responseUser);
        return responseUser;
    }

    public AppUser updateUser(AppUser appUser){
        AppUser updatedUser = appuserrepository.findByEmail(appUser.getEmail()).get();
        updatedUser.set_connected(false);
        return appuserrepository.save(updatedUser);
    }

    public Optional<AppUser> getByEmail(String email) {
        if(!appuserrepository.findByEmail(email).isPresent()) throw new IllegalStateException("User with email "+email+" doesnt exists!");
        else
            return appuserrepository.findByEmail(email);
    }
    public ResponseUser SignInUser(AppUser appUser){
        boolean present1 = appuserrepository.findByEmail(appUser.getEmail()).isPresent();
        if(present1){
            AppUser value = appuserrepository.findByEmail(appUser.getEmail()).get();
           // System.out.println("hello before if");
            if(bCryptPasswordEncoder.matches(appUser.getPassword(), value.getPassword())){
                // System.out.println("hello after if");
                ResponseUser responseUser = new ResponseUser();
                BeanUtils.copyProperties(responseUser, value);
                value.set_connected(true);
                value.setName("ok");
                appuserrepository.save(value);
                return responseUser;
            }else{
                throw new IllegalStateException("email or password invalidddddd");
            }
        }else {
            throw new IllegalStateException("email or password invalid");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        boolean isUserExists = appuserrepository.findByEmail(email).isPresent();
        if (isUserExists) {
            AppUser appUser =  appuserrepository.findByEmail(email).get();
            appUser.set_connected(true);
            appuserrepository.save(appUser);
            return new User(appUser.getEmail(), appUser.getPassword(),new ArrayList<>());
        }else throw new UsernameNotFoundException(email);
    }
}
