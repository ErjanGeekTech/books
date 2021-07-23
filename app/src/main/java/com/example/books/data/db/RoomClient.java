package com.example.books.data.db;


import android.content.Context;

import androidx.room.Room;

import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.db.daos.EpisodeDao;
import com.example.books.data.db.daos.LocationDao;

public class RoomClient {

    public AppDatabase provideRoom(Context context){
        return Room
                .databaseBuilder(context, AppDatabase.class, "rickAndMorty.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public CharacterDao provideCharacterDao(AppDatabase appDatabase){
        return appDatabase.characterDao();
    }
    public LocationDao provideLocationDao(AppDatabase appDatabase){
        return appDatabase.locationDao();
    }
    public EpisodeDao provideEpisodesDao(AppDatabase appDatabase){
        return appDatabase.episodeDao();
    }

}
