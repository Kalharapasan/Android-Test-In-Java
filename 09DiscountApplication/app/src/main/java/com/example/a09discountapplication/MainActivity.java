package com.example.a09discountapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    SeekBar seekBar;
    TextView textView1, textView2;
    EditText editText;
    private int seekVal, price;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editTextNumber); // ✅ Initialize EditText

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekVal = progress;
                textView2.setText("Discount Rate: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString().trim();

                if (input.isEmpty()) {
                    editText.setError("Please enter price");
                    return;
                }

                price = Integer.parseInt(input);

                // ✅ Apply discount correctly
                total = price - (price * (seekVal / 100.0));

                textView1.setText("Total Price: " + total);
            }
        });
    }
}
