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
        System.out.println("message handled is: "+ message+"to"+to);
        AppUserService apt = null;
        //need to check if the user exist and the status is true to redirect the message
        boolean userExist = apt==null? false: apt.findById(to); // we need to check if the user is exist and online
        if(userExist) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}
