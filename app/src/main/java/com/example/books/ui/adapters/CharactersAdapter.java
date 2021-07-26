package com.example.books.ui.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.books.databinding.ItemCharacterBinding;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.ui.interfaces.OnItemClick;

import java.util.ArrayList;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> {

   private ItemCharacterBinding binding;
   public OnItemClick itemClick;
   private ArrayList <RickAndMortyCharacter> list = new ArrayList<>();

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void addList (ArrayList <RickAndMortyCharacter> getList){
        list.addAll(getList);
        notifyDataSetChanged();
    }

    @Override
    public CharactersViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharactersViewHolder(binding.getRoot());
    }

    public static DiffUtil.ItemCallback<RickAndMortyCharacter> diffCallback = new DiffUtil.ItemCallback<RickAndMortyCharacter>() {
        @Override
        public boolean areItemsTheSame( RickAndMortyCharacter oldItem, RickAndMortyCharacter newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame( RickAndMortyCharacter oldItem, RickAndMortyCharacter newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public void onBindViewHolder( CharactersAdapter.CharactersViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CharactersViewHolder extends RecyclerView.ViewHolder {
        public CharactersViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(RickAndMortyCharacter item) {
            Glide
                    .with(binding.imageItemCharacter)
                    .load(item.image)
                    .into(binding.imageItemCharacter);
            binding.textItemCharacter.setText(item.name);

            itemView.setOnClickListener(v -> itemClick.onClick(item.getId(), v));
        }
    }
}
