package com.example.todoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    ArrayList<String> tasks;
    ArrayList<Integer> taskIds;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        tasks = new ArrayList<>();
        taskIds = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        binding.taskListView.setAdapter(adapter);

        loadTasks();

        binding.addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = binding.taskInput.getText().toString();
                if (!task.isEmpty()) {
                    boolean insert = databaseHelper.insertTask(getIntent().getStringExtra("email"), task);
                    if (insert) {
                        Toast.makeText(MainActivity.this, "Task Added!", Toast.LENGTH_SHORT).show();
                        loadTasks();
                        binding.taskInput.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to Add Task", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int taskId = taskIds.get(position);
                boolean delete = databaseHelper.deleteTask(taskId);
                if (delete) {
                    Toast.makeText(MainActivity.this, "Task Completed!", Toast.LENGTH_SHORT).show();
                    loadTasks();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to Complete Task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadTasks() {
        tasks.clear();
        taskIds.clear();

        Cursor cursor = databaseHelper.getTasks(getIntent().getStringExtra("email"));
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Tasks Available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                taskIds.add(cursor.getInt(0));
                tasks.add(cursor.getString(2));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
