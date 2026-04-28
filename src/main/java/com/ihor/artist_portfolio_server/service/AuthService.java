package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.LoginResponse;
import com.ihor.artist_portfolio_server.dto.UserCreateDTO;
import com.ihor.artist_portfolio_server.mapper.UserMapper;
import com.ihor.artist_portfolio_server.model.EmailVerificationToken;
import com.ihor.artist_portfolio_server.model.User;
import com.ihor.artist_portfolio_server.repository.ArtistRepository;
import com.ihor.artist_portfolio_server.repository.EmailVerificationTokenRepository;
import com.ihor.artist_portfolio_server.repository.FavoriteRepository;
import com.ihor.artist_portfolio_server.repository.UserRepository;
import com.ihor.artist_portfolio_server.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmailVerificationTokenRepository tokenRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ArtistRepository artistRepository;
    public User register(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toModel(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.save(user);

        String token = UUID.randomUUID().toString();

        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setToken(token);
        emailVerificationToken.setUserId(savedUser.getId());
        emailVerificationToken.setExpiresAt(LocalDateTime.now().plusHours(24));

        tokenRepository.save(emailVerificationToken);

        emailService.sendVerificationEmail(savedUser.getEmail(), token);

        return savedUser;
    }

    public LoginResponse login (String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.getIsVerified()){
            throw new RuntimeException("Email not verified");
        }

        boolean isArtist = artistRepository.existsByEmail(email);
        String token = jwtService.generateToken(user.getId(), user.getEmail(), isArtist);
        return new LoginResponse(token, isArtist);
    }

    public void verifyToken(String token) {
        EmailVerificationToken verificationToken = tokenRepository
                .findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if (verificationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = userRepository.findById(verificationToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsVerified(true);

        userRepository.save(user);

        tokenRepository.delete(verificationToken);
    }
}
