package com.example.a06radiobutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Correct initialization
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);


        // ✅ Button click logic
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "You say ";

                if (radioButton1.isChecked()) {
                    msg += radioButton1.getText();
                } else if (radioButton2.isChecked()) {
                    msg += radioButton2.getText();
                } else {
                    msg = "Please select an option!";
                }

                textView.setText(msg);


            }
        });
    }
}
