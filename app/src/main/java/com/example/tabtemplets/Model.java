package com.example.tabtemplets;

public class Model {
    String URL, musicname, musicURL;

    public Model() {
    }

    public Model(String URL, String musicname, String musicURL) {
        this.URL = URL;
        this.musicname = musicname;
        this.musicURL = musicURL;
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
}