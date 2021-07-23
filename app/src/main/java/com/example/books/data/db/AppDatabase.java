package com.example.books.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.db.daos.EpisodeDao;
import com.example.books.data.db.daos.LocationDao;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyLocation;

@Database(entities = {RickAndMortyCharacter.class, RickAndMortyLocation.class, RickAndMortyEpisodes.class}, version = 3)
abstract class AppDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao ();
    public abstract LocationDao locationDao ();
    public abstract EpisodeDao episodeDao ();
}
