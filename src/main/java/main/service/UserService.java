package main.service;

import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findBySessionId(String sessionId) {
        return userRepository.findBySessionId(sessionId).orElseThrow(EntityNotFoundException::new);
    }

    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        for (User user : iterable) {
            users.add(user);
        }
        return users;
    }

    public Boolean isAuth(String sessionId) {
        return userRepository.findBySessionId(sessionId).isPresent();
    }

}
