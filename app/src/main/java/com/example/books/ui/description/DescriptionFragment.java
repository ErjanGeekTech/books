package com.example.books.ui.description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.adapters.BooksAdapter;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.databinding.FragmentDescriptionBinding;
import com.example.books.models.BooksModel;


public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupViews(inflater, container, false);
        return binding.getRoot();
    }


    private void setupViews(LayoutInflater inflater, ViewGroup container, boolean b) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        if (getArguments() != null) {
            BooksModel model = (BooksModel) getArguments().getSerializable("model");
            binding.txtTitle.setText(model.getTitle());
            binding.txtDescription.setText(model.getDescription());
        }
    }
}