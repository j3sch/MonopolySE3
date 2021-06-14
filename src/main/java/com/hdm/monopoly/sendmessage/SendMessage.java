package com.hdm.monopoly.sendmessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class SendMessage {
    private static final Logger log = LogManager.getLogger(SendMessage.class);

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToPlayer(String sessionId, String destination, String message) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                .create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        messagingTemplate.convertAndSendToUser(sessionId, destination,
                message,
                headerAccessor.getMessageHeaders());
        log.info("send message to player. Destination: " + destination + "content: " + message);
    }

    public void sendToAll(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
        log.info("send message to all. Destination: " + destination + "content: " + message);
    }
}
