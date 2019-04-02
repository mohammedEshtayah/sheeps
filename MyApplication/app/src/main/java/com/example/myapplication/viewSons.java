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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class viewSons  extends RecyclerView.Adapter< viewSons.ViewHolder > {

    private Context context;
    private Cursor cursor;
    private List<listSons> list = new ArrayList<>();
    public DB db = new DB();
    SimpleDateFormat Formatdate = new SimpleDateFormat("dd-MM-yyyy");
    long dates;
    @Override
    public int getItemCount() {
        return list.size();

    }
    public viewSons(List<listSons> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewSons.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewsons, parent, false);

        return new viewSons.ViewHolder(v);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull viewSons.ViewHolder holder, int position) {
        listSons lists=list.get(position);

        holder.ID_sons.setText(" "+Integer.toString(lists.ID_sons()));
        if(lists.Finished()) {
            holder.ID_mothers.setBackgroundColor(Color.RED);

        }
        holder.ID_mothers.setText(Integer.toString(lists.ID_mothers()));
        holder.date.setText(lists.date());
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ID_sons,ID_mothers,date;
        public Button delete;


        public ViewHolder(View itemView) {
            super(itemView);
            ID_sons = (TextView) itemView.findViewById(R.id.idsons);

            ID_mothers = (TextView) itemView.findViewById(R.id.idmother);
            date = (TextView) itemView.findViewById(R.id.date);

            delete = (Button) itemView.findViewById(R.id.delete);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    listSons lists=list.get(position);
                    db.db.execSQL("delete from Sons WHERE ID_sons = '" +lists.ID_sons()+ "' and Finished= '"+lists.Finished()+"';");
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,list.size());

                    Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}
