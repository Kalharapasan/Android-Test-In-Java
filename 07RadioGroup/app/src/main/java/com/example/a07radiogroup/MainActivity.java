package com.example.a07radiogroup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Correct initialization
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "You Say ";

                if (radioButton1.isChecked()) {
                    msg += radioButton1.getText();  // Yes
                } else if (radioButton2.isChecked()) {
                    msg += radioButton2.getText();  // No
                } else {
                    msg = "Please select an option!";
                }

                textView.setText(msg);

                // ✅ Clear selection after showing result
                radioGroup.clearCheck();
            }
        });
    }
}
