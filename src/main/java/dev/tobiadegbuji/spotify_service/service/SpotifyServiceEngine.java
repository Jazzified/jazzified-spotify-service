package dev.tobiadegbuji.spotify_service.service;


import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import dev.tobiadegbuji.spotify_service.service.rest.SpotifySearchService;
import dev.tobiadegbuji.spotify_service.service.rest.SpotifyTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class SpotifyServiceEngine {

    private final SpotifyTokenService spotifyTokenService;
    private final SpotifySearchService spotifySearchService;

    public Mono<SearchArtistResponse> retrieveSearchResponse(SearchRequest searchRequest) {

        //Retrieve Spotify Token
        AuthenticationResponse authenticationResponse = spotifyTokenService.retrieveToken(new HttpHeaders());

        //Retrieve Search Result
        Mono<SearchArtistResponse> searchResponse = spotifySearchService.getArtistResponse(authenticationResponse, searchRequest);


        return searchResponse;
    }

}
