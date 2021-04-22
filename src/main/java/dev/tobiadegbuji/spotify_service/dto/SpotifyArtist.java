package dev.tobiadegbuji.spotify_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class SpotifyArtist {

    private SpotifyExternalUrl externalUrl;
    private SpotifyFollowers followers;
    private ArrayList<String> genres;
    private String href;
    private String id;
    private ArrayList<SpotifyArtistImage> images;
    private String name;
    private Integer popularity;
    private String type;
    private String uri;
}
