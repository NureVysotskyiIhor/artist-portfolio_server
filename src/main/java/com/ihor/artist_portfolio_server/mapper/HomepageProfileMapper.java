package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.HomepageProfileCreateDTO;
import com.ihor.artist_portfolio_server.dto.HomepageProfileResponseDTO;
import com.ihor.artist_portfolio_server.dto.HomepageProfileUpdateDTO;
import com.ihor.artist_portfolio_server.model.HomepageProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HomepageProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "bio", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    HomepageProfile toModel(HomepageProfileCreateDTO dto);

    HomepageProfileResponseDTO toDTO(HomepageProfile profile);

    @Mapping(target = "id", ignore = true)
    void updateModel(HomepageProfileUpdateDTO dto, @MappingTarget HomepageProfile profile);
}
