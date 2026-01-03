package com.example.a04seekbar;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Correct initialization
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textViwe);

        // ✅ Listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: add feedback
                textView.setText("Started moving...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: show final value
                textView.setText("Final Value: " + seekBar.getProgress());
            }
        });
    }
}
