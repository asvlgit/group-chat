package main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String datetime;
    private String username;
    private String text;
}
