package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AlteracaoSenhaActivity extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnAlterar;
    EditText edtSenhaAntiga;
    EditText edtSenhaNova;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_senha);
        initComponents();

        btnVoltar.setOnClickListener( v -> {
            i = new Intent(AlteracaoSenhaActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

        btnAlterar.setOnClickListener( v -> {
            i = new Intent(AlteracaoSenhaActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });
    }

    private void initComponents() {
        btnVoltar = findViewById(R.id.btnVoltarAlteracaoSenha);
        btnAlterar = findViewById(R.id.btnAlteracaoSenha);
        edtSenhaAntiga = findViewById(R.id.edtSenhaAntiga);
        edtSenhaNova = findViewById(R.id.edtSenhaNova);
    }

}