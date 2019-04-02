package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewNotes extends RecyclerView.Adapter< ViewNotes.ViewHolder > {

    private Context context;
    private Cursor cursor;
    private List<String> list = new ArrayList<>();
    public DB db = new DB();
    SimpleDateFormat Formatdate = new SimpleDateFormat("dd-MM-yyyy");
    long dates;

    public ViewNotes(List<String> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewNotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewnotes, parent, false);

        return new ViewNotes.ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewNotes.ViewHolder holder, int position) {


        holder.Notes.setText("  "+list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Notes;
        public Button delete;
        public DB db = new DB();


        public ViewHolder(View itemView) {
            super(itemView);
            Notes = (TextView) itemView.findViewById(R.id.note);
            delete = (Button) itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    db.db.execSQL("delete from notes WHERE note = '" +list.get(position) + "';");
                   list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,list.size());

                    Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}
