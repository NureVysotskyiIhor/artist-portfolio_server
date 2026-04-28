package com.ihor.artist_portfolio_server.mapper;

import com.ihor.artist_portfolio_server.model.EmailVerificationToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailVerificationTokenMapper {
    // No DTOs exist for EmailVerificationToken; token is created internally in AuthService
}
