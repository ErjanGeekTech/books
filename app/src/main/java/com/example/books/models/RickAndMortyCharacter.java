package com.example.books.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.books.models.converters.LocationConverter;
import com.example.books.models.converters.OriginConverter;
import com.example.books.models.converters.ResidentsConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class RickAndMortyCharacter implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

    @SerializedName("image")
    public String image;

    @SerializedName("origin")
    @TypeConverters(OriginConverter.class)
    private Origin origin;

    @SerializedName("location")
    @TypeConverters(LocationConverter.class)
    private Location location;

    @SerializedName("episode")
    @TypeConverters(ResidentsConverter.class)
    private List<String> episode;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }
}
