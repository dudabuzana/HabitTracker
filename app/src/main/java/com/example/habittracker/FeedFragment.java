package com.example.habittracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitInterno;
import com.example.habittracker.model.User;
import com.example.habittracker.retrofit.RetrofitInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {

    private RecyclerView RecyclerFeed;
    private View         view;
    private Button       btnAddHabito;

    List<HabitInterno> habitList;

    Intent i;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view          = inflater.inflate(R.layout.fragment_feed, container, false);
        btnAddHabito  = view.findViewById(R.id.btnAddHabito);
        RecyclerFeed  = view.findViewById(R.id.RecyclerFeed);

        btnAddHabito.setOnClickListener(v -> {
            i = new Intent(getContext(), CadastroHabitoActivity.class);
            startActivity(i);
        });

        habitList = new List<HabitInterno>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<HabitInterno> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(HabitInterno habitInterno) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends HabitInterno> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends HabitInterno> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public HabitInterno get(int index) {
                return null;
            }

            @Override
            public HabitInterno set(int index, HabitInterno element) {
                return null;
            }

            @Override
            public void add(int index, HabitInterno element) {

            }

            @Override
            public HabitInterno remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<HabitInterno> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<HabitInterno> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<HabitInterno> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String result = sharedPreferences.getString("UserLogged", "");
        Gson gson = new Gson();

        User user = gson.fromJson(result, User.class);

        //Chamada Retrofit para buscar lista de habitos do usuário
        Call<List<HabitInterno>> call = new RetrofitInitializer().getActions().listaTodosHabitos(user.getId());
        call.enqueue(new Callback<List<HabitInterno>>() {
            @Override
            public void onResponse(Call<List<HabitInterno>> call, Response<List<HabitInterno>> response) {
                if(response.isSuccessful()){
                    habitList = response.body();

                    MyAdapter myAdapter = new MyAdapter(getContext(), habitList);

                    RecyclerFeed.setLayoutManager(new LinearLayoutManager(getContext()));
//                    RecyclerFeed.setHasFixedSize(true);
                    //RecyclerFeed.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                    RecyclerFeed.setAdapter(myAdapter);

                    myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            HabitInterno habit = habitList.get(position);
//                            CumprimentoHabitoActivity myFrag = new CumprimentoHabitoActivity();
//                            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, myFrag).commit();

                            Gson gson = new Gson();
                            String habitoSelecionado = gson.toJson(habit);

                            i = new Intent(getContext(), CumprimentoHabitoActivity.class);
                            i.putExtra("habitoSelecionado", habitoSelecionado);
                            startActivity(i);
                        }
                    });


                }else{
                    Toast.makeText(getContext(), "Resposta Falha", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<HabitInterno>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });

//        Gson gson = new Gson();
        Type type = new TypeToken<List<Habit>>(){}.getType();
//        habitList = gson.fromJson(i.getStringExtra("habitList"), type);

        /* Verificar */

        return view;
    }

}