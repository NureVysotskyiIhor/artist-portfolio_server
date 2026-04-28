package com.ihor.artist_portfolio_server.repository;

import com.ihor.artist_portfolio_server.model.EmailVerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends MongoRepository<EmailVerificationToken, String> {

    Optional<EmailVerificationToken> findByToken(String token);

    Optional<EmailVerificationToken> findByUserId(String userId);

    void deleteByToken(String token);
}
