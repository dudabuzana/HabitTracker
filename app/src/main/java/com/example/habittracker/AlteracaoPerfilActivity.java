package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.example.habittracker.utils.Utils;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteracaoPerfilActivity extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnAlterar;
    EditText edtNome;
    EditText edtEmail;
    EditText edtDataNascimento;
    User user;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_perfil);
        initComponents();
        preencherFormulario();

        btnVoltar.setOnClickListener( v -> {
            i = new Intent(AlteracaoPerfilActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

        btnAlterar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String dataNascimento = edtDataNascimento.getText().toString();
            String foto = "";
            if(nome.isEmpty() || email.isEmpty() || dataNascimento.isEmpty()){
                Toast.makeText(AlteracaoPerfilActivity.this, "Todos os Campos são Obrigatórios", Toast.LENGTH_SHORT).show();
            }else{
                User userEdit = new User(nome, dataNascimento, email, foto, user.getSenha());

                Call<User> call = new RetrofitInitializer().getActions().updateUser(user.getId(),userEdit);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User userRetornado = response.body();
                            Toast.makeText(AlteracaoPerfilActivity.this, "Usuário Alterado Com Sucesso!", Toast.LENGTH_LONG).show();
                            
                            user.setNome(userRetornado.getNome());
                            user.setEmail(userRetornado.getEmail());
                            user.setDataNascimento(userRetornado.getDataNascimento());

                            Gson gson = new Gson();
                            String userJSON = gson.toJson(user);

                            SharedPreferences sharedPreferences;
                            sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("UserLogged", userJSON);
                            editor.apply();

                        }else{
                            Toast.makeText(AlteracaoPerfilActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(AlteracaoPerfilActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void initComponents() {
        btnVoltar = findViewById(R.id.btnVoltar);
        btnAlterar = findViewById(R.id.btnAlteracao);
        edtNome = findViewById(R.id.edtNomeAlteracao);
        edtDataNascimento = findViewById(R.id.edtDataAlteracao);
        edtEmail = findViewById(R.id.edtEmailAlteracao);
    }

    private void preencherFormulario(){
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String result = sharedPreferences.getString("UserLogged", "");
        Gson gson = new Gson();

        user = gson.fromJson(result, User.class);

        edtNome.setText(user.getNome());
        String dataFormatada = "";
        if(user.getDataNascimento().length() > 11){
            Utils utils = new Utils();
            dataFormatada = utils.formataData(user.getDataNascimento());

        }else{
            dataFormatada = user.getDataNascimento();
        }

        edtDataNascimento.setText(dataFormatada);
        edtEmail.setText(user.getEmail());
    }
}