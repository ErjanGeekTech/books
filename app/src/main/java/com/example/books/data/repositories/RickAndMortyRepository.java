package com.example.books.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.books.App;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RickAndMortyRepository {


    public MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters(int page) {
        MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> data = new MutableLiveData<>();
        App.charactersApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyCharacter>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<RickAndMortyCharacter>> call, Response<RickAndMortyResponse<RickAndMortyCharacter>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<RickAndMortyCharacter>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public ArrayList<RickAndMortyCharacter> getCharacters(){
        ArrayList list = new ArrayList();
        list.addAll(App.characterDao.getAll());
        return list;

    }

    public MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> fetchLocations(int page){
        MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> location = new MutableLiveData<>();
        App.charactersApiService.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyLocation>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<RickAndMortyLocation>> call, Response<RickAndMortyResponse<RickAndMortyLocation>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAllLocation(response.body().getResults());
                }
                location.setValue(response.body());

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<RickAndMortyLocation>> call, Throwable t) {
                location.setValue(null);
            }
        });
        return location;
    }

    public ArrayList<RickAndMortyLocation> getLocations(){
        ArrayList list = new ArrayList();
        list.addAll(App.characterDao.getAllLocation());
        return list;

    }

    public MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>> fetchEpisodes(int page){
        MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>> episodes = new MutableLiveData<>();
        App.charactersApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyEpisodes>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<RickAndMortyEpisodes>> call, Response<RickAndMortyResponse<RickAndMortyEpisodes>> response) {
                if (response.body() != null){
                    App.characterDao.insertAllEpisodes(response.body().getResults());
                }
                episodes.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<RickAndMortyEpisodes>> call, Throwable t) {
                episodes.setValue(null);
            }
        });
        return episodes;
    }

    public ArrayList<RickAndMortyEpisodes>getEpisodes(){
        ArrayList list = new ArrayList();
        list.addAll(App.characterDao.getAllEpisode());
        return list;

    }

}
