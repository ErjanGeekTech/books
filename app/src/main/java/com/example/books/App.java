package com.example.books;

import android.app.Application;

import com.example.books.data.network.retrofit.RetrofitClient;
import com.example.books.data.network.retrofit.apiservices.CharactersApiService;

public class App extends Application {

    public static CharactersApiService charactersApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        charactersApiService = new RetrofitClient().provideCharacterApiService();
    }
}
