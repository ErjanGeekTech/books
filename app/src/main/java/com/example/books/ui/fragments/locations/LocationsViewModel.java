package com.example.books.ui.fragments.locations;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;

public class LocationsViewModel extends ViewModel {

    RickAndMortyRepository repository = new RickAndMortyRepository();

    public int locationPage = 1;

    public MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>> fetchLocations(){
        return repository.fetchLocations(locationPage);
    }

    public ArrayList<RickAndMortyLocation> getLocations(){
        return repository.getLocations();
    }
}
