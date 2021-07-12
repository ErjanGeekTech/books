package com.example.books.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;

public abstract class BaseFragment<Binding, ViewModel> extends Fragment {

    protected Binding binding;
    protected ViewModel viewModel;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setupListener();
        setupObserve();
        setupViews();
    }


    protected abstract void initialize();
    protected abstract void setupListener();

    protected abstract void setupObserve();
    protected abstract void setupViews();





}