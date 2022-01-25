package com.example.habittracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FeedFragment extends Fragment {

    private View   view;
    private Button btnAddHabito;

    Intent i;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view         = inflater.inflate(R.layout.fragment_feed, container, false);
        btnAddHabito = view.findViewById(R.id.btnAddHabito);

        btnAddHabito.setOnClickListener(v -> {
            i = new Intent(getContext(), CadastroHabitoActivity.class);
            startActivity(i);
        });

        return view;
    }

}