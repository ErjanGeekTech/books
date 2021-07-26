package com.example.books.ui.fragments.description;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyCharacter;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DescriptionViewModel extends ViewModel {

    RickAndMortyRepository repository;


    @Inject
    public DescriptionViewModel(RickAndMortyRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<RickAndMortyCharacter> addDescription(int id){
       return repository.addDescription(id);
    }
}
