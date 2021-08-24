package com.example.books.models.converters;

import android.webkit.WebStorage;

import androidx.room.TypeConverter;

import com.example.books.models.Origin;
import com.google.gson.Gson;

public class OriginConverter {

    @TypeConverter
    public String fromOrigin(Origin origin) {
        return new Gson().toJson(origin);
    }
    @TypeConverter
    public Origin toOrigin(String origin) {
        return new Gson().fromJson(origin, Origin.class);
    }

}
