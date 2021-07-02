package dev.tobiadegbuji.spotify_service.controller;

import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import dev.tobiadegbuji.spotify_service.service.SpotifyServiceEngine;
import dev.tobiadegbuji.spotify_service.utils.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/jazzified/spotify")
public class SpotifyServiceController {

    private final SpotifyServiceEngine spotifyServiceEngine;

    @GetMapping("/searchArtist")
    public ResponseEntity<SearchArtistResponse> queryForArtist(@RequestParam String type,
                                                               @RequestParam String query,
                                                               @RequestParam(value = CommonConstants.QUERY_LIMIT, required = false) Integer limit){

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setType(type);
        searchRequest.setQuery(query);
        searchRequest.setLimit(Integer.parseInt(CommonConstants.QUERY_LIMIT));

        SearchArtistResponse searchArtistResponse = (SearchArtistResponse) spotifyServiceEngine.retrieveSearchResponse(searchRequest);
        return new ResponseEntity<>(searchArtistResponse, HttpStatus.OK);

    }



}
