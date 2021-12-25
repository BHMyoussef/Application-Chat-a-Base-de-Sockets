package Spring.Authentication.registration;

import Spring.Authentication.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    @PostMapping
    public AppUser registration (@RequestBody AppUser request){
        return registrationService.register(request);
    }
    @GetMapping
    public String getusers(){
        return "response";
    }
}
