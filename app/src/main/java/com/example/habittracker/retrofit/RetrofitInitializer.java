package com.example.habittracker.retrofit;

import com.example.habittracker.api.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private final Retrofit retrofit;

    public RetrofitInitializer() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://habit-tracker-udesc.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getActions() {
        return retrofit.create(Service.class);
    }
}
