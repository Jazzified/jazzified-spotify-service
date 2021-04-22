package dev.tobiadegbuji.spotify_service.controller;

import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import dev.tobiadegbuji.spotify_service.service.SpotifyServiceEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SpotifyIntegratorControllerTest {

    @Mock
    private SpotifyServiceEngine spotifyServiceEngine;

    @InjectMocks
    private SpotifyIntegratorController spotifyIntegratorController;

    Object searchArtistRes;
    SearchRequest searchRequest;

    @BeforeEach
    void setUp() {
        searchArtistRes = new SearchArtistResponse();
        searchRequest = new SearchRequest();
        searchRequest.setQuery("John");
    }

    @Test
    void queryForArtist() {
        //GIVEN
        given(spotifyServiceEngine.retrieveSearchResponse(any(SearchRequest.class))).willReturn(searchArtistRes);
        searchRequest.setType("artist");

        //WHEN
        ResponseEntity<SearchArtistResponse> res = spotifyIntegratorController.queryForArtist(searchRequest);

        //THEN
        assertEquals(res.getBody(), searchArtistRes);
        assertEquals(res.getStatusCode(), HttpStatus.OK);
        then(spotifyServiceEngine).should(times(1)).retrieveSearchResponse(any(SearchRequest.class));

    }
}