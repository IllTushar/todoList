package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolist.dialog.CustomDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb = findViewById(R.id.floatingbtn);
        recyclerView = findViewById(R.id.recycler_view);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFloatingButtonFunction();
            }
        });
    }
    @SuppressLint("MissingInflatedId")
    private void callFloatingButtonFunction(){
        CustomDialog customDialog = new CustomDialog(MainActivity.this);
        customDialog.show();
    }
}