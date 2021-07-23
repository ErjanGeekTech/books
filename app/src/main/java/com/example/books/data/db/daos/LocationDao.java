package com.example.books.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.books.models.RickAndMortyLocation;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface LocationDao {

    @Query("SELECT * FROM rickandmortylocation")
    List<RickAndMortyLocation> getAllLocation();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLocation(ArrayList<RickAndMortyLocation> locations);

}
