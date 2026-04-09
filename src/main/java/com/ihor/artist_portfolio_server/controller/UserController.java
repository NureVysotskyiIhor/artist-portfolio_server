package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.UserCreateDTO;
import com.ihor.artist_portfolio_server.dto.UserResponseDTO;
import com.ihor.artist_portfolio_server.dto.UserUpdateDTO;
import com.ihor.artist_portfolio_server.model.User;
import com.ihor.artist_portfolio_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        User user = new User();

        user.setEmail(userCreateDTO.getEmail());
        user.setName(userCreateDTO.getUsername());
        user.setAvaratUlr(userCreateDTO.getAvatarUrl());
        user.setBio(userCreateDTO.getBio());

        return ResponseEntity.ok(userService.createUser(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setName(userUpdateDTO.getUsername());
        user.setAvaratUlr(userUpdateDTO.getAvatarUrl());
        user.setBio(userUpdateDTO.getBio());

        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
