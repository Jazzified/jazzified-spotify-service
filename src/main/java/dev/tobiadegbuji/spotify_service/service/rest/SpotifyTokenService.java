package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.utils.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class SpotifyTokenService {

    private RestTemplate restTemplate;

    private ConfigProperties configProperties;

    public AuthenticationResponse retrieveToken(HttpHeaders headers){
        //Authentication Endpoint for Spotify
        String authEndPoint = configProperties.getEndpointURL().getSpotifyApiToken();

        //Create Authorization Header
        Base64 base64Token = new Base64();
        String clientIdAndSecret = configProperties.getAuthConfig().getClientId() + ":" + configProperties.getAuthConfig().getClientSecret();
        log.debug("ClientId and ClientSecret: " + clientIdAndSecret);
        String authorizationHeaderValue = "Basic " + base64Token.encodeToString(clientIdAndSecret.getBytes());
        log.debug("Auth Header: " + authorizationHeaderValue);
        headers.add("Authorization", authorizationHeaderValue);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        MultiValueMap<String, String> authenticationRequest= new LinkedMultiValueMap<>();
        authenticationRequest.add("grant_type", CommonConstants.GRANT_TYPE_CLIENT_CREDENTIALS);

        //Form Request
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(authenticationRequest, headers);

        log.debug(request.getBody().toString());

        //Send Request
       AuthenticationResponse authResponse = restTemplate.postForObject(authEndPoint,request,AuthenticationResponse.class);

       log.debug(authResponse.toString());

        return authResponse;

    }

}
