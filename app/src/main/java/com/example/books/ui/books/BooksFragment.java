package com.example.books.ui.books;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.books.R;
import com.example.books.adapters.BooksAdapter;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.interfaces.OnItemClick;
import com.example.books.models.BooksModel;

import java.util.ArrayList;
import java.util.List;


public class BooksFragment extends Fragment {

    FragmentBooksBinding binding;
    BooksViewModel viewModel;
    BooksAdapter adapter = new BooksAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupViews(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupRecycler();
        setupListener();
        updateObserve();
        onClick();
    }

    private void setupViews(LayoutInflater inflater, ViewGroup container, boolean b) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
    }

    private void setupRecycler() {
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);
    }


    private void setupListener() {
        binding.bntFill.setOnClickListener(v -> {
            binding.bntFill.setVisibility(View.GONE);
            viewModel.get();
        });
    }


    private void updateObserve() {
        viewModel.getAll.observe(getViewLifecycleOwner(), booksModels -> {
            binding.bntFill.setVisibility(View.GONE);
            adapter.addList(booksModels);
        });
    }

    private void initialize() {
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
    }

    public void onClick() {
        adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onClick(BooksModel model) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", model);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_booksFragment_to_descriptionFragment, bundle);
            }
        });
    }
}