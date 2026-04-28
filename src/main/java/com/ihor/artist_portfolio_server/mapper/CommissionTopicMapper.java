package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.CommissionTopicDTO;
import com.ihor.artist_portfolio_server.dto.CommissionTopicResponseDTO;
import com.ihor.artist_portfolio_server.model.CommissionTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommissionTopicMapper {

    @Mapping(target = "id", ignore = true)
    CommissionTopic toModel(CommissionTopicDTO dto);

    CommissionTopicResponseDTO toDTO(CommissionTopic commissionTopic);

    @Mapping(target = "id", ignore = true)
    void updateModel(CommissionTopicDTO dto, @MappingTarget CommissionTopic commissionTopic);
}
