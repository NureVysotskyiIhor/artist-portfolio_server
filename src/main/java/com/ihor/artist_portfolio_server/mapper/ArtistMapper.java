package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.ArtistCreateDTO;
import com.ihor.artist_portfolio_server.dto.ArtistResponseDTO;
import com.ihor.artist_portfolio_server.dto.ArtistUpdateDTO;
import com.ihor.artist_portfolio_server.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "id", ignore = true)
    Artist toModel(ArtistCreateDTO dto);

    ArtistResponseDTO toDTO(Artist artist);

    @Mapping(target = "id", ignore = true)
    void updateModel(ArtistUpdateDTO dto, @MappingTarget Artist artist);
}
