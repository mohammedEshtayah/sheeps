package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {
    private  DB db;
    private RecyclerView recyclerView;
    private List<String> listNotes= new ArrayList<>();
    private ViewNotes viewNotes;
    private Cursor cursor;
    private Button Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        db=new DB();
        Add=(Button)findViewById(R.id.ADD);
        recyclerView=(RecyclerView)findViewById(R.id.Nrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

Add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),add_Notes.class));
    }
});
        viewall();
    }
    public void viewall(){
        cursor = db.db.rawQuery("select * from notes", null);
        while (cursor.moveToNext()) {

            listNotes.add(cursor.getString(0));

        }
        viewNotes = new ViewNotes(listNotes, getApplicationContext());
        recyclerView.setAdapter(viewNotes);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
