package com.example.books.ui.description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.adapters.BooksAdapter;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.databinding.FragmentDescriptionBinding;
import com.example.books.models.BooksModel;
import com.example.books.ui.books.BooksViewModel;


public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;

    BooksViewModel viewModel;

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
        viewModel = new ViewModelProvider(requireActivity()).get(BooksViewModel.class);
        getData();
    }

    private void getData() {
        viewModel.getModel.observe(getViewLifecycleOwner(), new Observer<BooksModel>() {
            @Override
            public void onChanged(BooksModel model) {
                binding.txtTitle.setText(model.getTitle());
                binding.txtDescription.setText(model.getDescription());
            }
        });
    }
}