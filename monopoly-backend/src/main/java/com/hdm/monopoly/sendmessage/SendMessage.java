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

    /**
     * message to an individual player
     * @param sessionId the sessionID of the player who should receive the message
     * @param destination to which address the message should be sent
     * @param message the payload that is sent away
     */
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

    /**
     * message is sent to all connected players
     * @param destination to which address the message should be sent
     * @param message the payload that is sent away
     */
    public void sendToAll(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
        log.info("send message to all. Destination: " + destination + "content: " + message);
    }
}
