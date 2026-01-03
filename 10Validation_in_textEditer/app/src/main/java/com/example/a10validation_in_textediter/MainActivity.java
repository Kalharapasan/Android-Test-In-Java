package com.example.a10validation_in_textediter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText1, editText2, editText3, editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextText3);
        editText4 = findViewById(R.id.editTextText4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get current values when button clicked
                String name = editText1.getText().toString().trim();
                String password = editText2.getText().toString().trim();
                String email = editText3.getText().toString().trim();
                String phone = editText4.getText().toString().trim();

                boolean allValid = true;

                if (name.isEmpty()) {
                    editText1.setError("Name is required");
                    allValid = false;
                }

                if (password.isEmpty()) {
                    editText2.setError("Password is required");
                    allValid = false;
                }

                if (email.isEmpty()) {
                    editText3.setError("Email is required");
                    allValid = false;
                }

                if (phone.isEmpty()) {
                    editText4.setError("Phone number is required");
                    allValid = false;
                }


            }
        });
    }
}
