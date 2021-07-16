package com.example.books.ui.fragments.books;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentCharacterBinding;
import com.example.books.ui.adapters.CharactersAdapter;
import com.example.books.ui.interfaces.OnItemClick;


public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

  public CharactersAdapter adapter = new CharactersAdapter(CharactersAdapter.diffCallback);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
    }

    @Override
    protected void setupListener() {
        setupClickBooks();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> {
            adapter.submitList(rickAndMortyCharacterRickAndMortyResponse.getResults());
        });
    }

    @Override
    protected void setupViews() {
        setupRecycler();
    }

    private void setupRecycler() {
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);
    }


    private void setupClickBooks() {
        adapter.setItemClick(new OnItemClick() {
            @Override
            public void onClick(int id, View v) {
                Navigation.findNavController(v).navigate(CharacterFragmentDirections.actionCharacterFragmentToDescriptionFragment(id).setGetItemId(id));
            }

            @Override
            public void onLongClick(int image, View v) {

            }
        });
    }


}