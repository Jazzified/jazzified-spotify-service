package dev.tobiadegbuji.spotify_service.utils;

import lombok.Data;

@Data
public class TokenResponse {

    private String accessToken;
    private String accessTokenExpiration;
    private String refreshToken;
    private String refreshTokenExpiration;
    private String idToken;

}
