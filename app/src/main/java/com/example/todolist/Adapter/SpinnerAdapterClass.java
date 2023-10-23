package com.example.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todolist.R;

import java.util.ArrayList;

public class SpinnerAdapterClass extends BaseAdapter {
    ArrayList<String> list;
    Context context;

    public SpinnerAdapterClass(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_xml, viewGroup, false);
        }
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(list.get(i));
        return view;
    }
}
