package main.dto;

import main.model.Message;

import java.time.format.DateTimeFormatter;

public class MessageMapper {

    public static MessageDTO map(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setText(message.getMessage());
        messageDTO.setUsername(message.getUser().getName());
        messageDTO.setDatetime(message.getDateTime()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return messageDTO;
    }
}
