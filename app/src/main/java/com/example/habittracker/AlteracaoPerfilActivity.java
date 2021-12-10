package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AlteracaoPerfilActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1888;
    ImageView btnVoltar;
    Button    btnAlterar;
    Button    btnUploadFoto;
    EditText  edtNome;
    EditText  edtEmail;
    EditText  edtDataNascimento;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_perfil);
        initComponents();

        btnVoltar.setOnClickListener( v -> {
            i = new Intent(AlteracaoPerfilActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

        btnAlterar.setOnClickListener( v -> {
            i = new Intent(AlteracaoPerfilActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        });

        btnUploadFoto.setOnClickListener( v -> {
            i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), REQUEST_CODE);
            startActivity(i);
        });

    }

    //Seleção da Imagem
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK) {
                        //bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        //data gives you the image uri. Try to convert that to bitmap
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {

                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        btnVoltar         = findViewById(R.id.btnVoltar);
        btnAlterar        = findViewById(R.id.btnAlteracao);
        btnUploadFoto     = findViewById(R.id.btnUploadFoto);
        edtNome           = findViewById(R.id.edtNomeAlteracao);
        edtDataNascimento = findViewById(R.id.edtDataAlteracao);
        edtEmail          = findViewById(R.id.edtEmailAlteracao);
    }
}