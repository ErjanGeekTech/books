package com.example.books.ui.fragments.dialog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.R;
import com.example.books.base.BaseFragment;
import com.example.books.databinding.FragmentDailogBinding;
import com.example.books.ui.fragments.books.BooksViewModel;


public class DialogFragment extends BaseFragment<FragmentDailogBinding, DialogViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDailogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupImage();
    }

    private void setupImage() {
        int image = DialogFragmentArgs.fromBundle(getArguments()).getDialogImageBooks();
        binding.imageBooksDialog.setImageResource(image);
    }

    @Override
    protected void initialize() {
      viewModel = new ViewModelProvider(requireActivity()).get(DialogViewModel.class);
    }
}