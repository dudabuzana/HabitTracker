package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habittracker.databinding.FragmentPerfilBinding;
import com.example.habittracker.model.LoginJSON;
import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

/* Comentei pois minha conexão com a API não está funcionando */
/*import retrofit2.Call;*/
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

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
        verificaUsuarioLogado();

        cadastro.setOnClickListener(v -> {
            i = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(v -> {

            i = new Intent(MainActivity.this, BottomNavigationActivity.class);
            startActivity(i);



            if(edtEmail.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "E-Mail e Senha são obrigatórios", Toast.LENGTH_LONG).show();
            }else{
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                LoginJSON loginJSON = new LoginJSON(email, senha);
                Call<User> call = new RetrofitInitializer().getActions().loginUser(loginJSON);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User user = response.body();
                            Gson gson = new Gson();
                            String userJSON = gson.toJson(user);

                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("UserLogged", userJSON);
                            editor.apply();
                            i = new Intent(MainActivity.this, BottomNavigationActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
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

    private void verificaUsuarioLogado(){
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String result = sharedPreferences.getString("UserLogged", "");

        if(!result.isEmpty()){
            i = new Intent(MainActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        }
    }


}