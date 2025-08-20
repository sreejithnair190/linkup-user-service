package me.sreejithnair.linkup.user_service.service;

import me.sreejithnair.linkup.user_service.dto.request.SignInRequestDto;
import me.sreejithnair.linkup.user_service.dto.request.SignUpRequestDto;
import me.sreejithnair.linkup.user_service.dto.response.TokenResponseDto;

public interface AuthService {
    TokenResponseDto signUp(SignUpRequestDto signUpRequestDto);
    TokenResponseDto signIn(SignInRequestDto signInRequestDto);
}
