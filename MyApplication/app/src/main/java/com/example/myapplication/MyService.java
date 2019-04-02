package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    private DB db;
    private Cursor cursor;
    public MyService() {
        db=new DB(); 
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HashMap<String, String> map;
        cursor = db.db.rawQuery("select * from Mother", null);

            while (cursor.moveToNext()) {
                map = new HashMap<String, String>();
                map.put("ID_mothers"  , Integer.toString(cursor.getInt(cursor.getColumnIndex("ID_mothers"))));
                      map.put("date_Birth" , cursor.getString(cursor.getColumnIndex("date_Birth")));
                map.put("sherbet" ,   cursor.getString(cursor.getColumnIndex("sherbet")));
                map.put("Intestinal" ,   cursor.getString(cursor.getColumnIndex("Intestinal")));
                map.put("vaccination" ,  cursor.getString(cursor.getColumnIndex("vaccination")));
                map.put("Etswing" ,  cursor.getString(cursor.getColumnIndex("Etswing")));
                map.put("Ivomac" ,  cursor.getString(cursor.getColumnIndex("Ivomac")));
                map.put("pregnant" ,   cursor.getString(cursor.getColumnIndex("pregnant")));




/*


        try {
            if ( C_dates(map.get("vaccination")) >=150 && C_dates(map.get("date_Birth")) >=60    && C_dates(map.get("date_Birth")) < 61 )
                sendNotification(map.get("ID_mothers"),"Etswing");//Etswing

             if (  C_dates(map.get("vaccination")) >=120&&   C_dates(map.get("vaccination")) >121 &&  map.get("pregnant").equals("true"))
                 sendNotification(map.get("ID_mothers"),"sherbet");//sherbet

            if (   C_dates(map.get("vaccination")) >=127&& C_dates(map.get("vaccination")) >128  &&  map.get("pregnant").equals("true"))
                sendNotification(map.get("ID_mothers"),"Intestinal");//Intestinal

            if (  C_dates(map.get("Etswing")) >=15 &&C_dates(map.get("Etswing")) >16 )
                sendNotification(map.get("ID_mothers"),"vaccination");//vaccination

            if (  C_dates(map.get("vaccination")) >=33 && C_dates(map.get("vaccination")) <34 )
                sendNotification(map.get("ID_mothers"),"vaccination");//vaccination

            if (    C_dates(map.get("vaccination")) >=60&&C_dates(map.get("vaccination")) >61&&  map.get("pregnant").equals("true"))
             sendNotification(map.get("ID_mothers"),"Ivomac");//Ivomac


        } catch (Exception e) {
            e.printStackTrace();
        }
  */
            }



         Toast.makeText(getApplicationContext(),"aaaaa",Toast.LENGTH_LONG).show();
            stopSelf();
        return START_NOT_STICKY;
    }
    private long C_dates(String date2) {
        long diff=0;
        try {
            SimpleDateFormat Formatdate = new SimpleDateFormat("dd-MM-yyyy");

            diff = new Date().getTime() - Formatdate.parse(date2).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return    TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void sendNotification(String title,String state){
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notify = new NotificationCompat.Builder(MyService.this).
                setSmallIcon(R.drawable.sheep)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.sheep))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(state)
                .setVibrate(new long[] { 0, 500, 100, 500, 100,500 })
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentIntent(null).build();
        notify.flags|=Notification.FLAG_AUTO_CANCEL;
        NotificationManager no=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        no.notify(0,notify);



    }
    @Override
    public void onDestroy() {
     /*  Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        calendar.add(Calendar.DATE, 1);

        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(alarm.RTC_WAKEUP, calendar.getTimeInMillis(),
                PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0));

*/

        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(alarm.RTC_WAKEUP, System.currentTimeMillis()+100,
                PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0));



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
