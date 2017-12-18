package com.example.dipesh.myloginpage;

/**
 * Created by dipesh on 16-12-2017.
 */

public class EpisodesClass {
    String id, title,url;

    public EpisodesClass() {
    }

    public EpisodesClass(String title, String url,String id) {
        this.title = title;
        this.url = url;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
