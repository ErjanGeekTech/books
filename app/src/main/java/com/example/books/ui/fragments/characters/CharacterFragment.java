package com.example.books.ui.fragments.characters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentCharacterBinding;
import com.example.books.ui.adapters.CharactersAdapter;
import com.example.books.ui.interfaces.OnItemClick;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        fetchCharacters();
    }

    private void fetchCharacters() {
        if (isNetworkAvailable()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickAndMortyCharacterRickAndMortyResponse -> {
                binding.progressCircular.setVisibility(View.INVISIBLE);
                if (rickAndMortyCharacterRickAndMortyResponse != null) {
                    adapter.addList(rickAndMortyCharacterRickAndMortyResponse.getResults());
                }
            });
        } else {
            binding.progressCircular.setVisibility(View.INVISIBLE);
            adapter.addList(viewModel.getCharacters());
        }

    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

//        WifiManager wifiMgr = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
//
//        byte[] ipAddress = BigInteger.valueOf(wifiInfo.getIpAddress()).toByteArray();
//        try {
//            InetAddress myAddr = InetAddress.getByAddress(ipAddress);
//            String hostAddr = myAddr.getHostAddress();
//            Toast.makeText(getContext(), "Name is  " + hostAddr, Toast.LENGTH_SHORT).show();
//
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
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
                Navigation.findNavController(v).navigate(CharacterFragmentDirections.actionCharacterFragmentToDescriptionFragment2(id).setGetIdDescription(id));
            }

            @Override
            public void onLongClick(int image, View v) {

            }
        });
    }


}