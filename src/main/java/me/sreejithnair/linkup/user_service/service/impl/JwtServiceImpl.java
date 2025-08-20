package me.sreejithnair.linkup.user_service.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.sreejithnair.linkup.user_service.dto.response.TokenResponseDto;
import me.sreejithnair.linkup.user_service.entity.User;
import me.sreejithnair.linkup.user_service.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    private static final Long ACCESS_TOKEN_EXPIRATION = (long) (1000 * 60 * 10);
    private static final Long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 30;

    @Override
    public TokenResponseDto generateTokens(User user) {
        String accessToken = this.buildToken(user, ACCESS_TOKEN_EXPIRATION);
        String refreshToken = this.buildToken(user, REFRESH_TOKEN_EXPIRATION);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getUserClaim(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String buildToken(User user, long expirationMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(now).expiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }
}
