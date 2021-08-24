package com.example.books.ui.fragments.episodes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentEpisodesBinding;
import com.example.books.models.RickAndMortyEpisodes;
import com.example.books.models.RickAndMortyResponse;
import com.example.books.ui.adapters.EpisodesAdapter;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodesFragment extends BaseFragment<FragmentEpisodesBinding, EpisodesViewModel> {

    EpisodesAdapter adapter = new EpisodesAdapter();
    LinearLayoutManager linearLayoutManager;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean progressBarOne = true;


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
        fetchEpisodes();
    }

    private void fetchEpisodes() {
        if (isNetworkAvailable()){
            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<RickAndMortyEpisodes>>() {
                @Override
                public void onChanged(RickAndMortyResponse<RickAndMortyEpisodes> rickAndMortyEpisodesRickAndMortyResponse) {
                    binding.progressCircular.setVisibility(View.GONE);
                    if (rickAndMortyEpisodesRickAndMortyResponse != null) {
                        adapter.addList(rickAndMortyEpisodesRickAndMortyResponse.getResults());
                    }
                }
            });
        }else {
            binding.progressCircular.setVisibility(View.GONE);
            adapter.addList(viewModel.getEpisodes());
        }
    }


    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return  netInfo != null && netInfo.isConnected();
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
                            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> {
                                if (rickAndMortyCharacterRickAndMortyResponse != null) {
                                    binding.progressBar.setVisibility(View.INVISIBLE);
                                    adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults());
                                } else {
                                    progressBarOne = false;
                                    binding.rv.setPadding(0, 0, 0, 0);
                                    binding.progressBar.setVisibility(View.GONE);
                                }
                            });
                            if (progressBarOne) {
                                binding.progressBar.setVisibility(View.VISIBLE);
                            }
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