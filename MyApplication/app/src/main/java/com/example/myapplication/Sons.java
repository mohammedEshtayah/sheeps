package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Sons extends AppCompatActivity {
    private Cursor cursor;
    private RecyclerView recyclerView;
    private DB db;
    private List<listSons> listSons= new ArrayList<>();
    private listSons Clistsons;
    private  viewSons view;
    private RadioGroup radioSexGroup ;
    private RadioButton radioSexButton;
    private String att;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sons);
        radioSexGroup = (RadioGroup) findViewById(R.id.group);
         db=new DB();
        recyclerView=(RecyclerView)findViewById(R.id.srecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewall();
    }


    public void viewall(){
        cursor=db.db.rawQuery("select * from Sons",null);
        while (cursor.moveToNext()) {
            Clistsons = new listSons(cursor.getInt(cursor.getColumnIndex("ID_mothers")),
                    cursor.getInt(cursor.getColumnIndex("ID_sons")),
                    cursor.getString(cursor.getColumnIndex("date_Birth")),
                   Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex("Finished"))));
            listSons.add(Clistsons);

        }
         view = new viewSons(listSons, getApplicationContext());
          recyclerView.setAdapter(view);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listSons.clear();

               recyclerView.removeAllViewsInLayout();
                String[] id = newText.toString().split("-");
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
              att=  radioSexButton.getText().toString();
              if(!newText.isEmpty()&& ! att.isEmpty()&& id!=null) {
                  att = "ID_" + att;
                  for (int i = 0; i < id.length; i++) {
                      Cursor cursor = db.db.rawQuery("select * from Sons where " + att + " = " + id[i] + "", null);
                      while (cursor.moveToNext()) {
                          Clistsons = new listSons(cursor.getInt(cursor.getColumnIndex("ID_mothers")),
                                  cursor.getInt(cursor.getColumnIndex("ID_sons")),
                                  cursor.getString(cursor.getColumnIndex("date_Birth")),
                                  Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("Finished"))));
                          listSons.add(Clistsons);

                      }


                  }
                  view = new viewSons(listSons, getApplicationContext());
                  recyclerView.setAdapter(view);


              }

                if(newText.isEmpty()){
                    viewall();

                }
                return false;
            }
        });



        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setIconifiedByDefault(false);

        return true;


    }

}
