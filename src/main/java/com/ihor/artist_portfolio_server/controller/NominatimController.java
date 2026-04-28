package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.NominatimFeatureDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/nominatim")
@RequiredArgsConstructor
@Tag(name = "Geocoding", description = "Location search via Nominatim/OpenStreetMap")
public class NominatimController {

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";
    private static final String USER_AGENT = "artist-portfolio-app";

    private final RestTemplate restTemplate;

    @Operation(summary = "Search for a location", description = "Proxies Nominatim/OpenStreetMap. Returns up to 5 results")
    @GetMapping("/search")
    public ResponseEntity<List<NominatimFeatureDTO>> search(@RequestParam String q) {
        String url = UriComponentsBuilder.fromHttpUrl(NOMINATIM_URL)
                .queryParam("q", q)
                .queryParam("format", "json")
                .queryParam("limit", 5)
                .queryParam("addressdetails", 1)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, USER_AGENT);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<NominatimFeatureDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        return ResponseEntity.ok(response.getBody());
    }
}
