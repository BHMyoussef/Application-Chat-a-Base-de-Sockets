package Spring.backend.socket.event;

import com.sun.istack.logging.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Component
public class PresenceEventListener {

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        System.out.println("Client with username disconnected: " + event.getUser());
    }
}
