package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CadastroHabitoActivity extends AppCompatActivity {

    EditText edtNomeHabito;
    EditText edtDescricaoHabito;
    Button   btnVoltarFeed;
    Button   btnCadastroHabito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_habito);
    }

    private void initComponents() {
        edtNomeHabito      = findViewById(R.id.edtNomeHabito);
        edtDescricaoHabito = findViewById(R.id.edtDescricaoHabito);
      //  btnVoltarFeed      = findViewById(R.id.btnVoltarFeed);
        btnCadastroHabito  = findViewById(R.id.btnCadastroHabito);
    }

}