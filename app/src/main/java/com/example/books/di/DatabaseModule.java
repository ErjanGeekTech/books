package com.example.books.di;

import android.content.Context;

import com.example.books.data.db.AppDatabase;
import com.example.books.data.db.RoomClient;
import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.db.daos.EpisodeDao;
import com.example.books.data.db.daos.LocationDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    RoomClient roomClient = new RoomClient();

    @Singleton
    @Provides
    AppDatabase provideDaoDB(@ApplicationContext Context context){
        return roomClient.provideRoom(context);
    }

    @Singleton
    @Provides
    CharacterDao provideCharacterDao(AppDatabase appDatabase){
        return new RoomClient().provideCharacterDao(appDatabase);
    }
    @Singleton
    @Provides
    LocationDao provideLocationDao(AppDatabase appDatabase){
        return new RoomClient().provideLocationDao(appDatabase);
    }
    @Singleton
    @Provides
    EpisodeDao provideEpisodeDao(AppDatabase appDatabase){
        return new RoomClient().provideEpisodesDao(appDatabase);
    }

}
