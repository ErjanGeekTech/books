package com.example.books.ui.interfaces;

import android.view.View;

import com.example.books.models.RickAndMortyCharacter;

public interface OnItemClick {
    void onClick(int id, View v);
    void onLongClick(int image, View v);
}
