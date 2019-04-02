package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Drugs extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button date,change;
    private RadioButton readText;
    private    int selectedId;
    private EditText ids;
    private SimpleDateFormat dateFormat;
    private DB db;
    private String attribute, currentDateString;
    private RadioGroup group;
    private Spinner Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
         dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       Type  =(Spinner)findViewById(R.id.spinner);
        String[] type  = { "date_Birth", "Intestinal", "sherbet", "Ivomac", "Etswing","vaccination"};
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type.setAdapter(aa);
        db=new DB();
        change=(Button)findViewById(R.id.dchange);
        ids=(EditText)findViewById(R.id.NOTE);

         date=(Button)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new getDate();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 attribute= Type.getSelectedItem().toString();

                String[] id = ids.getText().toString().split("-");
try {


    if (id != null && attribute != null && currentDateString != null) {
        for (int i = 0; i < id.length; i++) {

            db.db.execSQL("UPDATE mother SET " + attribute + " ='" + currentDateString + "'  WHERE ID_mothers = " + Integer.parseInt(id[i]) + ";");
            ids.setError(null);
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
        }
    } else {


        ids.setError("");
    }


} catch (Exception e){
                ids.setError("");

            }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
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
