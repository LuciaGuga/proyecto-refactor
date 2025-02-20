package com.example.refactor.service;

import com.example.refactor.model.Song;
import com.example.refactor.model.SongBuilder;
import com.example.refactor.model.SpotifyArtist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.Optional;

public class SongProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongProcessor.class);

    public void processSongs() {
        final var playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        final var inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        final var playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        final LinkedList<Song> spotifyPlayList = new LinkedList<>();

        // Creamos el Optional<JSONArray>
        Optional<JSONArray> optionalItems = Optional.ofNullable(playlist)
                .map(p -> (JSONArray) p.get("items"));

        // Procesamos los items si existen,  agregamos el método ifPresentOrElse introducido en Java 9
        optionalItems.ifPresentOrElse(
                items -> {
                    // Código que procesa los items cuando existen
                    for (Object item : items) {
                        JSONObject songJSON = (JSONObject) item;
                        JSONObject trackJSON = (JSONObject) songJSON.get("track");
                        JSONArray artistsJSON = (JSONArray) trackJSON.get("artists");
                        JSONObject albumJSON = (JSONObject) trackJSON.get("album");

                        SpotifyArtist artist = extractArtistFromJson(artistsJSON);
                        Song song = getSong(trackJSON, albumJSON, artist);

                        spotifyPlayList.add(song);
                    }

                    // Logging de las canciones
                    for (Song song : spotifyPlayList) {
                        if(song != null)
                            LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                                    song.getSpotifyArtist().getName(), song.getAlbumName());
                    }
                },
                () -> LOGGER.warn("No se pudo procesar la playlist porque es null o no contiene items")
        );
    }

    //Creamos los siguientes dos métodos para hacer el código más limpio, más legible y más mantenible

    private SpotifyArtist extractArtistFromJson(JSONArray artistsJSON){
        SpotifyArtist artist = new SpotifyArtist();
        for (Object element : artistsJSON) {
            JSONObject artistJSON = (JSONObject) element;
            artist.setId(artistJSON.get("id").toString());
            artist.setName(artistJSON.get("name").toString());
        }
        return artist;
    }

    // Creamos los objetos de tipo Song según el patrón de diseño creacional Builder
    private Song getSong(JSONObject trackJSON, JSONObject albumJSON, SpotifyArtist artist){
        return new SongBuilder()
                .id(trackJSON.get("id").toString())
                .name(trackJSON.get("name").toString())
                .explicit(trackJSON.get("explicit").toString())
                .playable(trackJSON.get("is_playable").toString())
                .popularity(trackJSON.get("popularity").toString())
                .albumId(albumJSON.get("id").toString())
                .albumType(albumJSON.get("album_type").toString())
                .albumName(albumJSON.get("name").toString())
                .albumReleaseDate(albumJSON.get("release_date").toString())
                .albumTotalTracks(albumJSON.get("total_tracks").toString())
                .spotifyArtist(artist)
                .build();
    }
}
