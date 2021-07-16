package com.example.books.ui.fragments.description;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.books.App;
import com.example.books.data.network.retrofit.RetrofitClient;
import com.example.books.models.RickAndMortyCharacter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionViewModel extends ViewModel {
    MutableLiveData<RickAndMortyCharacter> characterDescription = new MutableLiveData<>();
    void addDescription(int id){
        Call<RickAndMortyCharacter> call = App.charactersApiService.fetchCharacter(id);
        call.enqueue(new Callback<RickAndMortyCharacter>() {
            @Override
            public void onResponse(Call<RickAndMortyCharacter> call, Response<RickAndMortyCharacter> response) {
                RickAndMortyCharacter character = response.body();
                characterDescription.setValue(character);
            }

            @Override
            public void onFailure(Call<RickAndMortyCharacter> call, Throwable t) {

            }
        });
    }

}
