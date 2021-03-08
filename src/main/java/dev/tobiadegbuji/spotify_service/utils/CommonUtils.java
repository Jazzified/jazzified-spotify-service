package dev.tobiadegbuji.spotify_service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tobiadegbuji.spotify_service.config.properties.ConfigProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log4j2
public class CommonUtils {


    public static Boolean isVirtualRequest(HttpHeaders httpHeaders) {
        return httpHeaders != null
                && httpHeaders.containsKey(CommonConstants.HEADER_VIRTUAL_REQUEST.toLowerCase())
                && StringUtils.isNotBlank(httpHeaders.getFirst(CommonConstants.HEADER_VIRTUAL_REQUEST.toLowerCase()))
                && CommonConstants.TRUE.equalsIgnoreCase(
                httpHeaders.getFirst(CommonConstants.HEADER_VIRTUAL_REQUEST.toLowerCase()));

    }


    public static TokenRequest prepareTokenRequest(HttpHeaders httpHeaders, AuthorizeRequest authorizeRequest, ConfigProperties configProperties) {
        return TokenRequest.builder()
                .client_id(httpHeaders.getFirst(CommonConstants.HEADER_CLIENT_ID))
                .client_Secret(httpHeaders.getFirst(CommonConstants.HEADER_CLIENT_SECRET))
                .code(authorizeRequest.getAuthorizationCode())
                .grant_type(configProperties.getAuthConfig().getGrantType())
                .redirect_uri(authorizeRequest.getRedirectURL())
                .build();

    }


    public static Optional<String> convertStringToJSON(Object request){
        var objectMapper = new ObjectMapper();
       Optional<String> response = null;
        try{
            response = Optional.of(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return response;
    }


    public static Consumer<List<String>> ifDebugEnabled = (errors) -> {
        if(log.isDebugEnabled())
        errors.forEach(log::debug);
    };




}
