package dev.tobiadegbuji.spotify_service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
@Component
public class AuthConfig {
   private String grantType;
   private String clientName;
   private String ownerCode;
}
