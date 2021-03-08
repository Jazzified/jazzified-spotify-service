package dev.tobiadegbuji.spotify_service.service.rest;

import com.netflix.ribbon.proxy.annotation.Http;
import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.utils.CommonUtils;
import dev.tobiadegbuji.spotify_service.utils.TokenRequest;
import dev.tobiadegbuji.spotify_service.utils.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@Log4j2
@AllArgsConstructor
public class SpotifyAuthService {

    private RestTemplate restTemplate;

    private ConfigProperties configProperties;


    private String getSpotifyApiURL(HttpHeaders httpHeaders) {
        //If virtual request return, grab the virtual endpoint URL
        if (CommonUtils.isVirtualRequest(httpHeaders))
            return configProperties.getVirtualEndpointURL().getSpotifyApiToken();
        return configProperties.getEndpointURL().getSpotifyApiToken();
    }

    public TokenResponse getToken(HttpHeaders httpHeaders, TokenRequest tokenRequest){

        var endpointUrl = getSpotifyApiURL(httpHeaders);
        var request = new HttpEntity<>(tokenRequest, httpHeaders);

        CommonUtils.ifDebugEnabled.accept(List.of(
                "Spotify end point URL: => " + endpointUrl,
                "Spotify Auth Token Request => " + CommonUtils.convertStringToJSON(request)));

        HttpEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(endpointUrl, request, TokenResponse.class);

        CommonUtils.ifDebugEnabled.accept(List.of("Spotify Auth Token Request => " + CommonUtils.convertStringToJSON(request)));

        return tokenResponse.getBody();
    }




}
