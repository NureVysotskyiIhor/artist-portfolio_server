package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.dto.UserCreateDTO;
import com.ihor.artist_portfolio_server.dto.UserResponseDTO;
import com.ihor.artist_portfolio_server.dto.UserUpdateDTO;
import com.ihor.artist_portfolio_server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isVerified", ignore = true)
    @Mapping(target = "name", source = "name")
    User toModel(UserCreateDTO dto);

    @Mapping(target = "name", source = "name")
    UserResponseDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "isVerified", ignore = true)
    void updateModel(UserUpdateDTO dto, @MappingTarget User user);
}
