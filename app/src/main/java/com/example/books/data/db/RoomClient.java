package com.example.books.data.db;


import android.content.Context;

import androidx.room.Room;

import com.example.books.data.db.daos.CharacterDao;

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

}
