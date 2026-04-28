package com.ihor.artist_portfolio_server.repository;


import com.ihor.artist_portfolio_server.model.Painting;
import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends MongoRepository<Painting, String> {
    List<Painting> findByStatusAndPriceBetween(PaintingStatus status, Double priceMin, Double priceMax);

    List<Painting> findByStatus(PaintingStatus status);

    List<Painting> findByStatusAndPriceGreaterThanEqual(PaintingStatus status, Double min);

    List<Painting> findByStatusAndPriceLessThanEqual(PaintingStatus status,Double max);

    List<Painting> findByPriceBetween(Double priceMin, Double priceMax);

    List<Painting> findByPriceGreaterThanEqual(Double min);

    List<Painting> findByPriceLessThanEqual(Double max);
}
