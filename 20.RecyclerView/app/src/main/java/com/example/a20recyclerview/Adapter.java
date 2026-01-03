package com.example.a20recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    Context context;

    public DataClass [] data;

    public Adapter(Context context, DataClass [] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.single_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.textView.setText(data[position].getName());
        holder.imageView.setImageResource(data[position].getImgID());

        final int currentPosition =position;
        holder.bUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass dataClass = data[currentPosition];
                Toast.makeText(context,"You Clik"+dataClass.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        Button bUtton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.textView);
            imageView =itemView.findViewById(R.id.imageView);
            bUtton =itemView.findViewById(R.id.button);
        }
    }
}
