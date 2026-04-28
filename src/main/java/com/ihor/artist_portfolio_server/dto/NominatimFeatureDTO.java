package com.ihor.artist_portfolio_server.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NominatimFeatureDTO {

    @JsonAlias("display_name")
    private String displayName;

    private String lat;
    private String lon;
}
