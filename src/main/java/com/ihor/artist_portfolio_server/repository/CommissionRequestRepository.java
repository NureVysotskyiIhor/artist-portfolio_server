package com.ihor.artist_portfolio_server.repository;

import com.ihor.artist_portfolio_server.model.CommissionRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRequestRepository extends MongoRepository<CommissionRequest, String> {
    List<CommissionRequest> findByUserId(String userId);
    List<CommissionRequest> findByLocationNear(Point point, Distance distance);
}
