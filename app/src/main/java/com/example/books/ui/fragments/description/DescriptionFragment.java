package com.example.books.ui.fragments.description;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.books.base.BaseFragment;
import com.example.books.data.network.retrofit.RetrofitClient;
import com.example.books.databinding.FragmentDescriptionBinding;
import com.example.books.models.RickAndMortyCharacter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DescriptionFragment extends BaseFragment<FragmentDescriptionBinding, DescriptionViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setupId() {
        int id = DescriptionFragmentArgs.fromBundle(getArguments()).getGetItemId();
        Log.e("tag", String.valueOf(id));
        setupService(id);
    }

    private void setupService(int id) {
        Call<RickAndMortyCharacter> call = RetrofitClient.provideCharacterApiService().fetchCharacter(id);
        call.enqueue(new Callback<RickAndMortyCharacter>() {
            @Override
            public void onResponse(Call<RickAndMortyCharacter> call, Response<RickAndMortyCharacter> response) {
                RickAndMortyCharacter character = response.body();
                Glide
                        .with(binding.imageDescription)
                        .load(character.image)
                        .into(binding.imageDescription);
                binding.textNameDescription.setText(character.getName());
            }

            @Override
            public void onFailure(Call<RickAndMortyCharacter> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(DescriptionViewModel.class);
    }

    @Override
    protected void setupListener() {
        setupId();
    }




}