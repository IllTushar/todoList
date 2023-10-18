package com.example.todolist.rec_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Room.ResponseModel;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myViewHolder>{
    ArrayList<ResponseModel>list;
    Context context;

    public AdapterClass(ArrayList<ResponseModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
