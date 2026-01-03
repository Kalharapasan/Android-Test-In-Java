package com.example.a2add_tow_number;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText num1;
    EditText num2;
    TextView ans;

    int nu1, nu2, an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.addButton);
        num1 = findViewById(R.id.num1Text);
        num2 = findViewById(R.id.num2Text);
        ans = findViewById(R.id.ansText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = num1.getText().toString().trim();
                String s2 = num2.getText().toString().trim();

                if (!s1.isEmpty() && !s2.isEmpty()) {
                    nu1 = Integer.parseInt(s1);
                    nu2 = Integer.parseInt(s2);
                    an = nu1 + nu2;
                    ans.setText(String.valueOf(an));
                } else {
                    ans.setText("Enter numbers!");
                }
            }
        });
    }
}
