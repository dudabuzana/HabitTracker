package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastroHabitoActivity extends AppCompatActivity {

    private EditText  edtNomeHabito;
    private EditText  edtDescricaoHabito;
    private ImageView btnVoltarFeed;
    private Button    btnCadastroHabito;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_habito);
        initComponents();

        btnVoltarFeed.setOnClickListener( v -> {
            i = new Intent(CadastroHabitoActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

        btnCadastroHabito.setOnClickListener( v -> {
            String nomeHabito      = edtNomeHabito.getText().toString();
            String descricaoHabito = edtDescricaoHabito.getText().toString();

            i = new Intent(CadastroHabitoActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });
    }

    private void initComponents() {
        edtNomeHabito      = findViewById(R.id.edtNomeHabito);
        edtDescricaoHabito = findViewById(R.id.edtDescricaoHabito);
        btnVoltarFeed      = findViewById(R.id.btnVoltarFeed);
        btnCadastroHabito  = findViewById(R.id.btnCadastroHabito);
    }

}