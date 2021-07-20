package com.example.books.ui.fragments.episodes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentEpisodesBinding;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyResponse;
import com.example.books.ui.adapters.EpisodesAdapter;

import org.jetbrains.annotations.NotNull;

public class EpisodesFragment extends BaseFragment<FragmentEpisodesBinding, EpisodesViewModel> {

    EpisodesAdapter adapter = new EpisodesAdapter();
    LinearLayoutManager linearLayoutManager;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupRequests() {
        super.setupRequests();
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<RickAndMortyEpisodes>>() {
            @Override
            public void onChanged(RickAndMortyResponse<RickAndMortyEpisodes> rickAndMortyEpisodesRickAndMortyResponse) {
                binding.progressCircular.setVisibility(View.GONE);
                adapter.addList(rickAndMortyEpisodesRickAndMortyResponse.getResults());
            }
        });
    }

    @Override
    protected void setupViews() {
        super.setupViews();
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
                            viewModel.episodesPage++;
                            Log.e("anime", "aded");
                            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults()));
                            loading = true;
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void initialize() {
        super.initialize();
        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(EpisodesViewModel.class);
    }
}