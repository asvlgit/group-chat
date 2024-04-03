package main.controller;

import main.dto.MessageDTO;
import main.dto.MessageMapper;
import main.dto.UserDTO;
import main.dto.UserMapper;
import main.model.Message;
import main.model.User;
import main.service.MessageService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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

        Message newMessage = new Message();
        newMessage.setDateTime(LocalDateTime.now());
        newMessage.setMessage(message);
        newMessage.setUser(user);

        messageService.saveMessage(newMessage);

        return Map.of("result", true);
    }

    @GetMapping("/message")
    public List<MessageDTO> getMessagesList() {
        ArrayList<MessageDTO> messages = new ArrayList<>();
        messageService.getMessages().stream()
                .sorted(Comparator.comparing(Message::getDateTime))
                .forEach(message -> {
                    messages.add(MessageMapper.map(message));
                });
        return messages;
    }

    @GetMapping("/user")
    public List<UserDTO> getUsersList() {
        ArrayList<UserDTO> users = new ArrayList<>();
        userService.getUsers().forEach(user -> {
            users.add(UserMapper.map(user));
        });
        return users;
    }
}
