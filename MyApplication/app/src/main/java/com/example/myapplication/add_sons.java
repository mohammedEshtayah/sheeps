package com.example.myapplication;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_sons extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    private Button date,add;
    private String  currentDateString;
    private EditText ids;
    private SimpleDateFormat dateFormat;
    private DB db;
    private int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sons);
        add = (Button) findViewById(R.id.addsons);
        db = new DB();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      Id=  getIntent().getIntExtra("ID_mothers",0);
        ids = (EditText) findViewById(R.id.numbersSons);
        date=(Button)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new getDate();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try{

                String[] id = ids.getText().toString().split("-");


                if (id[0] != null&& !currentDateString.equals(null)) {
                    boolean flag=true;

                    Cursor cursor = db.db.rawQuery("select ID_sons from Sons", null);
                    while (cursor.moveToNext()) {

                        for (int i = 0; i < id.length; i++) {
                            if (cursor.getInt(cursor.getColumnIndex("ID_sons")) == Integer.parseInt(id[i])) {
                                flag = false;

                            }
                        }

                    }
                    if(flag) {

                        for (int i = 0; i < id.length; i++) {

                            db.db.execSQL("insert into Sons values (" + id[0] + "," + Id + ",'" + currentDateString + "','false')");
                            db.db.execSQL("UPDATE mother SET pregnant = 'false'  WHERE ID_mothers = " + Id + ";");
                            ids.setError(null);
                            Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_LONG).show();

                        }
                    }else {

                        Toast.makeText(getApplicationContext(), "يوجد رقم من احد الارقام المدخله", Toast.LENGTH_LONG).show();

                    }
                } else {


                    ids.setError("");
                }
            }catch (Exception e){
                ids.setError("");

            } }

        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = dateFormat.format(c.getTime());


        date.setText(currentDateString);
    }
}
