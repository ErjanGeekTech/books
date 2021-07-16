package com.example.books.ui.fragments.description;

import android.os.Bundle;

import androidx.lifecycle.Observer;
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
        viewModel.addDescription(id);

    }


    @Override
    protected void initialize() {
        setupViewModel();
        setupId();
    }



    private void setupViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(DescriptionViewModel.class);

    }

    @Override
    protected void setupObserve() {
        super.setupObserve();
        viewModel.characterDescription.observe(getViewLifecycleOwner(), rickAndMortyCharacter -> {
            Glide
                    .with(binding.imageDescription)
                    .load(rickAndMortyCharacter.image)
                    .into(binding.imageDescription);
            binding.textNameDescription.setText(rickAndMortyCharacter.getName());
        });
    }
}