package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.FavoriteCreateDTO;
import com.ihor.artist_portfolio_server.dto.FavoriteWithPaintingDTO;
import com.ihor.artist_portfolio_server.dto.FavoritesPaintingStatsDTO;
import com.ihor.artist_portfolio_server.exception.ConflictException;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.FavoriteMapper;
import com.ihor.artist_portfolio_server.model.Favorite;
import com.ihor.artist_portfolio_server.repository.FavoriteRepository;
import com.ihor.artist_portfolio_server.repository.PaintingRepository;
import com.ihor.artist_portfolio_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;
    private final MongoTemplate mongoTemplate;

    public Favorite addFavorite(FavoriteCreateDTO dto, String userId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        paintingRepository.findById(dto.getPaintingId()).orElseThrow(() -> new EntityNotFoundException("Painting not found"));

        if (favoriteRepository.findByUserIdAndPaintingId(userId, dto.getPaintingId()).isPresent()) {
            throw new ConflictException("Favorite already exists");
        }
        Favorite favorite = favoriteMapper.toModel(dto);
        favorite.setUserId(userId);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(String userId, String paintingId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        paintingRepository.findById(paintingId).orElseThrow(() -> new EntityNotFoundException("Painting not found"));

        favoriteRepository.findByUserIdAndPaintingId(userId, paintingId).orElseThrow(() -> new EntityNotFoundException("Favorite not found"));
            favoriteRepository.deleteByUserIdAndPaintingId(userId, paintingId);
    }

    public List<Favorite> getUserFavorites(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return favoriteRepository.findByUserId(userId);
    }

    public List<FavoriteWithPaintingDTO> getFavoritesWithPainting(String userId) {
        MatchOperation match = Aggregation.match(
                Criteria.where("userId").is(userId)
        );

        AggregationOperation addFields = context -> new Document("$addFields",
                new Document("paintingObjectId",
                        new Document("$toObjectId", "$paintingId")
                )
        );

        LookupOperation lookup = LookupOperation.newLookup()
                .from("paintings")
                .localField("paintingObjectId")
                .foreignField("_id")
                .as("painting");

        UnwindOperation unwind = Aggregation.unwind("painting", true);

        Aggregation aggregation = Aggregation.newAggregation(match, addFields, lookup, unwind);

        return mongoTemplate.aggregate(
                aggregation, "favorites", FavoriteWithPaintingDTO.class).getMappedResults();
    }

    public List<FavoritesPaintingStatsDTO> getFavoritesStats (){

        GroupOperation group = Aggregation.group("paintingId").count().as("count");

        ProjectionOperation project = Aggregation.project()
                .andExpression("_id")
                .as("paintingId")
                .andInclude("count");

        Aggregation aggregation = Aggregation.newAggregation(group, project);

        return mongoTemplate.aggregate(
                aggregation, "favorites", FavoritesPaintingStatsDTO.class).getMappedResults();
    }
}
