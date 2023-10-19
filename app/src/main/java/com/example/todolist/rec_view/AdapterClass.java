package com.example.todolist.rec_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.Room.ResponseModel;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myViewHolder> {
    ArrayList<ResponseModel> list;
    Context context;

    public AdapterClass(ArrayList<ResponseModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_xml, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ResponseModel model = list.get(position);
        holder.bind(model);

        holder.textView.setText(list.get(position).getTitle());
        holder.comments.setText(list.get(position).getComments());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView comments;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title1);
            comments = itemView.findViewById(R.id.comments1);

        }

        public void bind(ResponseModel item) {
            textView.setText(item.getTitle());
            comments.setText(item.getComments());
        }
    }
}
