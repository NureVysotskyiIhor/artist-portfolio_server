package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.UserUpdateDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.UserMapper;
import com.ihor.artist_portfolio_server.model.User;
import com.ihor.artist_portfolio_server.repository.FavoriteRepository;
import com.ihor.artist_portfolio_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserMapper userMapper;

    //public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User updateUser(String id, UserUpdateDTO updateUserDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userMapper.updateModel(updateUserDTO, existingUser);
        return userRepository.save(existingUser);
    }

    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        favoriteRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }
}
