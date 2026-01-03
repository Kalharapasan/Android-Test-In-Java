package com.example.a21sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Load students from database
        loadStudents();

        // Floating Action Button click listener
        fabAdd.setOnClickListener(v -> showAddDialog());
    }

    // Load all students from database
    private void loadStudents() {
        studentList = dbHelper.getAllStudents();
        adapter = new StudentAdapter(this, studentList, this);
        recyclerView.setAdapter(adapter);

        // Show message if no students
        if (studentList.isEmpty()) {
            Toast.makeText(this, "No students found. Add one!", Toast.LENGTH_SHORT).show();
        }
    }

    // Show dialog to add new student
    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_student, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etAge = view.findViewById(R.id.etAge);

        builder.setView(view)
                .setTitle("Add New Student")
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String ageStr = etAge.getText().toString().trim();

                    // Validation
                    if (name.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        int age = Integer.parseInt(ageStr);

                        // Create student object
                        Student student = new Student(name, email, age);

                        // Add to database
                        long id = dbHelper.addStudent(student);

                        if (id > 0) {
                            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                            loadStudents(); // Reload list
                        } else {
                            Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Please enter valid age", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    // Handle edit button click from adapter
    @Override
    public void onEditClick(Student student, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_student, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etAge = view.findViewById(R.id.etAge);

        // Pre-fill with current data
        etName.setText(student.getName());
        etEmail.setText(student.getEmail());
        etAge.setText(String.valueOf(student.getAge()));

        builder.setView(view)
                .setTitle("Edit Student")
                .setPositiveButton("Update", (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String ageStr = etAge.getText().toString().trim();

                    // Validation
                    if (name.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        int age = Integer.parseInt(ageStr);

                        // Update student object
                        student.setName(name);
                        student.setEmail(email);
                        student.setAge(age);

                        // Update in database
                        int result = dbHelper.updateStudent(student);

                        if (result > 0) {
                            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                            loadStudents(); // Reload list
                        } else {
                            Toast.makeText(this, "Error updating student", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Please enter valid age", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    // Handle delete button click from adapter
    @Override
    public void onDeleteClick(Student student, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Student")
                .setMessage("Are you sure you want to delete " + student.getName() + "?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Delete from database
                    dbHelper.deleteStudent(student);
                    Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                    loadStudents(); // Reload list
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close database connection
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}