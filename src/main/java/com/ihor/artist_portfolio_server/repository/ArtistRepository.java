package com.ihor.artist_portfolio_server.repository;

import com.ihor.artist_portfolio_server.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    Boolean existsByEmail(String email);
}
