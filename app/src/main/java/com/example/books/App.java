package com.example.books;

import android.app.Application;

import com.example.books.data.db.RoomClient;
import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.db.daos.EpisodeDao;
import com.example.books.data.db.daos.LocationDao;
import com.example.books.data.network.retrofit.RetrofitClient;
import com.example.books.data.network.retrofit.apiservices.CharactersApiService;

public class App extends Application {

    public static CharactersApiService charactersApiService;
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodeDao episodeDao;

    @Override
    public void onCreate() {
        super.onCreate();
        charactersApiService = new RetrofitClient().provideCharacterApiService();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideRoom(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideRoom(this));
        episodeDao = roomClient.provideEpisodesDao(roomClient.provideRoom(this));
    }
}
