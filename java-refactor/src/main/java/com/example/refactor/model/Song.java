package com.example.refactor.model;

public record Song(String id, String name, String explicit, String playable, String popularity,
            String albumId, String albumType, String albumName, String albumReleaseDate,
            String albumTotalTracks, SpotifyArtist spotifyArtist){}