package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class view extends RecyclerView.Adapter< view.ViewHolder > {
    private List<listMothers> list;
    private Context context;

    SimpleDateFormat Formatdate = new SimpleDateFormat("dd-MM-yyyy");
    public  view(List<listMothers> list , Context context){

        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull view.ViewHolder holder, int position) {
        listMothers lists=list.get(position);
        DateFormat currentDateString = new SimpleDateFormat("dd-MM-yyyy");


                 try {
                    if (  C_dates(lists.date_Birth()) >60
                           &&  C_dates(lists.Etswing()) <0
                            && C_dates(lists.Etswing()) >31
                             && new Date().getTime()> Formatdate.parse(lists.Etswing()).getTime()
                     )

                        holder.Etswing.setBackgroundColor(Color.GRAY);




                    if (  C_dates(lists.vaccination()) >120&&  lists.pregnant()
                            &&  C_dates(lists.sherbet()) >0&&C_dates(lists.sherbet()) <31
                            && new Date().getTime()> Formatdate.parse(lists.sherbet()).getTime())

                        holder.sherbet.setBackgroundColor(Color.RED);




                    if (  C_dates(lists.vaccination()) >127&&    lists.pregnant()
                            &&  C_dates(lists.Intestinal()) >0&&C_dates(lists.Intestinal()) <31
                            && new Date().getTime()> Formatdate.parse(lists.Intestinal()).getTime())

                        holder.Intestinal.setBackgroundColor(Color.BLUE);




                    if ( lists.pregnant() &&C_dates(lists.vaccination())<150)
                        holder.vaccination.setBackgroundColor(Color.GREEN);



                    if (  C_dates(lists.date_Birth()) >60&&    lists.pregnant()
                            &&  C_dates(lists.Ivomac()) >0&&C_dates(lists.Ivomac()) <31
                            && new Date().getTime()> Formatdate.parse(lists.Ivomac()).getTime())

                        holder.Ivomac.setBackgroundColor(Color.YELLOW);


                 } catch (Exception e) {
                    e.printStackTrace();
                }






        holder.ID_mothers.setText(Integer.toString(lists.ID_mothers()));
        holder.date_Birth.setText(lists.date_Birth() );
        holder.sherbet.setText(lists.sherbet());
if(lists.pregnant())  holder.vaccination.setBackgroundColor(Color.YELLOW);
        holder.Ivomac.setText(lists.Ivomac());
        holder.Etswing.setText( lists.Etswing());
        holder.Intestinal.setText(lists.Intestinal());
        holder.vaccination.setText(lists.vaccination());

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
    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView  ID_mothers,date_Birth,vaccination,sherbet,Ivomac,Etswing,Intestinal;




        private boolean pregnant;
        public ViewHolder(View itemView) {
            super(itemView);

            ID_mothers=(TextView)itemView.findViewById(R.id.idmother);
            date_Birth=(TextView)itemView.findViewById(R.id.dateBirth);
            sherbet=(TextView)itemView.findViewById(R.id.sherbet);
            Ivomac=(TextView)itemView.findViewById(R.id.ivomac);
            Etswing=(TextView)itemView.findViewById(R.id.Etswing);
            vaccination=(TextView)itemView.findViewById(R.id.vaccination);
            Intestinal=(TextView)itemView.findViewById(R.id.Intestinal);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    listMothers lists=list.get(position);

                Intent Sons=new Intent(context,add_sons.class);
                    Sons.putExtra("ID_mothers",lists.ID_mothers());

                    Sons.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(Sons);


                }
            });
        }
    }
}