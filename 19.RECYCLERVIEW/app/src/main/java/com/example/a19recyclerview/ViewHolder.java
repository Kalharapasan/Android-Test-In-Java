package com.example.a19recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name;
    TextView email;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.name);
        email = itemView.findViewById(R.id.email);
    }
}
