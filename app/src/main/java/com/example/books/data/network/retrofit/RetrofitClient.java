package com.example.books.data.network.retrofit;

import com.example.books.data.network.retrofit.apiservices.CharactersApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

     Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public  CharactersApiService provideCharacterApiService(){
        return retrofitClient.create(CharactersApiService.class);
    }
}
