package com.example.habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habittracker.model.Habit;
/*import com.squareup.picasso.Picasso;*/

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Habit> habits;
    public MyAdapter(Context ct, List<Habit> users) {
        context     = ct;
        this.habits = users;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view               = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(habits.get(position).getNome());
        holder.txtId.setText(""+habits.get(position).getId());
        /* Verificar */
        /*Picasso.get().load(habits.get(position).getIcone()).into(holder.img);*/
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  txtId;
        TextView  txtName;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId   = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            img     = itemView.findViewById(R.id.imgIcone);
        }
    }
}
