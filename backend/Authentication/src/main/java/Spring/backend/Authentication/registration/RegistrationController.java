package Spring.backend.Authentication.registration;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserRepository;
import Spring.backend.Authentication.appuser.ResponseUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    private AppUserRepository appUserRepository;
    @PostMapping
    public ResponseUser registration (@RequestBody AppUser request){
        if(request.getPassword()=="" || request.getName()=="" || request.getEmail()==""){
            throw new IllegalStateException("All Inputs Are Required!");
        }else{
            return registrationService.register(request);
        }
    }
}
