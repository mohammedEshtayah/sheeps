package com.example.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class input_test extends AppCompatActivity {
    private Button add;

    private EditText ids;
     private DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_test);
        add = (Button) findViewById(R.id.addd);
          db = new DB();
        ids = (EditText) findViewById(R.id.numbersMo);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String[] id = ids.getText().toString().split("-");


                if (id[0] != null) {
                    for (int i = 0; i < id.length; i++) {

                        db.db.execSQL("UPDATE mother SET pregnant = 'true'  WHERE ID_mothers = " + Integer.parseInt(id[i]) + ";");
                        ids.setError(null);
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();

                        Cursor cursor = db.db.rawQuery("select * from Mother", null);
                        while (cursor.moveToNext()) {
                            Log.d("ssssss",cursor.getString(cursor.getColumnIndex("pregnant"))  );

                        }
                    }
                } else {


                    ids.setError("");
                }
            }

        });
    }
}