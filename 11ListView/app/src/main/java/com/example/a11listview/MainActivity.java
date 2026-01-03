package com.example.a11listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static ListView list_View;


    private static String [] Names =new String[]{"Sadharuwan" ,"Kalhara" ,"Tharushi","Janith","Kumara"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView();

    }
    public void listView (){
        list_View = (ListView) findViewById(R.id.listViwe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.name_list, Names);
        list_View.setAdapter(adapter);
        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)list_View.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"Position: "+position+" value :"+value,Toast.LENGTH_LONG).show();
            }
        });

    }
}