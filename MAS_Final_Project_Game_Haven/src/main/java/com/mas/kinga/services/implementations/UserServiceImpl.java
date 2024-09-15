package com.mas.kinga.services.implementations;

import com.mas.kinga.exceptions.ResourceNotFoundException;
import com.mas.kinga.models.*;
import com.mas.kinga.repositories.UserRepository;
import com.mas.kinga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long id) {
        if(!userExists(id)) throw new ResourceNotFoundException(User.class.getName(), "userId", id);
        return userRepository.findById(id).orElse(null);
    }

    public Boolean userExists(long id){
        System.err.println("Checking user by id " + id + " returning value: " + userRepository.existsById(id)); return userRepository.existsById(id);
    }
}
