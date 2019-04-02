package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class add_Notes extends AppCompatActivity {
    private Button Add;
    private EditText NOTE;
    private DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__notes);
        Add=(Button)findViewById(R.id.addNote);
        NOTE=(EditText)findViewById(R.id.NOTE);
        db=new DB();
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!NOTE.getText().toString().isEmpty())    db.db.execSQL("insert into notes values('"+NOTE.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(),Notes.class));
    }
}
