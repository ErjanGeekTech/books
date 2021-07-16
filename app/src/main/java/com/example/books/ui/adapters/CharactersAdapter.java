package com.example.books.ui.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.books.databinding.ItemCharacterBinding;
import com.example.books.models.RickAndMortyCharacter;
import com.example.books.ui.interfaces.OnItemClick;

public class CharactersAdapter extends ListAdapter<RickAndMortyCharacter, CharactersAdapter.CharactersViewHolder> {

   private ItemCharacterBinding binding;
   public OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public CharactersAdapter(DiffUtil.ItemCallback<RickAndMortyCharacter> diffCallback) {
        super(diffCallback);
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
        holder.onBind(getItem(position));
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
