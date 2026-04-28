package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.PaintingCreateDTO;
import com.ihor.artist_portfolio_server.dto.PaintingResponseDTO;
import com.ihor.artist_portfolio_server.dto.PaintingUpdateDTO;
import com.ihor.artist_portfolio_server.model.Painting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaintingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Painting toModel(PaintingCreateDTO dto);

    PaintingResponseDTO toDTO(Painting painting);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateModel(PaintingUpdateDTO dto, @MappingTarget Painting painting);
}
