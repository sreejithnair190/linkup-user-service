package me.sreejithnair.linkup.user_service.service;

import me.sreejithnair.linkup.user_service.dto.response.TokenResponseDto;
import me.sreejithnair.linkup.user_service.entity.User;

public interface JwtService {
    TokenResponseDto generateTokens(User user);
}
