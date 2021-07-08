package com.example.books.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.R;
import com.example.books.databinding.FragmentBooksBinding;
import com.example.books.databinding.ItemBooksListBinding;
import com.example.books.interfaces.OnItemClick;
import com.example.books.models.BooksModel;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

   public static List<BooksModel> list = new ArrayList<>();
    OnItemClick onItemClick;
    ItemBooksListBinding binding;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public BooksViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_books_list, parent, false);
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
        list.clear();
        list.addAll(listf);
        notifyDataSetChanged();
    }

    public class BooksViewHolder extends  RecyclerView.ViewHolder{
        public BooksViewHolder( View itemView) {
            super(itemView);

        }

        public void bind(BooksModel booksModel) {
            itemView.setOnClickListener(v -> onItemClick.onClick(booksModel));
            binding.txtTitle.setText(booksModel.getTitle());
            binding.imageView.setImageResource(booksModel.getImage());
        }
    }
}
