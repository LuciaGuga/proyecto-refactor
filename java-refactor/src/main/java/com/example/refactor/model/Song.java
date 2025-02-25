package com.example.refactor.model;

//Implementación de RECORDS al tratarse de datos inmutables y cuyos atributos no se modificarán
public record Song(String id, String name, String explicit, String playable, String popularity,
            String albumId, String albumType, String albumName, String albumReleaseDate,
            String albumTotalTracks, SpotifyArtist spotifyArtist){}