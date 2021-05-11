package dev.tobiadegbuji.spotify_service.exceptions;


public class SSAuthTimeoutException extends SSAuthException{

    public SSAuthTimeoutException(String developerMessage, String backendErrorCode, String backendErrorMessage) {
        super("ROA5004", "Gateway Timeout", developerMessage, backendErrorCode, backendErrorMessage, "504");
    }
}
