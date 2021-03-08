package dev.tobiadegbuji.spotify_service.utils;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String ClientId;
    private String grantType;
    private String code;
    private String redirectURI;
}
