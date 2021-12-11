package com.example.habittracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    private Button btnSair;
    private Button btnAlterarPerfil;
    private Button btnAlterarSenha;
    private View view;
    private TextView textView;
    private User user;
    Intent i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnSair = view.findViewById(R.id.btnSair);
        btnAlterarPerfil = view.findViewById(R.id.btnAlterarPerfil);
        btnAlterarSenha = view.findViewById(R.id.btnAlterarSenha);
        textView = view.findViewById(R.id.textView);
        preencherNome();

        btnSair.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("UserLogged");
            editor.apply();
            i = new Intent(getContext(), MainActivity.class);
            startActivity(i);
        });

        btnAlterarPerfil.setOnClickListener(v -> {
            i = new Intent(getContext(), AlteracaoPerfilActivity.class);
            startActivity(i);
        });

        btnAlterarSenha.setOnClickListener(v -> {
            i = new Intent(getContext(), AlteracaoSenhaActivity.class);
            startActivity(i);
        });


        return view;
    }

    private void preencherNome(){
        SharedPreferences sharedPreferences;
        sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String result = sharedPreferences.getString("UserLogged", "");
        Gson gson = new Gson();

        user = gson.fromJson(result, User.class);

        updateUI();

    }

    public void updateUI(){
        textView.setText(user.getNome());
    }

}