package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.AppConfig;
import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class SpotifySearchService {


    private RestTemplate restTemplate;

    private AppConfig config;


    public SearchArtistResponse getArtistResponse(AuthenticationResponse authResponse, SearchRequest searchRequest){

        //Add Auth Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authResponse.getAccess_token());



        return null;
    }


}
