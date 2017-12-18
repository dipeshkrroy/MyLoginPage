package com.example.dipesh.myloginpage;

/**
 * Created by dipesh on 14-12-2017.
 */

public class Animes {
    private String animeId;
    private String animeName;
    private String animeyear;
    private String noofepisode;

    public Animes() {

    }

    public Animes(String animeId, String animeName, String animeyear, String noofepisode) {
        this.animeId = animeId;
        this.animeName = animeName;
        this.animeyear = animeyear;
        this.noofepisode = noofepisode;
    }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getAnimeyear() {
        return animeyear;
    }

    public void setAnimeyear(String animeyear) {
        this.animeyear = animeyear;
    }

    public String getNoofepisode() {
        return noofepisode;
    }

    public void setNoofepisode(String noofepisode) {
        this.noofepisode = noofepisode;
    }
}
