package com.trello.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
@Slf4j
@Controller
public class webSocketController {

    @GetMapping("/connect")
    public String connect() {
        return "index";
    }

    @RestController
    public class WebSocketController {

        @MessageMapping("/state")
        @SendTo("/topic/state")
        public TextMessage updateUserState(String state) {
            String username = "";
            String output = username + "state changed: " + state;
            log.info("web-socket controller: {}", output);
            System.out.println(output);
            return new TextMessage(output);
        }
    }
}