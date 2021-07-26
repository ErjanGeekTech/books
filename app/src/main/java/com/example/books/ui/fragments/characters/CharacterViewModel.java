package com.example.books.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends ViewModel {

    RickAndMortyRepository repository;

    @Inject
    public CharacterViewModel(RickAndMortyRepository repository) {
        this.repository = repository;
    }

    public int chartersPage = 1;

    public  MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>> fetchCharacters(){
        return repository.fetchCharacters(chartersPage);
    }

    public ArrayList<RickAndMortyCharacter> getCharacters(){
        return repository.getCharacters();
    }
}
