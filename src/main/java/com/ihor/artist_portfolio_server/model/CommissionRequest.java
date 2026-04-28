package com.ihor.artist_portfolio_server.model;

import com.ihor.artist_portfolio_server.model.enums.CommissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "commission_requests")
public class CommissionRequest {
    @Id
    private String id;

    private String userId;
    private String topicId;
    private String topicName;
    private String title;
    private String description;
    private Double budgetMin;
    private Double budgetMax;
    private LocalDate deadline;
    private Contacts contacts;
    private String artistNote;
    private CommissionStatus status = CommissionStatus.PENDING;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String city;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location; // [longitude, latitude]
}
