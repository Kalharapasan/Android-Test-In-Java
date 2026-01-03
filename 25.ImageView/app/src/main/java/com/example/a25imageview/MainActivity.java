package com.example.a25imageview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    private int current_img_index;
    int [] imges = {R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar04,R.drawable.avatar5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_img_index ++;
                current_img_index = current_img_index + imges.length;
                imageView.setImageResource(imges[current_img_index]);
            }
        });


    }
}