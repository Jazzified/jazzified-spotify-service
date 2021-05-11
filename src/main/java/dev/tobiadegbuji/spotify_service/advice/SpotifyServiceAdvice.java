package dev.tobiadegbuji.spotify_service.advice;


import dev.tobiadegbuji.spotify_service.exceptions.SSAuthTokenException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class SpotifyServiceAdvice {


    @ExceptionHandler(SSAuthTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleSSAuthTokenException() {

        return null;
    }


}
