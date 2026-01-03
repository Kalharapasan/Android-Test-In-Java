package com.example.a20recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.main);

        DataClass [] data = new DataClass[]{
                new DataClass("Kalhara",R.drawable.avatar3,1),
                new DataClass("Saman",R.drawable.avatar04,2),
                new DataClass("Tharushi",R.drawable.avatar2,3),
                new DataClass("Sagara",R.drawable.avatar3,4),
        };

        Adapter adapter = new Adapter(this,data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }
}