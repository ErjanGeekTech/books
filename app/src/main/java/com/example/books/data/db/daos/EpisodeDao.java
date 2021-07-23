package com.example.books.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.books.models.RickAndMortyEpisodes;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {

    @Query("SELECT * FROM rickandmortyepisodes")
    List<RickAndMortyEpisodes> getAllEpisode();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEpisodes(ArrayList<RickAndMortyEpisodes> episodes);

}
