package com.ihor.artist_portfolio_server.repository;

import com.ihor.artist_portfolio_server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional <User> findByEmail(String username);
    
    boolean existsByEmail(String email);

}
