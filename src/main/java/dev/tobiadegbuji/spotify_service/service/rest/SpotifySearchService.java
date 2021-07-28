package dev.tobiadegbuji.spotify_service.service.rest;

import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import dev.tobiadegbuji.spotify_service.dto.AuthenticationResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchArtistResponse;
import dev.tobiadegbuji.spotify_service.dto.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@AllArgsConstructor
public class SpotifySearchService {

    private final ConfigProperties properties;

    // @CircuitBreaker(name = "artistResponseCB", fallbackMethod = "artistResponseFallback")
    public Mono<SearchArtistResponse> getArtistResponse(AuthenticationResponse authResponse, SearchRequest searchRequest) {

        WebClient webClient = WebClient
                .builder()
                .baseUrl(properties.getEndpointURL().getSpotifySearchQuery())
                .defaultHeaders(httpHeaders -> httpHeaders.set("Authorization", "Bearer" + " " + authResponse.getAccess_token()))
                .build();

        Function<UriBuilder, URI> requestUri = uriBuilder -> uriBuilder
                .queryParam("q", searchRequest.getQuery())
                .queryParam("type", searchRequest.getType().toLowerCase())
                .queryParam("limit", searchRequest.getLimit())
                .build();

        return webClient.get()
                .uri(requestUri)
                .retrieve()
                .bodyToMono(SearchArtistResponse.class);

    }
}
