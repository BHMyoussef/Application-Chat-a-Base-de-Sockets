package Spring.backend.Authentication.registration;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserRepository;
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
    public AppUser registration (@RequestBody AppUser request){ return registrationService.register(request); }
    @GetMapping
    public List<AppUser> getusers(){
        return appUserRepository.findAll();
    }
}
