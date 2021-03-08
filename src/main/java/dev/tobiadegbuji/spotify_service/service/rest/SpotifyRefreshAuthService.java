package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.utils.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class SpotifyRefreshAuthService {

    private final RestTemplate restTemplate;

    private final ConfigProperties configProperties;

    //TODO: IMPLEMENT HYSTRIX COMMAND
    public RefreshTokenResponse execute(HttpHeaders httpHeaders, RefreshTokenRequest refreshTokenRequest){

        var endpointUrl = configProperties.getEndpointURL().getSpotifyApiRefreshToken();

        if(CommonUtils.isVirtualRequest(httpHeaders) && !StringUtils.isEmpty(configProperties.getVirtualEndpointURL().getSpotifyApiRefreshToken()))
            endpointUrl = configProperties.getEndpointURL().getSpotifyApiRefreshToken();

        var request = new HttpEntity<>(refreshTokenRequest, httpHeaders);


        CommonUtils.ifDebugEnabled.accept(List.of(
                "Spotify end point URL: => " + endpointUrl,
                "Spotify Refresh Auth Token Request => " + CommonUtils.convertStringToJSON(request)));

        ResponseEntity<RefreshTokenResponse> refreshTokenResponse = restTemplate.postForEntity(endpointUrl, request, RefreshTokenResponse.class);

        CommonUtils.ifDebugEnabled.accept(List.of("Spotify Auth Token Request => " + CommonUtils.convertStringToJSON(request)));

        return refreshTokenResponse.getBody();
    }

    //TODO: Implement Fallback Method


}
