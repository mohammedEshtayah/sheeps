package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class viewOne extends RecyclerView.Adapter< viewOne.ViewHolder > {
private List<listOneM> list;
private Context context;
private Cursor cursor;
    private listMothers listMothers;
    private List<listMothers> listMother= new ArrayList<>();
    private DB db=new DB();
    SimpleDateFormat Formatdate = new SimpleDateFormat("dd-MM-yyyy");
    long dates;

public  viewOne(List<listOneM> list , Context context){

        this.list=list;
        this.context=context;
        }
@NonNull
@Override
public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmother,parent,false);

        return new ViewHolder(v);
        }

@RequiresApi(api = Build.VERSION_CODES.O)
@Override
public void onBindViewHolder(@NonNull viewOne.ViewHolder holder, int position) {

        listOneM lists=list.get(position);
    viewall(lists);

      dates= C_dates(listMothers.date_Birth());

    switch(lists.Type())
    {
        case "Etswing":
            try {
                if (  C_dates(listMothers.date_Birth()) >60 &&  listMothers.pregnant()
                        &&  C_dates(lists.setType()) >0&&C_dates(lists.setType()) <31
                        && new Date().getTime()> Formatdate.parse(lists.setType()).getTime())

                    holder.setType.setBackgroundColor(Color.RED);

            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case "sherbet":
            try {
            if (  C_dates(listMothers.vaccination()) >120&&  listMothers.pregnant()
                    &&  C_dates(lists.setType()) >0&&C_dates(lists.setType()) <31
                    && new Date().getTime()> Formatdate.parse(lists.setType()).getTime())

                holder.setType.setBackgroundColor(Color.RED);

        } catch (Exception e) {
            e.printStackTrace();
        }
            break;
        case "Intestinal":
            //m3we

            try {
                if (  C_dates(listMothers.vaccination()) >127&&    listMothers.pregnant()
                        &&  C_dates(lists.setType()) >0&&C_dates(lists.setType()) <31
                        && new Date().getTime()> Formatdate.parse(lists.setType()).getTime())

                    holder.setType.setBackgroundColor(Color.RED);

            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case "vaccination":

            try {
                if (  C_dates(listMothers.Etswing()) >15&&    listMothers.pregnant()
                        &&  C_dates(lists.setType()) >0&&C_dates(lists.setType()) <31
                        && new Date().getTime()> Formatdate.parse(lists.setType()).getTime())

                    holder.setType.setBackgroundColor(Color.RED);

            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case "Ivomac":


            try {
                if (  C_dates(listMothers.date_Birth()) >60&&    listMothers.pregnant()
                        &&  C_dates(lists.setType()) >0&&C_dates(lists.setType()) <31
                        && new Date().getTime()> Formatdate.parse(lists.setType()).getTime())

                    holder.setType.setBackgroundColor(Color.RED);

            } catch (Exception e) {
                e.printStackTrace();
            }
            break;



    }










        holder.ID_mothers.setText(Integer.toString(lists.ID_mothers()));

    if(lists.Type().equals("date_Birth"))
    holder.setType.setText("تاريخ التلقيح");
    if(lists.Type().equals("Intestinal"))
        holder.setType.setText("معوي");
    if(lists.Type().equals("sherbet"))
        holder.setType.setText("شربات");
    if(lists.Type().equals("Ivomac"))
        holder.setType.setText("أيفوميك");
    if(lists.Type().equals("Etswing"))
        holder.setType.setText("أسفنج");
    if(lists.Type().equals("vaccination"))
        holder.setType.setText("تلقيح");



    holder.Type.setText(lists.setType() );


        /*   */
        }

    private long C_dates(String date2) {
        long diff=0;
        try {

              diff = new Date().getTime() - Formatdate.parse(date2).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return    TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void viewall(listOneM slist){
        cursor=db.db.rawQuery("select * from Mother where "+slist.ID_mothers()+"",null);
        while (cursor.moveToNext()) {
            listMothers = new listMothers(cursor.getInt(cursor.getColumnIndex("ID_mothers")),
                    cursor.getString(cursor.getColumnIndex("date_Birth")),
                    cursor.getString(cursor.getColumnIndex("sherbet")),
                    cursor.getString(cursor.getColumnIndex("Intestinal")),
                    cursor.getString(cursor.getColumnIndex("vaccination")),
                    cursor.getString(cursor.getColumnIndex("Etswing")), cursor.getString(cursor.getColumnIndex("Ivomac")),
                    true);


        }

    }
@Override
public int getItemCount() {
        return list.size();

        }

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView ID_mothers,setType,Type;




    private boolean pregnant;
    public ViewHolder(View itemView) {
        super(itemView);

        ID_mothers=(TextView)itemView.findViewById(R.id.idmother);
        Type=(TextView)itemView.findViewById(R.id.Type);
       setType=(TextView)itemView.findViewById(R.id.setType);




        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= getAdapterPosition();
                listOneM lists=list.get(position);

                  /*  Intent Buy=new Intent(context,ToBuy.class);
                    Buy.putExtra("Abstract",lists.getAbstract());
                    Buy.putExtra("Titel",lists.getTitel());
                    Buy.putExtra("NoCopie",lists.getNoCopie());
                    Buy.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(Buy);
                    */

            }
        });
    }
}
}