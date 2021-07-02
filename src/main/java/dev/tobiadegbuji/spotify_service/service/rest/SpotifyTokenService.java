package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.exceptions.SSAuthTokenException;
import dev.tobiadegbuji.spotify_service.utils.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
@AllArgsConstructor
public class SpotifyTokenService {

    private final RestTemplate restTemplate;

    private final ConfigProperties configProperties;

    public AuthenticationResponse retrieveToken(HttpHeaders headers) {

        log.debug("Beginning retrieveToken method");

        //Authentication Endpoint for Spotify
        String authEndPoint = configProperties.getEndpointURL().getSpotifyApiToken();

        //Create Authorization Header
        Base64 base64Token = new Base64();
        String clientIdAndSecret = configProperties.getAuthConfig().getClientId() + ":" + configProperties.getAuthConfig().getClientSecret();
        log.debug(() -> "ClientId and ClientSecret: " + clientIdAndSecret);
        String authorizationHeaderValue = "Basic " + base64Token.encodeToString(clientIdAndSecret.getBytes());
        log.debug(() -> "Auth Header: " + authorizationHeaderValue);
        headers.add("Authorization", authorizationHeaderValue);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        MultiValueMap<String, String> authenticationRequest = new LinkedMultiValueMap<>();
        authenticationRequest.add("grant_type", CommonConstants.GRANT_TYPE_CLIENT_CREDENTIALS);

        //Form Request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(authenticationRequest, headers);

        log.debug(() -> request.getBody().toString());

        AuthenticationResponse authResponse;

        try {
            //Send Request
            authResponse = restTemplate.postForObject(authEndPoint, request, AuthenticationResponse.class);

            if (authResponse != null)
                log.debug(authResponse::toString);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SSAuthTokenException(e);
        }


        log.debug("Ending retrieveToken method");


        return authResponse;

    }

}
