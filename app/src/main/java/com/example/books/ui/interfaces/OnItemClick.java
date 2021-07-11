package com.example.books.ui.interfaces;

import android.view.View;

import com.example.books.ui.models.BooksModel;

public interface OnItemClick {
    void onClick(BooksModel model, View v);
}
