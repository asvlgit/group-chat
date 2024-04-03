package main.dto;

import main.model.User;

public class UserMapper {

    public static UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getName());
        return userDTO;
    }
}
