package com.example.books.di;


import android.app.Application;
import android.content.Context;

import androidx.annotation.BoolRes;

import com.example.books.data.db.AppDatabase;
import com.example.books.data.db.RoomClient;
import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.network.retrofit.RetrofitClient;
import com.example.books.data.network.retrofit.apiservices.CharactersApiService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {


    @Singleton
    @Provides
    CharactersApiService provideCharacterApiService(){
        return new RetrofitClient().provideCharacterApiService();
    }





}
