package com.example.books.ui.fragments.description;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentDescriptionBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DescriptionFragment extends BaseFragment<FragmentDescriptionBinding, DescriptionViewModel> {

    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setupId() {
         id = DescriptionFragmentArgs.fromBundle(getArguments()).getGetIdDescription();
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
        viewModel.addDescription(id).observe(getViewLifecycleOwner(), rickAndMortyCharacter -> {
            Glide
                    .with(binding.imageDescription)
                    .load(rickAndMortyCharacter.image)
                    .into(binding.imageDescription);
            binding.textNameDescription.setText(rickAndMortyCharacter.getName());
        });
    }
}