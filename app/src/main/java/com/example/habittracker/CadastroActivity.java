package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            if(edtSenha.getText().toString().equals(edtSenhaConfirmacao.getText().toString())){
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String dataNascimento = edtDataNascimento.getText().toString();
                String senha = edtSenha.getText().toString();
                String foto = "";
                User user = new User(nome, dataNascimento, email, foto, senha);
                Call<User> call = new RetrofitInitializer().getActions().createUser(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, user.getNome() + " cadastrado!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CadastroActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(CadastroActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                Toast.makeText(CadastroActivity.this, "Senhas Diferentes!", Toast.LENGTH_LONG).show();
            }
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