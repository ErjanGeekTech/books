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
import com.example.books.interfaces.OnItemClick;
import com.example.books.models.BooksModel;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

   public static List<BooksModel> list = new ArrayList<>();
    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public BooksViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_books_list, parent, false);
        return new BooksViewHolder(view);
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
        TextView title;
        ImageView imageView;
        public BooksViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            imageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(BooksModel booksModel) {
            itemView.setOnClickListener(v -> onItemClick.onClick(getAdapterPosition()));
            title.setText(booksModel.getTitle());
            imageView.setImageResource(booksModel.getImage());
        }
    }
}
