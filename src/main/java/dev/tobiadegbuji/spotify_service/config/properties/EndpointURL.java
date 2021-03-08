package dev.tobiadegbuji.spotify_service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "url.virtual") //Only retrieves properties with prefix of URL
@Component
public class EndpointURL {
    private String spotifyApiToken;
    private String spotifyApiRefreshToken;
    private String spotifySubscriptions;
}
