package com.example.books.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.books.models.converters.ResidentsConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
@Entity
public class RickAndMortyLocation implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;
    @SerializedName("dimension")
    public String dimension;

    @SerializedName("residents")
    @TypeConverters({ResidentsConverter.class})
    private List<String> residents;

    @SerializedName("url")
    public String url;

    @SerializedName("created")
    public String created;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
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

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }
}
