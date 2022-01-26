package Spring.backend.socket.event;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserRepository;
import Spring.backend.Authentication.appuser.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;

@Component
@Service
public class WebSocketEventListener implements ApplicationListener<SessionDisconnectEvent> {
    Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    AppUserRepository appUserRepository;
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        logger.error("SessionDisconnectEvent user =======> " + event.getUser().getName());

        boolean isUserExists = appUserRepository.findByEmail(event.getUser().getName()).isPresent();
        if (isUserExists) {
            AppUser appUser =  appUserRepository.findByEmail(event.getUser().getName()).get();
            appUser.set_connected(false);
            appUserRepository.save(appUser);
        }
    }
}