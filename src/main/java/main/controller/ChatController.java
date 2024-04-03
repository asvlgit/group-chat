package main.controller;

import main.model.Message;
import main.model.User;
import main.service.MessageService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/init")
    public Map<String, Boolean> init() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        return Map.of("result", userService.isAuth(sessionId));
    }

    @PostMapping("/auth")
    public Map<String, Boolean> auth(@RequestParam String name) {

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);

        userService.save(user);

        return Map.of("result", true);
    }

    @PostMapping("/message")
    public Map<String, Boolean> sendMessage(@RequestParam String message) {

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userService.findBySessionId(sessionId);

        Message messageEntity = new Message();
        messageEntity.setDateTime(LocalDateTime.now());
        messageEntity.setMessage(message);
        messageEntity.setUser(user);

        return Map.of("result", true);
    }

    @GetMapping("/message")
    public List<String> getMessagesList() {
        return null;
    }

    @GetMapping("/user")
    public HashMap<Integer, String> getUsersList() {
        return null;
    }
}
