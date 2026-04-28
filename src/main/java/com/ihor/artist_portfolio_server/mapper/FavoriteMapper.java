package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.FavoriteCreateDTO;
import com.ihor.artist_portfolio_server.dto.FavoriteResponseDTO;
import com.ihor.artist_portfolio_server.model.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Favorite toModel(FavoriteCreateDTO dto);

    FavoriteResponseDTO toDTO(Favorite favorite);
}
