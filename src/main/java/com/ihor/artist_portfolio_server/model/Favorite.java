package com.ihor.artist_portfolio_server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "favorites")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Favorite {
    @Id
    private String id;

    private String userId;
    private String paintingId;

    @CreatedDate
    private LocalDateTime createdAt;
}
