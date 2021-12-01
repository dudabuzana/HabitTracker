package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AlteracaoPerfilActivity extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnAlterar;
    EditText edtNome;
    EditText edtEmail;
    EditText edtDataNascimento;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_perfil);
        initComponents();

        btnVoltar.setOnClickListener( v -> {
            i = new Intent(AlteracaoPerfilActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

    }

    private void initComponents() {
        btnVoltar = findViewById(R.id.btnVoltar);
        btnAlterar = findViewById(R.id.btnAlteracao);
        edtNome = findViewById(R.id.edtNomeAlteracao);
        edtDataNascimento = findViewById(R.id.edtDataAlteracao);
        edtEmail = findViewById(R.id.edtEmailAlteracao);
    }
}