package Spring.backend.socket.controller;

import Spring.backend.Authentication.appuser.AppUserService;
import Spring.backend.socket.models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessagesController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("the message: "+message+"reciever is: "+ to);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

    }
}
