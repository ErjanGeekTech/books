package com.example.books.data.network.retrofit.apiservices;

import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharactersApiService {
    @GET("api/character/")
    Call<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters(
            @Query("page") int page
    );
    @GET("api/character/{id}")
    Call<RickAndMortyCharacter> fetchCharacter(
            @Path("id") int id);

    @GET("api/location")
    Call<RickAndMortyResponse<RickAndMortyLocation>> fetchLocations(
            @Query("page") int page
    );

    @GET("api/episode")
    Call<RickAndMortyResponse<RickAndMortyEpisodes>> fetchEpisodes(
            @Query("page") int page
    );
}
