package dev.tobiadegbuji.spotify_service.exceptions;


public class SSAuthException extends SpotifyServiceException{

    public SSAuthException(String code, String message, String developerMessage, String backendErrorCode, String backendErrorMessage, String httpStatusCode) {
        super();
    }

}
