package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ADU extends AppCompatActivity {

private Button Add,Update,Delete;
private EditText no,no2,noc;
    private  DB db;
      DateFormat dateFormat;
    private String NO;
    private DB database;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DB();
        setContentView(R.layout.activity_adu);
        final java.sql.Date date =  new java.sql.Date(2000,1,1);
          dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Add=(Button)findViewById(R.id.add);
        Update=(Button)findViewById(R.id.update);
        Delete=(Button)findViewById(R.id.delete);
        no=(EditText)findViewById(R.id.noMother);
        no2=(EditText)findViewById(R.id.noMother2);
        noc=(EditText)findViewById(R.id.changeno);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                NO=no.getText().toString();
                boolean flag=true;
                if( ! NO.isEmpty()){
                    Cursor cursor = db.db.rawQuery("select ID_mothers from Mother", null);
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(cursor.getColumnIndex("ID_mothers")) == Integer.parseInt(NO)) {
                            flag=false;

                        }

                    }
                    if(flag) {

                        ContentValues cv = new ContentValues();
                        cv.put("ID_mothers", NO);
                        cv.put("date_Birth", "00-00-0000");
                        cv.put("sherbet", "00-00-0000");
                        cv.put("Ivomac", "00-00-0000");
                        cv.put("Etswing", "00-00-0000");
                        cv.put("vaccination", "00-00-0000");
                        cv.put("Intestinal", "00-00-0000");
                        cv.put("pregnant", false);
                        db.db.insert("Mother", null, cv);
                        Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }else {

                        Toast.makeText(getApplicationContext(),"This exists",Toast.LENGTH_LONG).show();

                    }
                }else {
                    no.setError("");


                }

            }
        });
       Delete.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
               try {


                   NO = no.getText().toString();
                   if (!NO.isEmpty()) {
                       db.db.execSQL("delete from mother WHERE ID_mothers = " + Integer.parseInt(NO) + "  ;");
                       db.db.execSQL("UPDATE Sons SET  Finished = 'true'  WHERE ID_mothers = " + Integer.parseInt(NO) + "    ;");

                       Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       finish();

                   } else {
                       no.setError("");


                   }
               }catch (Exception e){
                   no.setError("");

               }

            }
        });

       Update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String NO2=no2.getText().toString();
               NO=noc.getText().toString();
               boolean s=false;
               try {
                   if( ! NO.isEmpty()&&!NO2.isEmpty()){
                       db.db.execSQL("UPDATE mother SET  ID_mothers =" + NO + "  WHERE ID_mothers = " + Integer.parseInt(NO2) +" ;");
                       db.db.execSQL("UPDATE Sons SET  ID_mothers =" + NO + "  WHERE ID_mothers = " + Integer.parseInt(NO2) + " AND Finished = '"+s+"' ;");

                       Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       finish();
                   }else {
                       noc.setError("");


                   }
               }catch (Exception e){
                   noc.setError("");}


           }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
