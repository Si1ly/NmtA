package com.example.nmta.data.VideoItem;

public class VideoItem {
    private int id;
    private String uri;
    private String listUser;

    public VideoItem(String uri, String listUser) {
        this.uri = uri;
        this.listUser = listUser;
    }

    public VideoItem(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getListUser() {
        return listUser;
    }

    public void setListUser(String listUser) {
        this.listUser = listUser;
    }
}
