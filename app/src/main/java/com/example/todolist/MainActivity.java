package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.*;

import com.example.todolist.Adapter.SpinnerAdapterClass;
import com.example.todolist.Room.RequestModel;
import com.example.todolist.Room.ResponseModel;

import com.example.todolist.rec_view.AdapterClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AdapterClass.OnItemClick {
    RecyclerView recyclerView;
    FloatingActionButton fb;
    RequestModel requestModel;
    private AlertDialog dialog;
    AdapterClass adapterClass;
    Spinner spinner;
    ArrayList<ResponseModel> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb = findViewById(R.id.floatingbtn);
        recyclerView = findViewById(R.id.recycler_view);
        requestModel = RequestModel.getRequestModel(MainActivity.this);
        spinner = findViewById(R.id.spinner);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFloatingButtonFunction();
            }
        });
        callGetDataFunction(list);
        spinnerFunction(list);

    }

    private void spinnerFunction(ArrayList<ResponseModel> list) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("filter");
        list1.add("Recent");
        list1.add("Sequence");
        SpinnerAdapterClass adapterClass1 = new SpinnerAdapterClass(list1, MainActivity.this);
        spinner.setAdapter(adapterClass1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                ArrayList<ResponseModel> filterList = getData();
                callFilterFunction(selectedItem, filterList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private ArrayList<ResponseModel> getData() {
        ArrayList<ResponseModel> listing = (ArrayList<ResponseModel>) requestModel.roomInterface().getAllData();
        return listing;
    }

    private void callFilterFunction(String selectedItem, ArrayList<ResponseModel> list) {
        ArrayList<ResponseModel> filterList = new ArrayList<>();
        if (selectedItem.equals("Recent")) {
            for (int i = list.size() - 1; i >= 0; i--) {
                filterList.add(list.get(i));
            }
            spinnerFunctionData(filterList);
        } else if (selectedItem.equals("Sequence")) {
            for (int i =0;i<list.size();i++){
                filterList.add(list.get(i));
            }
            spinnerFunctionData(filterList);
        }

    }

    private void spinnerFunctionData(ArrayList<ResponseModel> filtering) {
        adapterClass = new AdapterClass(filtering, MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(adapterClass);
    }

    private void callGetDataFunction(ArrayList<ResponseModel> filterList) {
        filterList = (ArrayList<ResponseModel>) requestModel.roomInterface().getAllData();
        adapterClass = new AdapterClass(filterList, MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(adapterClass);
    }

    @SuppressLint("MissingInflatedId")
    private void callFloatingButtonFunction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.floating_dialog, null);
        EditText title = dialogView.findViewById(R.id.title);
        EditText comments = dialogView.findViewById(R.id.comments);
        Button yes = dialogView.findViewById(R.id.yes);
        Button no = dialogView.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String Title = title.getText().toString().trim();
                String Comments = comments.getText().toString().trim();
                if (validation(Title, Comments)) {
                    insertDataInsideTable(requestModel, Title, Comments);
                    dialog.dismiss();
                    callGetDataFunction(list);
                    adapterClass.notifyDataSetChanged();
                }
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

        ResponseModel responseModel = new ResponseModel(title, comments);
        requestModel.roomInterface().insertTheEntity(responseModel);
        adapterClass.notifyItemInserted(adapterClass.getItemCount());
        adapterClass.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void itemClickListner(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the dialog title, message, and other properties
        builder.setTitle("Delete")
                .setMessage("Are you sure to remove it?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // This is the code that will be executed when the user clicks 'OK'
                        // You can add your action here
                        ResponseModel responseModel = new ResponseModel(id);
                        requestModel.roomInterface().deleteTheEntity(responseModel);
                        callGetDataFunction(list);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // This is the code that will be executed when the user clicks 'Cancel'
                        dialog.dismiss();
                    }
                });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private boolean validation(String title, String comments) {

        if (title.isEmpty()) {
            toast("Enter Title");
            return false;
        }
        if (comments.isEmpty()) {
            toast("Enter Comment");
            return false;
        }
        return true;
    }

    private void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(int id, String Title, String Comments) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.floating_dialog, null);
        EditText title = dialogView.findViewById(R.id.title);
        EditText comments = dialogView.findViewById(R.id.comments);
        Button yes = dialogView.findViewById(R.id.yes);
        Button no = dialogView.findViewById(R.id.no);
        title.setText(Title);
        comments.setText(Comments);
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String Title = title.getText().toString().trim();
                String Comments = comments.getText().toString().trim();
                if (validation(Title, Comments)) {
                    ResponseModel responseModel = new ResponseModel(id, Title, Comments);
                    requestModel.roomInterface().updateTheEntity(responseModel);
                    dialog.dismiss();
                    callGetDataFunction(list);
                    adapterClass.notifyDataSetChanged();
                }
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
}