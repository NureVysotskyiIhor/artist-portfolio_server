package com.ihor.artist_portfolio_server.model;


import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document(collection = "paintings")
public class Painting {

    @Id
    private String id;

    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private PaintingStatus status = PaintingStatus.FOR_SALE;
    private Boolean isPublic = false;

    @CreatedDate
    private LocalDateTime createdAt;
}
