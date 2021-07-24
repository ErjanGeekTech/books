package com.example.books.models.converters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResidentsConverter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public String fromList(List<String> residents) {
        return residents.stream().collect(Collectors.joining(","));
    }

    @TypeConverter
    public List<String> toList(String resident) {
        return Arrays.asList(resident.split(","));
    }

}
