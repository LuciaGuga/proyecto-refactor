package com.example.refactor.model;

public class Song {

    private String id;
    private String name;
    private String explicit;
    private String playable;
    private String popularity;

    private String albumId;
    private String albumType;
    private String albumName;
    private String albumReleaseDate;
    private String albumTotalTracks;

    public SpotifyArtist spotifyArtist;

    /* Con el patrón de diseño creacional Builder implementado con la clase
     * Song Builder ya no es necesario métodos setter pero sí un constructor
     * con todos los parámetros y seguiremos necesitando los getters
     */

    public Song(String id, String name, String explicit, String playable,
                String popularity, String albumId, String albumType,
                String albumName, String albumReleaseDate, String albumTotalTracks,
                SpotifyArtist spotifyArtist) {
        this.id = id;
        this.name = name;
        this.explicit = explicit;
        this.playable = playable;
        this.popularity = popularity;
        this.albumId = albumId;
        this.albumType = albumType;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
        this.albumTotalTracks = albumTotalTracks;
        this.spotifyArtist = spotifyArtist;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExplicit() {
        return explicit;
    }

    public String getPlayable() {
        return playable;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getAlbumType() {
        return albumType;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public String getAlbumTotalTracks() {
        return albumTotalTracks;
    }

    public SpotifyArtist getSpotifyArtist() {
        return spotifyArtist;
    }

}
