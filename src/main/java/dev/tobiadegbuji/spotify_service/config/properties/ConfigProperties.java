package dev.tobiadegbuji.spotify_service.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ConfigProperties {
    private EndpointURL endpointURL;
    private AuthConfig authConfig;
}
