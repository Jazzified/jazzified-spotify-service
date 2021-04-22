package dev.tobiadegbuji.spotify_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpotifyArtists {

    private String href;
    private List<SpotifyArtist> items;
    private Integer limit;
    private Integer total;
    private Integer offset;

}
