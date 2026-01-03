package com.example.a22sqliteloginregistryandcurdoperation;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;
    private Button btnLogout;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);
        btnLogout = findViewById(R.id.btnLogout);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadUsers();

        // Add button click
        fabAdd.setOnClickListener(v -> showAddUserDialog());

        // Logout button click
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    // Load all users from database
    private void loadUsers() {
        userList = dbHelper.getAllUsers();
        adapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(adapter);
    }

    // Show dialog to add new user
    private void showAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        builder.setView(dialogView);

        EditText etName = dialogView.findViewById(R.id.etDialogName);
        EditText etEmail = dialogView.findViewById(R.id.etDialogEmail);
        EditText etPhone = dialogView.findViewById(R.id.etDialogPhone);
        Button btnSave = dialogView.findViewById(R.id.btnDialogSave);
        Button btnCancel = dialogView.findViewById(R.id.btnDialogCancel);

        AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.addUser(name, email, phone);
            if (success) {
                Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
                loadUsers();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // Show dialog to edit user
    @Override
    public void onEditClick(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        builder.setView(dialogView);

        EditText etName = dialogView.findViewById(R.id.etDialogName);
        EditText etEmail = dialogView.findViewById(R.id.etDialogEmail);
        EditText etPhone = dialogView.findViewById(R.id.etDialogPhone);
        Button btnSave = dialogView.findViewById(R.id.btnDialogSave);
        Button btnCancel = dialogView.findViewById(R.id.btnDialogCancel);

        // Set existing values
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etPhone.setText(user.getPhone());
        btnSave.setText("Update");

        AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.updateUser(user.getId(), name, email, phone);
            if (success) {
                Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
                loadUsers();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // Delete user with confirmation
    @Override
    public void onDeleteClick(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete " + user.getName() + "?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    boolean success = dbHelper.deleteUser(user.getId());
                    if (success) {
                        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                        loadUsers();
                    } else {
                        Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}