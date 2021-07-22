package com.example.books.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
}
