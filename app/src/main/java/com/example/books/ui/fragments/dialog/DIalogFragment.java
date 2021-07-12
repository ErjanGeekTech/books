package com.example.books.ui.fragments.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentDlalogBinding;
import com.example.books.ui.fragments.books.BooksViewModel;


public class DIalogFragment extends AppCompatDialogFragment {

    FragmentDlalogBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDlalogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupImage();

        super.onViewCreated(view, savedInstanceState);
    }

    private void setupImage() {
        int image = DIalogFragmentArgs.fromBundle(getArguments()).getGetImageDlalog();
        Log.e("tag", String.valueOf(image));
        binding.imageBooksDialog.setImageResource(image);
    }

}