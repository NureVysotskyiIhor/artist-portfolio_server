package com.ihor.artist_portfolio_server.repository;


import com.ihor.artist_portfolio_server.model.CommissionTopic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionTopicRepository extends MongoRepository<CommissionTopic, String> {
}
