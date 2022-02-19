package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitInterno;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CumprimentoHabitoActivity extends AppCompatActivity {
    EditText edtNomeHabitoAlteracao;
    EditText edtDescHabitoAlteracao;
    CheckBox cbAtivo;
    EditText edtHoraNotificacao;
    Button btnAlteracao3;
    ImageView btnVoltarCumprimento;
    Intent i;

    private HabitInterno habitInterno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumprimento_habito);
        initComponents();
        Intent intent = getIntent();
        String habit = intent.getStringExtra("habitoSelecionado");

        if(intent.hasExtra(("habitoSelecionado"))){
            Gson gson = new Gson();
            habitInterno = gson.fromJson(habit, HabitInterno.class);

            edtNomeHabitoAlteracao.setText(habitInterno.getNome());
            edtDescHabitoAlteracao.setText(habitInterno.getDescricao());
            cbAtivo.setChecked(habitInterno.isAtivo());
            edtHoraNotificacao.setText(habitInterno.getHorario());

        }

        btnAlteracao3.setOnClickListener(v -> {
            String nome = edtNomeHabitoAlteracao.getText().toString();
            String desc = edtDescHabitoAlteracao.getText().toString();
            boolean ativo = cbAtivo.isChecked();
            String horario = edtHoraNotificacao.getText().toString();
            Habit habitEdit = new Habit(nome, desc, ativo, horario, "");
            Call<Habit> call = new RetrofitInitializer().getActions().updateHabito(habitInterno.getUsuario().getId(), habitEdit);
            call.enqueue(new Callback<Habit>() {
                @Override
                public void onResponse(Call<Habit> call, Response<Habit> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(CumprimentoHabitoActivity.this, "Hábito Alterado com Sucesso", Toast.LENGTH_LONG).show();
                        NotificationManager notificationManager = (NotificationManager) CumprimentoHabitoActivity.this.getSystemService(CumprimentoHabitoActivity.this.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            int importance = NotificationManager.IMPORTANCE_DEFAULT;
                            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
                            notificationManager.createNotificationChannel(notificationChannel);
                            builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannel.getId());
                        } else {
                            builder = new NotificationCompat.Builder(getApplicationContext());
                        }
                        String[] horaminuto = horario.split(":");
                        int hora = Integer.parseInt(horaminuto[0]);
                        int minuto = Integer.parseInt(horaminuto[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hora);
                        calendar.set(Calendar.MINUTE, minuto);
                        if(isDateBeforeNow(calendar)){
                            //adiciona um dia
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        calendar.getTimeInMillis();
                        builder = builder
                                .setSmallIcon(R.drawable.logo)
                                .setContentTitle("MEU DEUS")
                                .setContentText("FUNCIONOU, DUDA!")
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setAutoCancel(true)
                                .setWhen(calendar.getTimeInMillis())
                                .setShowWhen(true);
                        notificationManager.notify(1, builder.build());

                    }else{
                        Toast.makeText(CumprimentoHabitoActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Habit> call, Throwable t) {
                    Toast.makeText(CumprimentoHabitoActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void initComponents() {
        edtNomeHabitoAlteracao = findViewById(R.id.edtNomeHabitoAlteracao);
        edtDescHabitoAlteracao = findViewById(R.id.edtDescHabitoAlteracao);
        cbAtivo = findViewById(R.id.cbAtivo);
        edtHoraNotificacao = findViewById(R.id.edtHoraNotificacao);
        btnAlteracao3 = findViewById(R.id.btnAlteracao3);
        btnVoltarCumprimento = findViewById(R.id.btnVoltarCumprimento);

        btnVoltarCumprimento.setOnClickListener( v -> {
            i = new Intent(this, BottomNavigationActivity.class);
            startActivity(i);
        });
    }


    private static boolean isDateBeforeNow(Calendar calendar){
        return calendar.getTimeInMillis() <= System.currentTimeMillis();
    }
}