package com.example.a15backgruondcolorchangerapp;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    ConstraintLayout constraintLayout;

    int[]colors ={
            Color.BLUE,
            Color.YELLOW,
            Color.GRAY,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.BLACK


    };
    private int currentIndex=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button =findViewById(R.id.button);
        constraintLayout =findViewById(R.id.main);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex =(currentIndex +1) % colors.length ;

                constraintLayout.setBackgroundColor(colors[currentIndex]);

            }
        });




    }
}