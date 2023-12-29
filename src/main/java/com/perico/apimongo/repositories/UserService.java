package com.perico.apimongo.repositories;

import org.springframework.stereotype.Service;

import com.perico.apimongo.models.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        user= userRepository.save(user);
        return user;
    }
    public User findByUsername(String username){
        try {
            return userRepository.findByUsername(username);
            
        } catch (Exception e) {
            // ? handle exception
        }
        return null;
    }
    
}
