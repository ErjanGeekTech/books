package com.example.books.data.network.retrofit.apiservices;

import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharactersApiService {
    @GET("api/character")
    Call<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters();
    @GET("api/character/{id}")
    Call<RickAndMortyCharacter> fetchCharacter(
            @Path("id") int id);

}
