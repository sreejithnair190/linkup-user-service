package me.sreejithnair.linkup.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import me.sreejithnair.linkup.user_service.dto.request.SignInRequestDto;
import me.sreejithnair.linkup.user_service.dto.request.SignUpRequestDto;
import me.sreejithnair.linkup.user_service.dto.response.TokenResponseDto;
import me.sreejithnair.linkup.user_service.entity.User;
import me.sreejithnair.linkup.user_service.exception.BadRequestException;
import me.sreejithnair.linkup.user_service.exception.ResourceAlreadyExistsException;
import me.sreejithnair.linkup.user_service.repository.UserRepository;
import me.sreejithnair.linkup.user_service.service.AuthService;
import me.sreejithnair.linkup.user_service.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static me.sreejithnair.linkup.user_service.util.PasswordUtil.hashPassword;
import static me.sreejithnair.linkup.user_service.util.PasswordUtil.verifyPassword;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public TokenResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(signUpRequestDto.getEmail());
        if (existingUser.isPresent()){
            throw new ResourceAlreadyExistsException("User with email already exists: " + signUpRequestDto.getEmail());
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setPassword(hashPassword(signUpRequestDto.getPassword()));

        User savedUser = userRepository.save(newUser);

        return jwtService.generateTokens(savedUser);
    }

    @Override
    public TokenResponseDto signIn(SignInRequestDto signInRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(signInRequestDto.getEmail());
        if (existingUser.isEmpty()){
            throw new BadRequestException("Invalid email");
        }

        User user = existingUser.get();

        boolean validPassword = verifyPassword(signInRequestDto.getPassword(), user.getPassword());
        if (!validPassword) {
            throw new BadRequestException("Invalid Password");
        }


        return jwtService.generateTokens(user);
    }
}
