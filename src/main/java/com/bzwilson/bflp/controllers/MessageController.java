package com.bzwilson.bflp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

//    @MessageMapping("/hello")
//    @SendTo("/topic/messages")
//    public String send(String message) {
//        return message;
//    }


//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String processMessageFromClient(String message, @Header("simpSessionId") String sessionId) throws Exception {
//        System.out.println("message: " + message + "going to session id: " + sessionId + "subscription id = " + fa);
//        System.out.println("message: " + message + "going to session id: " + sessionId + "subscription id = " + fa);
       String str =  "session id is: " + sessionId + "message: " + message + "fsafaasfasf";
       System.out.println(str);
       return str;
    }

}

// @Payload String message,
