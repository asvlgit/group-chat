package main.service;

import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    public Boolean isAuth(String sessionId) {
        return userRepository.findBySessionId(sessionId).isPresent();
    }

}
