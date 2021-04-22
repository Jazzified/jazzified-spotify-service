package dev.tobiadegbuji.spotify_service.service;


import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.service.rest.SpotifyTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SpotifyServiceEngine {

    private final SpotifyTokenService spotifyTokenService;

    public SearchArtistResponse retrieveSearchResponse(){

        //Retrieve Spotify Token
        spotifyTokenService.retrieveToken(new HttpHeaders());

        //Retrieve Search Result

        return null;
    }

}
