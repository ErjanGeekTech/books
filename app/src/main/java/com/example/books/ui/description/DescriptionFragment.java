package com.example.books.ui.description;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.adapters.BooksAdapter;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.databinding.FragmentDescriptionBinding;


public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        getData();
        return binding.getRoot();
    }

    private void getData() {
        if (getArguments() != null){
            int position = getArguments().getInt("position");
            String title = BooksAdapter.list.get(position).getTitle();
            String description = BooksAdapter.list.get(position).getDescription();
            binding.txtTitle.setText(title);
            binding.txtDescription.setText(description);
        }
    }
}