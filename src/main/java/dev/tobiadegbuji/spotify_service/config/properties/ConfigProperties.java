package dev.tobiadegbuji.spotify_service.config.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigProperties {
    private EndpointURL endpointURL;
    private AuthConfig authConfig;
    private VirtualEndpointURL virtualEndpointURL;
}
