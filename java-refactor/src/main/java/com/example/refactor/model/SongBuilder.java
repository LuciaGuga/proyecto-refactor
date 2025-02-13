package com.example.refactor.model;

/*
 * Esta clase nos permite aplicar el patrón de diseño creacional Builder, la justificación
 * para aplicar este diseño es la gran cantidad de atributos que tiene la clase Song
 */
public class SongBuilder {
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

    public SongBuilder id(String id){
        this.id = id;
        return this;
    }

    public SongBuilder name(String name){
        this.name = name;
        return this;
    }

    public SongBuilder explicit(String explicit){
        this.explicit = explicit;
        return this;
    }

    public SongBuilder playable(String playable){
        this.playable = playable;
        return this;
    }

    public SongBuilder popularity(String popularity){
        this.popularity = popularity;
        return this;
    }

    public SongBuilder albumId(String albumId){
        this.albumId = albumId;
        return this;
    }

    public SongBuilder albumType(String albumType){
        this.albumType = albumType;
        return this;
    }

    public SongBuilder albumName(String albumName){
        this.albumName = albumName;
        return this;
    }

    public SongBuilder albumReleaseDate(String albumReleaseDate){
        this.albumReleaseDate = albumReleaseDate;
        return this;
    }

    public SongBuilder albumTotalTracks(String albumTotalTracks){
        this.albumTotalTracks = albumTotalTracks;
        return this;
    }

    public SongBuilder spotifyArtist(SpotifyArtist spotifyArtist){
        this.spotifyArtist = spotifyArtist;
        return this;
    }

    //Creamos el metodo build() que instancia el objeto de tipo Song que tiene muchos parámetros
    public Song build(){
        return new Song(id, name, explicit, playable, popularity, albumId, albumType,
                albumName, albumReleaseDate, albumTotalTracks, spotifyArtist);
    }
}
