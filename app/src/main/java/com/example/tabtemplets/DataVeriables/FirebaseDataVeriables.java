package com.example.tabtemplets.DataVeriables;

public class FirebaseDataVeriables {
    String wallpaperID, URL, musicname, musicURL, musicID;

    public FirebaseDataVeriables() {
    }

    public FirebaseDataVeriables(String wallpaperID, String URL) {
        this.wallpaperID = wallpaperID;
        this.URL = URL;
    }

    public FirebaseDataVeriables(String musicname, String musicURL, String musicID) {
        this.musicname = musicname;
        this.musicURL = musicURL;
        this.musicID = musicID;
    }

    public FirebaseDataVeriables(String wallpaperID, String URL, String musicname, String musicURL) {
        this.wallpaperID = wallpaperID;
        this.URL = URL;
        this.musicname = musicname;
        this.musicURL = musicURL;
    }

    public String getWallpaperID() {
        return wallpaperID;
    }

    public void setWallpaperID(String wallpaperID) {
        this.wallpaperID = wallpaperID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String getMusicID() {
        return musicID;
    }

    public void setMusicID(String musicID) {
        this.musicID = musicID;
    }
}