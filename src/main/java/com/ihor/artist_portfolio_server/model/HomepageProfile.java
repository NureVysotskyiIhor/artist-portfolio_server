package com.ihor.artist_portfolio_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "homepage_profile")

public class HomepageProfile {
    @Id
    private String id;

    private String email;
    private String name;
    private String title;
    private String bio;

    private List<String> skills;
    private List<String> achievements;
    private Contacts contacts;

    private Boolean isActive = false;
}
