package me.sreejithnair.linkup.user_service.controller;

import lombok.RequiredArgsConstructor;
import me.sreejithnair.linkup.user_service.dto.request.SignInRequestDto;
import me.sreejithnair.linkup.user_service.dto.request.SignUpRequestDto;
import me.sreejithnair.linkup.user_service.dto.response.TokenResponseDto;
import me.sreejithnair.linkup.user_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    ResponseEntity<TokenResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        TokenResponseDto tokenResponseDto = authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(tokenResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    ResponseEntity<TokenResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        TokenResponseDto tokenResponseDto = authService.signIn(signInRequestDto);
        return ResponseEntity.ok(tokenResponseDto);
    }
}
