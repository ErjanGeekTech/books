package com.example.books.ui.fragments.books;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.App;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {
    MutableLiveData <RickAndMortyResponse<RickAndMortyCharacter>> data = new MutableLiveData<>();

    public  void fetchCharacters(){
        App.charactersApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<RickAndMortyCharacter>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<RickAndMortyCharacter>> call, Response<RickAndMortyResponse<RickAndMortyCharacter>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<RickAndMortyCharacter>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }
}
