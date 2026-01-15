package com.example.a30firebase_i;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistryActivity extends AppCompatActivity {

    private Button regiButton;
    private EditText fName,lname,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        regiButton= findViewById(R.id.btnRegister);
        fName =findViewById(R.id.etFirstName);
        lname=findViewById(R.id.etFirstName);
        email =findViewById(R.id.etEmail);
        password =findViewById(R.id.etPassword);

    }
}