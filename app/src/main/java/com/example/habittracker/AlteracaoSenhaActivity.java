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

import com.example.habittracker.model.RecuperarSenhaJSON;
import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteracaoSenhaActivity extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnAlterar;
    EditText edtSenhaAntiga;
    EditText edtSenhaNova;
    EditText edtSenhaNovaConfirmacao;
    User user;

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

            String senhaAntiga = edtSenhaAntiga.getText().toString();
            String senhaNova = edtSenhaNova.getText().toString();
            String senhaNovaConfirmacao = edtSenhaNovaConfirmacao.getText().toString();

            if(senhaAntiga.isEmpty() || senhaNova.isEmpty() || senhaNovaConfirmacao.isEmpty()){
                Toast.makeText(AlteracaoSenhaActivity.this, "Todos Os Campos São Obrigatórios.", Toast.LENGTH_LONG).show();
            }else{
                if(senhaNova.equals(senhaNovaConfirmacao)){
                    RecuperarSenhaJSON json = new RecuperarSenhaJSON(senhaAntiga, senhaNova, senhaNovaConfirmacao);

                    Call<User> call = new RetrofitInitializer().getActions().updatePassword(user.getId(), json);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(AlteracaoSenhaActivity.this, "Senha Atualizada Com Sucesso!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(AlteracaoSenhaActivity.this, "Erro ao Atualizar a Senha", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(AlteracaoSenhaActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                }else{
                    Toast.makeText(AlteracaoSenhaActivity.this, "Senhas diferentes!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initComponents() {
        btnVoltar = findViewById(R.id.btnVoltarAlteracaoSenha);
        btnAlterar = findViewById(R.id.btnAlteracaoSenha);
        edtSenhaAntiga = findViewById(R.id.edtSenhaAntiga);
        edtSenhaNova = findViewById(R.id.edtSenhaNova);
        edtSenhaNovaConfirmacao = findViewById(R.id.edtSenhaConfirma);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String result = sharedPreferences.getString("UserLogged", "");
        Gson gson = new Gson();

        user = gson.fromJson(result, User.class);
    }

}