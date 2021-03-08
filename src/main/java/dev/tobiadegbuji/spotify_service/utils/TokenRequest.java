package dev.tobiadegbuji.spotify_service.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {

    private String client_id;
    private String client_Secret;
    private String grant_type;
    private String code;
    private String redirect_uri;

}

