package com.example.books.ui.fragments.characters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentCharacterBinding;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.models.RickAndMortyResponse;
import com.example.books.ui.adapters.CharactersAdapter;
import com.example.books.ui.interfaces.OnItemClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

    private CharactersAdapter adapter = new CharactersAdapter();
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager linearLayoutManager;
    private boolean progressbarOne = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<RickAndMortyCharacter>>() {
            @Override
            public void onChanged(RickAndMortyResponse<RickAndMortyCharacter> rickAndMortyCharacterRickAndMortyResponse) {
                binding.progressCircular.setVisibility(View.INVISIBLE);
                if (rickAndMortyCharacterRickAndMortyResponse != null) {
                    adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults());
                }
            }
        });
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
    protected void setupViews() {
        setupRecycler();
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rv.setLayoutManager(linearLayoutManager);
        binding.rv.setAdapter(adapter);

        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            viewModel.chartersPage++;
                            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> {
                                if (rickAndMortyCharacterRickAndMortyResponse != null) {
                                    binding.progressBar.setVisibility(View.INVISIBLE);
                                    adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults());
                                } else {
                                    progressbarOne = false;
                                    binding.rv.setPadding(0, 0, 0, 0);
                                    binding.progressBar.setVisibility(View.GONE);
                                }
                            });
                            if (progressbarOne) {
                                binding.progressBar.setVisibility(View.VISIBLE);
                            }
                            loading = true;
                        }
                    }
                }
            }
        });
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