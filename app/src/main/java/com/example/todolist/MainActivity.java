package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

import com.example.todolist.Room.RequestModel;
import com.example.todolist.Room.ResponseModel;

import com.example.todolist.rec_view.AdapterClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fb;
    RequestModel requestModel;
    private AlertDialog dialog;
    AdapterClass adapterClass ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb = findViewById(R.id.floatingbtn);
        recyclerView = findViewById(R.id.recycler_view);
        requestModel=RequestModel.getRequestModel(MainActivity.this);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFloatingButtonFunction();
            }
        });
        callGetDataFunction(requestModel);

    }



    private void callGetDataFunction(RequestModel requestModel)
    {
        ArrayList<ResponseModel>list = (ArrayList<ResponseModel>) requestModel.roomInterface().getAllData();
        adapterClass= new AdapterClass(list,MainActivity.this);
        recyclerView.setAdapter(adapterClass);

    }

    @SuppressLint("MissingInflatedId")
    private void callFloatingButtonFunction(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.floating_dialog, null);
        EditText title  = dialogView.findViewById(R.id.title);
        EditText comments = dialogView.findViewById(R.id.comments);
        Button yes = dialogView.findViewById(R.id.yes);
        Button no  = dialogView.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String Title  = title.getText().toString().trim();
                String Comments  =  comments.getText().toString().trim();
                insertDataInsideTable(requestModel,Title,Comments);
                dialog.dismiss();
                callGetDataFunction(requestModel);
                adapterClass.notifyDataSetChanged();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        dialog = builder.create(); // Assign dialog here
        dialog.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void insertDataInsideTable(RequestModel requestModel, String title, String comments) {
        ResponseModel responseModel = new ResponseModel(title,comments);
        requestModel.roomInterface().insertTheEntity(responseModel);
        adapterClass.notifyItemInserted(adapterClass.getItemCount());
        adapterClass.notifyDataSetChanged();
    }
}