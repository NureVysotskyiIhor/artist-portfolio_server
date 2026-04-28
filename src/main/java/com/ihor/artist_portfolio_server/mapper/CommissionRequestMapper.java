package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.CommissionRequestCreateDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestResponseDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateArtistDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateDTO;
import com.ihor.artist_portfolio_server.model.CommissionRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommissionRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "location", ignore = true)
    CommissionRequest toModel(CommissionRequestCreateDTO dto);

    CommissionRequestResponseDTO toDTO(CommissionRequest commissionRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateModel(CommissionRequestUpdateDTO dto, @MappingTarget CommissionRequest commissionRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", source = "status")
    void updateModel(CommissionRequestUpdateArtistDTO dto, @MappingTarget CommissionRequest commissionRequest);

    @AfterMapping
    default void mapLocation(CommissionRequest source, @MappingTarget CommissionRequestResponseDTO target) {
        if (source.getLocation() != null) {
            target.setLongitude(source.getLocation().getX());
            target.setLatitude(source.getLocation().getY());
        }
    }
}
