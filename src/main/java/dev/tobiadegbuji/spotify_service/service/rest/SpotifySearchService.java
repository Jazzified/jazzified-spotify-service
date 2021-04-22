package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SpotifySearchService {


    private RestTemplate restTemplate;

    private ConfigProperties properties;

    public Object getArtistResponse(AuthenticationResponse authResponse, SearchRequest searchRequest) {

        //Add Auth Header
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authResponse.getAccess_token());
        headers.set("Authorization", "Bearer" + " " + authResponse.getAccess_token());

        //Add path parameters
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(properties.getEndpointURL().getSpotifySearchQuery())
                .queryParam("q", searchRequest.getQuery())
                .queryParam("type", searchRequest.getType().toLowerCase())
                .queryParam("limit", searchRequest.getLimit());

        log.info(builder.toUriString());


        HttpEntity<?> searchSpotifyRequest = new HttpEntity<>(headers);

        Optional<HttpEntity<SearchArtistResponse>> response = Optional.empty();

        try {
            response = Optional.of(restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    searchSpotifyRequest,
                    SearchArtistResponse.class
            ));
            log.info(response.get().getBody().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.map(HttpEntity::getBody).orElseThrow(() -> new RuntimeException());
    }


}
