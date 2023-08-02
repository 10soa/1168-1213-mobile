package com.example.andao_apk.Notification_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andao_apk.R;

import java.util.ArrayList;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.MultimediaViewHolder>{

    Context context;
    ArrayList<NotificationClass> newsArrayList;

    public NotificationAdapter(Context context, ArrayList<NotificationClass> arrayList) {
        this.context=context;
        this.newsArrayList=arrayList;
    }

    @NonNull
    @Override
    public MultimediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new MultimediaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MultimediaViewHolder holder, int position) {
         NotificationClass rep=newsArrayList.get(position);
         holder.imageView.setImageResource(rep.getImage());
         holder.textView1.setText(rep.getTitre());
         holder.textView2.setText(rep.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class MultimediaViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public MultimediaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView1=itemView.findViewById(R.id.textcardView1);
            textView2=itemView.findViewById(R.id.textecardView2);
        }
    }
}
