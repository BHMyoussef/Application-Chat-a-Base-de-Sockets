package com.chatApp.chatBackEnd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserApp> getUsers() {
        return userRepository.findAll();
    }
    public Optional<UserApp> getById(long id) {
        if(!userRepository.existsById(id)) throw new IllegalStateException("user with id "+id+" doesnt exists!");
        else
            return userRepository.findById(id);
    }
    public void addNewUser(UserApp user) {
        Optional<UserApp> clientOptional = userRepository.findByEmail(user.getEmail());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("user exist !");
        }
        userRepository.save(user);
    }

}
