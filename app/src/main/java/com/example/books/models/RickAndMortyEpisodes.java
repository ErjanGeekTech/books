package com.example.books.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.books.models.converters.ResidentsConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity
public class RickAndMortyEpisodes {
    @PrimaryKey
    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String name;

    @SerializedName("air_date")
    public String airDate;

    @SerializedName("episode")
    public String episode;


    @SerializedName("url")
    public String url;

    @SerializedName("created")
    public String created;

    @SerializedName("characters")
    @TypeConverters(ResidentsConverter.class)
    private List<String> characters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }
}
