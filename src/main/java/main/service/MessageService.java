package main.service;

import main.model.Message;
import main.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        Iterable<Message> iterable = messageRepository.findAll();
        for (Message message : iterable) {
            messages.add(message);
        }
        return messages;
    }

}
