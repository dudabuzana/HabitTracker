package com.example.habittracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.habittracker.model.Habit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private RecyclerView RecyclerFeed;
    private View         view;
    private Button       btnAddHabito;

    List<Habit> habitList;

    Intent i;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view          = inflater.inflate(R.layout.fragment_feed, container, false);
        btnAddHabito  = view.findViewById(R.id.btnAddHabito);
        RecyclerFeed  = view.findViewById(R.id.RecyclerFeed);

        btnAddHabito.setOnClickListener(v -> {
            i = new Intent(getContext(), CadastroHabitoActivity.class);
            startActivity(i);
        });

        Gson gson = new Gson();
        Type type = new TypeToken<List<Habit>>(){}.getType();
        habitList = gson.fromJson(i.getStringExtra("habitList"), type);

        /* Verificar */
        MyAdapter myAdapter = new MyAdapter(getContext(), habitList);
        RecyclerFeed.setAdapter(myAdapter);
        RecyclerFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}