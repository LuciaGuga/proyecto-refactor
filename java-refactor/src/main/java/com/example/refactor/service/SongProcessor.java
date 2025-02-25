package com.example.refactor.service;

import com.example.refactor.model.Song;
import com.example.refactor.model.SpotifyArtist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Optional;

public class SongProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongProcessor.class);

    public void processSongs() {
        //Usamos var para implementar inferencia de tipos
        final var playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        final var inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        final var playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        final var spotifyPlayList = new LinkedList<Song>();

        // Usamos Optional<JSONArray> y pattern matching para instanceof
        Optional<JSONArray> optionalItems = Optional.ofNullable(playlist)
                .map(p -> {
                    if(p.get("items") instanceof JSONArray jsonArray) {
                        return jsonArray;
                    }
                    return null;
                    });

        // Usamos ifPresentOrElse introducido en Java 9
        optionalItems.ifPresentOrElse(
                items -> processItems(items, spotifyPlayList), //creamos el método ProcessItems
                () -> LOGGER.warn("No se pudo procesar la playlist porque es null o no contiene items")
        );
    }


    //Creamos los siguientes tres métodos para hacer el código más limpio, más legible y más mantenible

    private void processItems(JSONArray items, LinkedList<Song> spotifyPlayList) {
        {
            for (Object item : items) {
                //Usamos var para implementar inferencia de tipos
                var songJSON = (JSONObject) item;
                var trackJSON = (JSONObject) songJSON.get("track");
                var artistsJSON = (JSONArray) trackJSON.get("artists");
                var albumJSON = (JSONObject) trackJSON.get("album");

                var artist = extractArtistFromJson(artistsJSON);
                var song = getSong(trackJSON, albumJSON, artist);

                spotifyPlayList.add(song);
            }

            // Logging de las canciones
            for (Song song : spotifyPlayList) {
                if(song != null)
                    LOGGER.info(" - {} - {} - {} - {}", song.id(), song.name(),
                            song.spotifyArtist().getName(), song.albumName());
            }
        }
    }

    private SpotifyArtist extractArtistFromJson(JSONArray artistsJSON){
        var artist = new SpotifyArtist();
        for (Object element : artistsJSON) {
            var artistJSON = (JSONObject) element;
            artist.setId(artistJSON.get("id").toString());
            artist.setName(artistJSON.get("name").toString());
        }
        return artist;
    }

    private Song getSong(JSONObject trackJSON, JSONObject albumJSON, SpotifyArtist artist){

        return new Song(trackJSON.get("id").toString(), trackJSON.get("name").toString(),
                trackJSON.get("explicit").toString(), trackJSON.get("is_playable").toString(),
                trackJSON.get("popularity").toString(), albumJSON.get("id").toString(),
                albumJSON.get("album_type").toString(), albumJSON.get("name").toString(),
                albumJSON.get("release_date").toString(), albumJSON.get("total_tracks").toString(),
                artist);
    }
}
