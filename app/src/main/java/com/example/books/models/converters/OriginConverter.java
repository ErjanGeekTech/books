package com.example.books.models.converters;

import android.webkit.WebStorage;

import androidx.room.TypeConverter;

public class OriginConverter {

    @TypeConverter
    public long dateToTimestamp(WebStorage.Origin origin) {

            return origin.getQuota();

    }

}
