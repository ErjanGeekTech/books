package com.example.books.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.databinding.FragmentDlalogBinding;


public class DIalogFragment extends DialogFragment {

    FragmentDlalogBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = FragmentDlalogBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog builder = new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initialize();
        return builder;
    }

    private void initialize() {
        setupImage();
    }

    private void setupImage() {
//        int image = DIalogFragmentArgs.fromBundle(getArguments()).getGetImageDlalog();
//        Log.e("tag", String.valueOf(image));
//        binding.imageBooksDialog.setImageResource(image);
    }

}