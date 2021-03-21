package com.uts_33459;

public class MainModel {
    Integer albumCover;
    String songTitle;
    String songSinger;

    public MainModel (Integer albumCover, String songTitle, String songSinger) {
        this.albumCover = albumCover;
        this.songTitle = songTitle;
        this.songSinger = songSinger;
    }

    public Integer getAlbumCover() {
        return albumCover;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongSinger() {
        return  songSinger;
    }
}
