package com.example.books.ui.fragments.locations;

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
import com.example.books.data.repositories.RickAndMortyRepository;
import com.example.books.databinding.FragmentLocationsBinding;
import com.example.books.models.RickAndMortyLocation;
import com.example.books.models.RickAndMortyResponse;
import com.example.books.ui.adapters.LocationsAdapter;

import org.jetbrains.annotations.NotNull;

public class LocationsFragment extends BaseFragment<FragmentLocationsBinding, LocationsViewModel> {

    LocationsAdapter adapter = new LocationsAdapter();
    LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private boolean progressBarOne = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    RickAndMortyRepository repository = new RickAndMortyRepository();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
                            viewModel.locationPage++;

                            viewModel.fetchLocations().observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> {
                                if (rickAndMortyCharacterRickAndMortyResponse != null) {
                                    binding.progressBar.setVisibility(View.INVISIBLE);
                                    adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults());
                                }else {
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
    protected void setupRequests() {
        super.setupRequests();
        viewModel.fetchLocations().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<RickAndMortyLocation>>() {
            @Override
            public void onChanged(RickAndMortyResponse<RickAndMortyLocation> rickAndMortyLocationRickAndMortyResponse) {
                binding.progressCircular.setVisibility(View.GONE);
                if(rickAndMortyLocationRickAndMortyResponse != null) {
                    adapter.addList(rickAndMortyLocationRickAndMortyResponse.getResults());
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
        viewModel = new ViewModelProvider(this).get(LocationsViewModel.class);
    }
}