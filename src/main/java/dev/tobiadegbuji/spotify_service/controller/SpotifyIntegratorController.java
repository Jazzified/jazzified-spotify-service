package dev.tobiadegbuji.spotify_service.controller;

import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SpotifyArtists;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import dev.tobiadegbuji.spotify_service.service.SpotifyServiceEngine;
import dev.tobiadegbuji.spotify_service.utils.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/jazzified/spotify")
public class SpotifyIntegratorController {

    private final SpotifyServiceEngine spotifyServiceEngine;

    @GetMapping("/searchArtist")
    public ResponseEntity<SearchArtistResponse> queryForArtist(@RequestParam(required = true) String type,
                                                               @RequestParam(required = true) String query,
                                                               @RequestParam(value = CommonConstants.QUERY_LIMIT, required = false) Integer limit){

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setType(type);
        searchRequest.setQuery(query);
        searchRequest.setLimit(Integer.valueOf(CommonConstants.QUERY_LIMIT));

        System.out.println(searchRequest);

        SearchArtistResponse searchArtistResponse = (SearchArtistResponse) spotifyServiceEngine.retrieveSearchResponse(searchRequest);
        return new ResponseEntity<>(searchArtistResponse, HttpStatus.OK);

    }

}
