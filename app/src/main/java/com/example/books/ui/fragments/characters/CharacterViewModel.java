package com.example.books.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyResponse;

public class CharacterViewModel extends ViewModel {

    RickAndMortyRepository repository = new RickAndMortyRepository();

    public int chartersPage = 1;

    public  MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters(){
        return repository.fetchCharacters(chartersPage);
    }
}
