package com.example.books.ui.fragments.description;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentDescriptionBinding;
import com.example.books.ui.models.BooksModel;


public class DescriptionFragment extends BaseFragment<FragmentDescriptionBinding, DescriptionViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setupDescription() {
        BooksModel model = DescriptionFragmentArgs.fromBundle(getArguments()).getBooksDescription();
        binding.txtTitle.setText(model.getTitle());
        binding.txtDescription.setText(model.getDescription());
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(DescriptionViewModel.class);
    }

    @Override
    protected void setupListener() {
        setupDescription();
    }

    @Override
    protected void setupObserve() {

    }

    @Override
    protected void setupViews() {
    }


}