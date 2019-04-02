package com.example.myapplication;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

public class DB {
    public SQLiteDatabase db;
    private  File file;
    public  DB(){
        file = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), "/project-android");

        if (!file.exists())  file.mkdir();
        File myFile = new File(file.getAbsolutePath()+"/DB.db");

        db= SQLiteDatabase .openOrCreateDatabase( myFile.getPath() ,null);

        db.execSQL("create table IF NOT EXISTS Mother(ID_mothers number,date_Birth varchar(20)," +
                "sherbet varchar(20),Intestinal varchar(20),Ivomac varchar(20),Etswing varchar(20),vaccination varchar(20),pregnant boolean /*,CONSTRAINT PK_ID PRIMARY key (ID_Mothers) */);");




        db.execSQL("   create table IF NOT EXISTS Sons(ID_sons number, ID_mothers number,date_Birth,Finished boolean  );");


        db.execSQL("   create table IF NOT EXISTS notes(note varchar(1000));");





    }

}