package com.example.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    private static final int REQUEST_DB = 10;
    private  DB db;
private  RecyclerView recyclerView;
private NavigationView navigationView;
    private DB database;
    private List<listMothers> listMother= new ArrayList<>();
    private listMothers listMothers;
    private view view;
    private   Cursor cursor;
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
         startService(new Intent(this, MyService.class));
      /*  Intent brod=new Intent(this, MyReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this, 0, brod, PendingIntent.FLAG_NO_CREATE) != null);
        if(alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, brod, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000), 1800000, pendingIntent);
        }
        MyReceiver myReceiver =new MyReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(String.valueOf(AlarmManager.RTC_WAKEUP));
        this.registerReceiver(myReceiver, filter);

         PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0,brod, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + ( 1000), pendingIntent);

*/

            db=new DB();

        recyclerView=(RecyclerView)findViewById(R.id.srecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        viewall();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
        case R.id.ADU:
        startActivity(new Intent(getApplicationContext(),ADU.class));
        break;

        case R.id.Drugs:
            startActivity(new Intent(getApplicationContext(),Drugs.class));

        break;

            case R.id.test:
                startActivity(new Intent(getApplicationContext(),input_test.class));

                break;


            case  R.id.State:
                startActivity(new Intent(getApplicationContext(),view_one.class));

                break;

            case  R.id.notes:
                startActivity(new Intent(getApplicationContext(),Notes.class));

                break;
            case  R.id.Sons:
                startActivity(new Intent(getApplicationContext(),Sons.class));

                break;
    }
        return false;

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
                recyclerView.removeAllViewsInLayout();
                String[] id = newText.toString().split("-");
                listMother.clear();
for(int i =0; i<id.length;i++) {
    Cursor cursor = db.db.rawQuery("select * from mother where ID_mothers='" + id[i] + "'", null);
    while (cursor.moveToNext()) {
        listMothers = new listMothers(cursor.getInt(cursor.getColumnIndex("ID_mothers")),
        cursor.getString(cursor.getColumnIndex("date_Birth")),
                cursor.getString(cursor.getColumnIndex("sherbet")),
                cursor.getString(cursor.getColumnIndex("Intestinal")),
                cursor.getString(cursor.getColumnIndex("vaccination")),
                cursor.getString(cursor.getColumnIndex("Etswing")),
                cursor.getString(cursor.getColumnIndex("Ivomac")),
                true);

        listMother.add(listMothers);
    }

}
                view=new view(listMother,getApplicationContext());
                recyclerView.setAdapter(view);



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

   public void viewall(){
       cursor=db.db.rawQuery("select * from Mother",null);
       while (cursor.moveToNext()) {
  listMothers = new listMothers(cursor.getInt(cursor.getColumnIndex("ID_mothers")),
          cursor.getString(cursor.getColumnIndex("date_Birth")),
                   cursor.getString(cursor.getColumnIndex("sherbet")),
                   cursor.getString(cursor.getColumnIndex("Intestinal")),
                   cursor.getString(cursor.getColumnIndex("vaccination")),
                    cursor.getString(cursor.getColumnIndex("Etswing")), cursor.getString(cursor.getColumnIndex("Ivomac")),
          Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("pregnant"))));
           listMother.add(listMothers);

       }
       view = new view(listMother, getApplicationContext());
       recyclerView.setAdapter(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_DB)
        {

            for (int i =0 ; i < grantResults.length; i++)
                if (grantResults[i]!= PackageManager.PERMISSION_GRANTED)
                    return;
db=new DB();


        }
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
    }

}
