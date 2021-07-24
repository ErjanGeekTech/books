package com.example.books.models.converters;


import androidx.room.TypeConverter;

import com.example.books.models.Location;
import com.google.gson.Gson;

public class LocationConverter {
    @TypeConverter
    public String fromLocation(Location location){
        return new Gson().toJson(location);
    }
    @TypeConverter
    public Location toLocation(String location){
        return new Gson().fromJson(location, Location.class);
    }

}
