package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.ArtistCreateDTO;
import com.ihor.artist_portfolio_server.dto.ArtistUpdateDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.ArtistMapper;
import com.ihor.artist_portfolio_server.model.Artist;
import com.ihor.artist_portfolio_server.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public Artist getArtistById(String id) {
        return artistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist not found"));
    }

    public Artist createArtist(ArtistCreateDTO dto) {
        Artist artist = artistMapper.toModel(dto);
        return artistRepository.save(artist);
    }

    public Artist updateArtist(String id, ArtistUpdateDTO dto) {
        Artist existingArtist = artistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist not found"));
        artistMapper.updateModel(dto, existingArtist);
        return artistRepository.save(existingArtist);
    }
}