package com.ihor.artist_portfolio_server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "commission_topics")
public class CommissionTopic {
    @Id
    private String id;

    private String name;
    private String description;
    private String icon;
    private Boolean isActive = true;
}
