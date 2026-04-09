package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.model.User;
import com.ihor.artist_portfolio_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email){
        return userRepository.findById(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(String id, User updateUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updateUser.getName());
        existingUser.setBio(updateUser.getBio());
        existingUser.setAvaratUlr(updateUser.getAvaratUlr());

        return userRepository.save(existingUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
