package com.example.books.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyLocation;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM rickandmortycharacter")
    List<RickAndMortyCharacter> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<RickAndMortyCharacter> characters);



    @Query("SELECT * FROM rickandmortylocation")
    List<RickAndMortyLocation> getAllLocation();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLocation(ArrayList<RickAndMortyLocation> locations);

    @Query("SELECT * FROM rickandmortyepisodes")
    List<RickAndMortyEpisodes> getAllEpisode();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEpisodes(ArrayList<RickAndMortyEpisodes> episodes);

}
