package com.example.a22sqliteloginregistryandcurdoperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegUsername, etRegPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize views
        etRegUsername = findViewById(R.id.etRegUsername);
        etRegPassword = findViewById(R.id.etRegPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etRegUsername.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();
                String confirmPass = etConfirmPassword.getText().toString().trim();

                // Validation
                if (username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,
                            "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.length() < 4) {
                    Toast.makeText(RegisterActivity.this,
                            "Username must be at least 4 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this,
                            "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPass)) {
                    Toast.makeText(RegisterActivity.this,
                            "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if username already exists
                if (dbHelper.checkUsername(username)) {
                    Toast.makeText(RegisterActivity.this,
                            "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register user
                boolean success = dbHelper.registerUser(username, password);
                if (success) {
                    Toast.makeText(RegisterActivity.this,
                            "Registration Successful!", Toast.LENGTH_SHORT).show();

                    // Navigate back to login
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Login text click
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}