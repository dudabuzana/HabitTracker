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

public class PerfilFragment extends Fragment {

    private Button btnSair;
    private Button btnAlterarPerfil;
    private Button btnAlterarSenha;
    private View view;

    Intent i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        initComponents();

        btnSair.setOnClickListener(v -> {
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

    private void initComponents() {
        btnSair = view.findViewById(R.id.btnSair);
        btnAlterarPerfil = view.findViewById(R.id.btnAlterarPerfil);
        btnAlterarSenha = view.findViewById(R.id.btnAlterarSenha);
    }

}