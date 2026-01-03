package com.example.a23sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Add_Data extends AppCompatActivity {

    EditText fName,lName,gender,email;
    Button add,back;

    DBHelp dbHelp;

    void cleanForm (){
        fName.setText("");
        lName.setText("");
        email.setText("");
        gender.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        back=findViewById(R.id.backButton);
        add=findViewById(R.id.addButton);

        fName =findViewById(R.id.userFName);
        lName =findViewById(R.id.userLName);
        gender =findViewById(R.id.userGender);
        email =findViewById(R.id.userEmail);

        dbHelp =new DBHelp(this);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname =fName.getText().toString();
                String lname =lName.getText().toString();
                String gend =gender.getText().toString();
                String mail =email.getText().toString();

                if(fname.isEmpty() || lname.isEmpty() || gend.isEmpty() || mail.isEmpty() ){
                    Toast.makeText(Add_Data.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    cleanForm();
                    return;
                }
                long rowID = dbHelp.inserData(fname,lname,gend,mail);
                if (rowID != -1){
                    Toast.makeText(Add_Data.this, "Inserted Successfully (ID: " + rowID + ")", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Add_Data.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Add_Data.this, "Error \n Insert Fail", Toast.LENGTH_SHORT).show();
                    cleanForm();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Add_Data.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}