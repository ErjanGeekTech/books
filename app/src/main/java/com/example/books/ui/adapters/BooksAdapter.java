package com.example.books.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.books.databinding.ItemBooksListBinding;
import com.example.books.ui.interfaces.OnItemClick;
import com.example.books.ui.models.BooksModel;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    List<BooksModel> list = new ArrayList<>();
    OnItemClick onItemClick;
    ItemBooksListBinding binding;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public BooksViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = ItemBooksListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BooksViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(BooksAdapter.BooksViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<BooksModel> listf) {
        list = listf;
        notifyDataSetChanged();
    }

    public class BooksViewHolder extends  RecyclerView.ViewHolder{
        public BooksViewHolder( View itemView) {
            super(itemView);

        }

        public void bind(BooksModel booksModel) {
            itemView.setOnClickListener(v -> onItemClick.onClick(booksModel, itemView));
            binding.imageView.setOnLongClickListener(v -> {
                onItemClick.onLongClick(booksModel.getImage(), v);
                return false;
            });
            binding.txtTitle.setText(booksModel.getTitle());
            binding.imageView.setImageResource(booksModel.getImage());
        }
    }
}
