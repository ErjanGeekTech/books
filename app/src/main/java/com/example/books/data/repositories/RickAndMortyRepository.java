package com.example.books.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.books.data.db.daos.CharacterDao;
import com.example.books.data.db.daos.EpisodeDao;
import com.example.books.data.db.daos.LocationDao;
import com.example.books.data.network.retrofit.apiservices.CharactersApiService;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RickAndMortyRepository {

    CharactersApiService charactersApiService;

    CharacterDao characterDao;
    LocationDao locationDao;
    EpisodeDao episodeDao;

    @Inject
    public RickAndMortyRepository(CharactersApiService charactersApiService, CharacterDao characterDao, LocationDao locationDao, EpisodeDao episodeDao) {
        this.charactersApiService = charactersApiService;
        this.characterDao = characterDao;
        this.locationDao = locationDao;
        this.episodeDao = episodeDao;
    }


    public MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters(int page) {
        MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> data = new MutableLiveData<>();
        charactersApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyCharacter>>() {
            @Override
            public void onResponse(@NotNull Call<RickAndMortyResponse<RickAndMortyCharacter>> call, @NotNull Response<RickAndMortyResponse<RickAndMortyCharacter>> response) {
                if (response.body() != null) {
                    characterDao.insertAll(response.body().getResults());
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RickAndMortyResponse<RickAndMortyCharacter>> call, @NotNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public ArrayList<RickAndMortyCharacter> getCharacters() {
        return new ArrayList<>(characterDao.getAll());
    }

    public MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> fetchLocations(int page) {
        MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> location = new MutableLiveData<>();
        charactersApiService.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyLocation>>() {
            @Override
            public void onResponse(@NotNull Call<RickAndMortyResponse<RickAndMortyLocation>> call, @NotNull Response<RickAndMortyResponse<RickAndMortyLocation>> response) {
                if (response.body() != null) {
                    locationDao.insertAllLocation(response.body().getResults());
                }
                location.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RickAndMortyResponse<RickAndMortyLocation>> call, @NotNull Throwable t) {
                location.setValue(null);
            }
        });
        return location;
    }

    public ArrayList<RickAndMortyLocation> getLocations() {
        return new ArrayList<>(locationDao.getAllLocation());

    }

    public MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>> fetchEpisodes(int page) {
        MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>> episodes = new MutableLiveData<>();
        charactersApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<RickAndMortyEpisodes>>() {
            @Override
            public void onResponse(@NotNull Call<RickAndMortyResponse<RickAndMortyEpisodes>> call, @NotNull Response<RickAndMortyResponse<RickAndMortyEpisodes>> response) {
                if (response.body() != null) {
                    episodeDao.insertAllEpisodes(response.body().getResults());
                }
                episodes.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RickAndMortyResponse<RickAndMortyEpisodes>> call, @NotNull Throwable t) {
                episodes.setValue(null);
            }
        });
        return episodes;
    }

    public ArrayList<RickAndMortyEpisodes> getEpisodes() {
        return new ArrayList<>(episodeDao.getAllEpisode());

    }


    public MutableLiveData<RickAndMortyCharacter> addDescription(int id) {
        MutableLiveData<RickAndMortyCharacter> characterDescription = new MutableLiveData<>();
        charactersApiService.fetchCharacter(id).enqueue(new Callback<RickAndMortyCharacter>() {
            @Override
            public void onResponse(Call<RickAndMortyCharacter> call, Response<RickAndMortyCharacter> response) {
                RickAndMortyCharacter character = response.body();
                characterDescription.setValue(character);
            }

            @Override
            public void onFailure(Call<RickAndMortyCharacter> call, Throwable t) {
                characterDescription.setValue(null);

            }
        });
        return characterDescription;
    }


}
