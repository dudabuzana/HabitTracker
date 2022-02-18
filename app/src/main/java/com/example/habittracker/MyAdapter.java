package com.example.habittracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habittracker.model.HabitInterno;
/*import com.squareup.picasso.Picasso;*/

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<HabitInterno> habits;
    private OnItemClickListener listener;
    Intent i;
    public MyAdapter(Context ct, List<HabitInterno> habits) {
        context     = ct;
        this.habits = habits;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view               = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HabitInterno habit = habits.get(position);
        holder.txtName.setText(habits.get(position).getNome());
        holder.txtDesc.setText(""+habits.get(position).getDescricao());
        /* Verificar */
        /*Picasso.get().load(habits.get(position).getIcone()).into(holder.img);*/
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  txtDesc;
        TextView  txtName;


        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            txtDesc   = itemView.findViewById(R.id.txtDesc);
            txtName = itemView.findViewById(R.id.txtName);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick((position));
                    }
                }
            });

        }
    }
}
