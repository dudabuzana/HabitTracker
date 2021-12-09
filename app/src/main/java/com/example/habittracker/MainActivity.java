package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.habittracker.databinding.FragmentPerfilBinding;

public class MainActivity extends AppCompatActivity {

    Intent i;

    TextView cadastro;
    Button btnLogin;
    EditText edtEmail;
    EditText edtSenha;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

    cadastro.setOnClickListener(v -> {
        i = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(i);
    });

    btnLogin.setOnClickListener(v -> {
        i = new Intent(MainActivity.this, BottomNavigationActivity.class);
        startActivity(i);
    });

    }

    private void initComponents() {
        cadastro = findViewById(R.id.txtCadastro);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        logo = findViewById(R.id.logo);
        logo.setVisibility(View.VISIBLE);
    }



}