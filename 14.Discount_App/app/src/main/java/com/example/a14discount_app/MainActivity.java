package com.example.a14discount_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textView1, textView2, textView3, textView4;
    SeekBar seekBar;
    RadioGroup radioGroup;

    private int val, seekbarval;
    private double dis, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        seekBar = findViewById(R.id.seekBar);
        radioGroup = findViewById(R.id.radioGroup);

        seekbarval = 0;
        val = 0;


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView1.setText(progress + "%");
                seekbarval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String str = editText.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    disableDiscount();
                    return;
                }

                try {
                    val = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    val = 0;
                }

                if (checkedId == R.id.card) {
                    if (val > 10000) {
                        enableDiscount();
                    } else {
                        disableDiscount();
                        seekbarval = 0;
                    }
                } else if (checkedId == R.id.cash) {
                    enableDiscount();
                } else {
                    disableDiscount();
                    seekbarval = 0;
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();

                if (TextUtils.isEmpty(str)) {
                    editText.setError("Enter bill amount");
                    return;
                }

                try {
                    val = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    editText.setError("Invalid number");
                    return;
                }


                dis = (seekbarval * val) / 100.0;
                total = val - dis;

                textView2.setVisibility(View.VISIBLE);
                textView2.setText(String.valueOf(val));
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(String.valueOf(dis));
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(String.valueOf(total));
            }
        });
    }

    private void enableDiscount() {
        seekBar.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
    }

    private void disableDiscount() {
        seekBar.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
    }
}
