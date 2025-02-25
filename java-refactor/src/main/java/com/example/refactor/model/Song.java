package com.example.refactor.model;

//Convertimos la clase SOng en un record al tratarse de datos inmutables y cuyos atributos no se modificarán
public record Song(String id, String name, String explicit, String playable, String popularity,
            String albumId, String albumType, String albumName, String albumReleaseDate,
            String albumTotalTracks, SpotifyArtist spotifyArtist){}