package com.example.a24toolbarmenus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Use switch for Java instead of Kotlin's when
        switch (item.getItemId()) {

            case R.id.ic_add_contact:
                Toast.makeText(this, "You Click Add Contact", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_favorites:
                Toast.makeText(this, "You Click Add Favorites", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_settins:
                Toast.makeText(this, "You Click Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.miFeedback:
                Toast.makeText(this, "You Click Feedback", Toast.LENGTH_SHORT).show();
                break;

            case R.id.miClose:
                Toast.makeText(this, "You Click Close", Toast.LENGTH_SHORT).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
