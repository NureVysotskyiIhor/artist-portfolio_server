package com.ihor.artist_portfolio_server.dto;


import com.ihor.artist_portfolio_server.model.Contacts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomepageProfileCreateDTO {
    private String name;
    private String email;
    private Contacts contacts;
}
