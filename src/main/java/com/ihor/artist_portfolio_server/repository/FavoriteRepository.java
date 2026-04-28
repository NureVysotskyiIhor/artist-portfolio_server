package com.ihor.artist_portfolio_server.repository;


import com.ihor.artist_portfolio_server.model.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    Optional<Favorite> findByUserIdAndPaintingId(String userId, String paintingId);
    List<Favorite> findByUserId(String userId);
    List<Favorite> findByPaintingId(String paintingId);
    void deleteByUserIdAndPaintingId(String userId, String paintingId);
    void deleteByPaintingId(String paintingId);
    void deleteByUserId(String userId);
}
