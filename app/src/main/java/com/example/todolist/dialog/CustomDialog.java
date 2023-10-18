package com.example.todolist.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.todolist.R;

public class CustomDialog extends Dialog {
    EditText title,comments;
    Button yes ,no;
    public CustomDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.floating_dialog);
        title  = findViewById(R.id.title);
        comments = findViewById(R.id.comments);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
