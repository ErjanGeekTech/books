package com.example.books.ui.fragments.locations;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationsViewModel extends ViewModel {

    RickAndMortyRepository repository;

    @Inject
    public LocationsViewModel(RickAndMortyRepository repository) {
        this.repository = repository;
    }

    public int locationPage = 1;

    public MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> fetchLocations(){
        return repository.fetchLocations(locationPage);
    }

    public ArrayList<RickAndMortyLocation> getLocations(){
        return repository.getLocations();
    }
}
