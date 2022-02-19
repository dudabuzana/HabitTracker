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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import com.example.habittracker.databinding.FragmentPerfilBinding;
import com.example.habittracker.model.LoginJSON;
import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.android.gms.common.api.GoogleApiClient;
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
    SignInButton login_botao_signin_google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        login_botao_signin_google.setSize(SignInButton.SIZE_STANDARD);
        verificaUsuarioLogado();

        cadastro.setOnClickListener(v -> {
            i = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(i);
        });


        login_botao_signin_google.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                    .requestIdToken("221431225817-hont4i90ihu36k61a97a8nsr384a1g0c.apps.googleusercontent.com")
                    .requestEmail()
                    .build();
            GoogleSignInClient cliente = GoogleSignIn.getClient(this, gso);

            startActivityForResult(cliente.getSignInIntent(), 11);
        });

        btnLogin.setOnClickListener(v -> {

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
        login_botao_signin_google = findViewById(R.id.login_botao_signin_google);
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