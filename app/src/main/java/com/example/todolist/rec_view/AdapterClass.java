package com.example.todolist.rec_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.Room.ResponseModel;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myViewHolder> {
    ArrayList<ResponseModel> list;
    Context context;
   public interface OnItemClick{
         void itemClickListner(int id);
         void updateList(int id,String title,String comments);
     }
    OnItemClick onItemClick;


    public AdapterClass(ArrayList<ResponseModel> list, Context context,OnItemClick onItemClick) {
        this.list = list;
        this.context = context;
        this.onItemClick =onItemClick;
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_xml, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.comments.setText(list.get(position).getComments());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (onItemClick!=null){
                   onItemClick.updateList(list.get(position).getId(),list.get(position).getTitle(),list.get(position).getComments());
               }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.itemClickListner(list.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView comments;
        ImageView update, remove;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title1);
            comments = itemView.findViewById(R.id.comments1);
            update = itemView.findViewById(R.id.edit);
            remove = itemView.findViewById(R.id.remove);
        }

    }
}
