package com.example.a23sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelp dbHelp;
    Button insert, update, delete;
    ListView listView;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;
    String selectedId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewData);
        insert = findViewById(R.id.addDataButton);
        update = findViewById(R.id.updateDataButton);
        delete = findViewById(R.id.deleteDataButton);

        dbHelp = new DBHelp(this);
        loadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String record = dataList.get(position);
                if (record.startsWith("ID:")) {
                    selectedId = record.split("\n")[0].replace("ID:", "").trim();
                    Toast.makeText(MainActivity.this, "Selected ID: " + selectedId, Toast.LENGTH_SHORT).show();
                }
            }
        });

        insert.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Add_Data.class);
            startActivity(intent);
        });

        update.setOnClickListener(v -> {
            if (selectedId != null) {
                Cursor c = dbHelp.getDataById(selectedId);
                if (c != null && c.moveToFirst()) {
                    String fname = c.getString(1);
                    String lname = c.getString(2);
                    String gender = c.getString(3);
                    String email = c.getString(4);
                    c.close();

                    Intent intent = new Intent(MainActivity.this, Update.class);
                    intent.putExtra("id", selectedId);
                    intent.putExtra("fname", fname);
                    intent.putExtra("lname", lname);
                    intent.putExtra("gender", gender);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    if (c != null) c.close();
                    Toast.makeText(MainActivity.this, "Failed to load selected record", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Select a record to update", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(v -> {
            if (selectedId != null) {
                boolean deleted = dbHelp.deleteData(selectedId);
                if (deleted) {
                    Toast.makeText(MainActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
                    selectedId = null;
                    loadData();
                } else {
                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Select a record to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        dataList = new ArrayList<>();
        Cursor res = dbHelp.getAllData();

        if (res.getCount() == 0) {
            dataList.add("No Data In User Database");
        } else {
            while (res.moveToNext()) {
                String record = "ID: " + res.getInt(0) +
                        "\nFirst Name: " + res.getString(1) +
                        "\nLast Name: " + res.getString(2) +
                        "\nGender: " + res.getString(3) +
                        "\nMail: " + res.getString(4);
                dataList.add(record);
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
