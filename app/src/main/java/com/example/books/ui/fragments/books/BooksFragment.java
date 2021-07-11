package com.example.books.ui.fragments.books;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.base.BaseFragment;
import com.example.books.ui.adapters.BooksAdapter;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.ui.interfaces.OnItemClick;
import com.example.books.ui.models.BooksModel;


public class BooksFragment extends BaseFragment<FragmentBooksBinding, BooksViewModel> {

    BooksAdapter adapter = new BooksAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
        setupObserve();
        setupListener();
        onClick();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(BooksViewModel.class);
    }

    protected void setupObserve() {
        viewModel.getAll.observe(getViewLifecycleOwner(), booksModels -> {
            binding.bntFill.setVisibility(View.GONE);
            adapter.addList(booksModels);
        });
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



    public void onClick() {
        adapter.setOnItemClick((model, v) -> {
            Navigation.findNavController(v).navigate(BooksFragmentDirections.actionBooksFragmentToDescriptionFragment(model).setBooksDescription(model));
        });
    }
}