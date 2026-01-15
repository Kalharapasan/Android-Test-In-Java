package com.example.a30firebase_i;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistryActivity extends AppCompatActivity {
    private Button regiButton;
    private EditText email,password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        regiButton= findViewById(R.id.btnRegister);
        email =findViewById(R.id.etEmail);
        password =findViewById(R.id.etPassword);
        auth =FirebaseAuth.getInstance();

        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _password = password.getText().toString();
                String _email = email.getText().toString();
                if (TextUtils.isEmpty(_email)||TextUtils.isEmpty(_password)){
                    Toast.makeText(RegistryActivity.this, "Empty Credentioal", Toast.LENGTH_SHORT).show();
                }else if(_password.length() <6){
                    Toast.makeText(RegistryActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(_email,_password);
                }
            }
            private void registerUser( String email, String password) {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistryActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistryActivity.this, "User Registry Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistryActivity.this,LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegistryActivity.this, "User Registry Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}