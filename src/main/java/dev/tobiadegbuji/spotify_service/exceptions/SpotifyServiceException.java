package dev.tobiadegbuji.spotify_service.exceptions;

import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SpotifyServiceException extends RuntimeException{

    public SpotifyServiceException(Throwable cause) {
        super(cause);
    }
}
