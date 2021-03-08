package dev.tobiadegbuji.spotify_service.utils;


import lombok.Data;

@Data
public class RefreshTokenResponse {

    private String accessToken;

    private String accesTokenExpiration;

    private String refreshToken;

    private String refreshTokenExpiration;

    private String idToken;


}
