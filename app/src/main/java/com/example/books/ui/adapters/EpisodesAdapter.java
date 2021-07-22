package com.example.books.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.databinding.ItemEpisodesBinding;
import com.example.books.models.RickAndMortyEpisodes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {

    ArrayList<RickAndMortyEpisodes> list =new ArrayList<>();
    ItemEpisodesBinding binding;

    public void addList(List<RickAndMortyEpisodes> getList){
        list.addAll(getList);
        notifyDataSetChanged();
    }

    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemEpisodesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EpisodesViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EpisodesViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EpisodesViewHolder extends RecyclerView.ViewHolder {
        public EpisodesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void onBind(RickAndMortyEpisodes rickAndMortyEpisodes) {
            binding.txtNameEpisodes.setText(rickAndMortyEpisodes.name);
            binding.txtAirDateEpisodes.setText(rickAndMortyEpisodes.airDate);
            binding.txtEpisodesEpisodes.setText(rickAndMortyEpisodes.episode);
            binding.txtCreatedEpisodes.setText(rickAndMortyEpisodes.created);
        }
    }
}
