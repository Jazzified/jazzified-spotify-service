package dev.tobiadegbuji.spotify_service.controller;

import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SpotifyArtists;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import dev.tobiadegbuji.spotify_service.service.SpotifyServiceEngine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/jazzified/spotify")
public class SpotifyIntegratorController {

    private final SpotifyServiceEngine spotifyServiceEngine;

    @GetMapping("/searchArtist")
    public ResponseEntity<SearchArtistResponse> queryForArtist(@RequestBody @Valid SearchRequest searchRequest){
        SearchArtistResponse searchArtistResponse = (SearchArtistResponse) spotifyServiceEngine.retrieveSearchResponse(searchRequest);
        return new ResponseEntity<>(searchArtistResponse, HttpStatus.OK);

    }

}
