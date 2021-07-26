package com.example.books.ui.fragments.episodes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodesViewModel extends ViewModel {

    RickAndMortyRepository repository;

    @Inject
    public EpisodesViewModel(RickAndMortyRepository repository) {
        this.repository = repository;
    }

    int episodesPage = 1;

    public MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>> fetchEpisodes(){
        return repository.fetchEpisodes(episodesPage);
    }

    public ArrayList<RickAndMortyEpisodes> getEpisodes(){
        return repository.getEpisodes();
    }

}
