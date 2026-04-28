package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.Contacts;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HomepageProfileResponseDTO {
    private String id;
    private String email;
    private String name;
    private String title;
    private String bio;
    private List<String> skills;
    private List<String> achievements ;
    private Contacts contacts;
    private Boolean isActive;
}
