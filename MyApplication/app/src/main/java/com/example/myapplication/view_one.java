package com.example.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class view_one extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private  DB db;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private DB database;
    private List<listOneM> listOneM= new ArrayList<>();
    private listOneM listOne;
    private viewOne viewone;
    private Cursor cursor;
   private String att;
    private Spinner Types;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one);
        recyclerView=(RecyclerView)findViewById(R.id.srecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Types  =(Spinner)findViewById(R.id.spinner2);
        String[] type  = { "تاريخ التلقيح", "معوي", "شربات", "أيفوميك", "أسفنج","تلقيح"};
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Types.setAdapter(aa);
        Types.setOnItemSelectedListener(this);
        db=new DB();
        att="date_Birth";

        viewall(att);
    }

    private void viewall(String att) {
        cursor=db.db.rawQuery("select ID_mothers , "+att+" from Mother",null);
        while (cursor.moveToNext()) {
            listOne = new listOneM(cursor.getInt(cursor.getColumnIndex("ID_mothers")),

                    cursor.getString(cursor.getColumnIndex(att))  ,att);
            listOneM.add(listOne);

        }
        viewone = new viewOne(listOneM, getApplicationContext());
        recyclerView.setAdapter(viewone);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        recyclerView.removeAllViewsInLayout();
        listOneM.clear();
        att=parent.getItemAtPosition(position).toString();


        if(att.equals("تاريخ التلقيح"))
      viewall( "date_Birth");
        if(att.equals("معوي"))
            viewall( "Intestinal");
        if(att.equals("شربات"))
            viewall( "sherbet");
        if(att.equals("أيفوميك"))
            viewall( "Ivomac");
        if(att.equals("أسفنج"))
            viewall( "Etswing");
        if(att.equals("تلقيح"))
            viewall( "vaccination");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.removeAllViewsInLayout();

                listOneM.clear();

                if(!newText.isEmpty()){
                    String[] id = newText.toString().split("-");
                    if(att.equals("تاريخ التلقيح"))
                        att="date_Birth";
                    if(att.equals("معوي"))
                        att="Intestinal";
                    if(att.equals("شربات"))
                        att="sherbet";
                    if(att.equals("أيفوميك"))
                        att="Ivomac";
                    if(att.equals("أسفنج"))
                        att="Etswing";
                    if(att.equals("تلقيح"))
                        att="vaccination";

                    for(int i =0; i<id.length;i++) {
                    cursor=db.db.rawQuery("select ID_mothers , "+att+" from Mother where ID_mothers="+id[i]+"",null);
                    while (cursor.moveToNext()) {
         listOne = new listOneM(cursor.getInt(cursor.getColumnIndex("ID_mothers")), cursor.getString(cursor.getColumnIndex(att))  ,att);
                        listOneM.add(listOne);

                    }

                }


            viewone = new viewOne(listOneM, getApplicationContext());
                    recyclerView.setAdapter(viewone);





                }else {   viewall(att);}
                return false;
            }
        });





        return true;


    }

}
