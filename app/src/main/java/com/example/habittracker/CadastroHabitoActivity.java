package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            if(nomeHabito.isEmpty() || descricaoHabito.isEmpty()){
                Toast.makeText(this, "Nome e Descrição do Hábito São Obrigatórios!", Toast.LENGTH_LONG).show();
            }else{
                SharedPreferences sharedPreferences;
                sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("UserLogged", "");
                Gson gson = new Gson();

                User user = gson.fromJson(result, User.class);

                Habit habit = new Habit(nomeHabito, descricaoHabito, true, "", user.getId());
                retrofit2.Call<Habit> call = new RetrofitInitializer().getActions().createHabito(habit);
                call.enqueue(new Callback<Habit>() {
                    @Override
                    public void onResponse(Call<Habit> call, Response<Habit> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(CadastroHabitoActivity.this, "Hábito cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            i = new Intent(CadastroHabitoActivity.this, BottomNavigationActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(CadastroHabitoActivity.this, "Erro ao cadastrar hábito", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Habit> call, Throwable t) {
                        Toast.makeText(CadastroHabitoActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

    private void initComponents() {
        edtNomeHabito      = findViewById(R.id.edtNomeHabito);
        edtDescricaoHabito = findViewById(R.id.edtDescricaoHabito);
        btnVoltarFeed      = findViewById(R.id.btnVoltarFeed);
        btnCadastroHabito  = findViewById(R.id.btnCadastroHabito);
    }

}