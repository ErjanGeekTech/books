package com.example.books.data.network.retrofit;

import com.example.books.data.network.retrofit.apiservices.CharactersApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

     Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
             .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public  CharactersApiService provideCharacterApiService(){
        return retrofitClient.create(CharactersApiService.class);
    }
}
