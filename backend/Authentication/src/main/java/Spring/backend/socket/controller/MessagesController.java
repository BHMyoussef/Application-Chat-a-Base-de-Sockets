package Spring.backend.socket.controller;

import Spring.backend.socket.models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MessagesController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("message handled is: "+ message+"to"+to);
        boolean userExist = false; // we need to check if the user is exist and online
        if(userExist) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}
