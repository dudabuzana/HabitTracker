package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastroActivity extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnCadastro;
    EditText edtNome;
    EditText edtEmail;
    EditText edtDataNascimento;
    EditText edtSenha;
    EditText edtSenhaConfirmacao;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        initComponents();


    btnVoltar.setOnClickListener( v -> {
        i = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(i);
    });

    btnCadastro.setOnClickListener(v -> {

    });

    }


    private void initComponents() {
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCadastro = findViewById(R.id.btnCadastro);
        edtNome = findViewById(R.id.edtNomeCadastro);
        edtDataNascimento = findViewById(R.id.edtDataCadastro);
        edtEmail = findViewById(R.id.edtEmailCadastro);
        edtSenha = findViewById(R.id.edtSenhaCadastro);
        edtSenhaConfirmacao = findViewById(R.id.edtSenhaConfirmacao);
    }
}