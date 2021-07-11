package com.example.books.ui.fragments.description;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentDescriptionBinding;
import com.example.books.ui.models.BooksModel;
import com.example.books.ui.fragments.books.BooksViewModel;


public class DescriptionFragment extends BaseFragment<FragmentDescriptionBinding, DescriptionViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BooksModel model = DescriptionFragmentArgs.fromBundle(getArguments()).getBooksDescription();
        binding.txtTitle.setText(model.getTitle());
        binding.txtDescription.setText(model.getDescription());
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(DescriptionViewModel.class);
    }




}