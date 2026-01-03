package com.example.a23sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {

    EditText fName, lName, gender, email;
    DBHelp dbHelp;
    Button update, back;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        back = findViewById(R.id.backButton);
        update = findViewById(R.id.updateButton);

        fName = findViewById(R.id.userFName);
        lName = findViewById(R.id.userLName);
        gender = findViewById(R.id.userGender);
        email = findViewById(R.id.userEmail);

        dbHelp = new DBHelp(this);


        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        fName.setText(intent.getStringExtra("fname"));
        lName.setText(intent.getStringExtra("lname"));
        gender.setText(intent.getStringExtra("gender"));
        email.setText(intent.getStringExtra("email"));

        update.setOnClickListener(v -> {
            String fname = fName.getText().toString();
            String lname = lName.getText().toString();
            String gend = gender.getText().toString();
            String mail = email.getText().toString();

            boolean isUpdated = dbHelp.updateData(userId, fname, lname, gend, mail);
            if (isUpdated) {
                Toast.makeText(Update.this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(Update.this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(v -> finish());
    }
}
