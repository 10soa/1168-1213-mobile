package com.example.andao_apk.Reglages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andao_apk.Multimedia.MultimediaFiche;
import com.example.andao_apk.R;

import java.util.List;

public class ReglagesAdapter extends RecyclerView.Adapter<ReglagesAdapter.ReglagesViewHolder> {

    private Context context;
    private List<ReglagesClass> list;

    public ReglagesAdapter(Context context,List<ReglagesClass> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ReglagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.card_reglages,parent,false);
        return new ReglagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReglagesViewHolder holder,int position) {
        holder.textView.setText(list.get(position).getItem());
        holder.image.setImageResource(list.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getCle().compareTo("settings")==0){
                    Intent intent=new Intent(context, ThemeActivity.class);
                    intent.putExtra("text",list.get(position).getItem());
                    intent.putExtra("image",list.get(position).getImage());
                    context.startActivity(intent);
                }else{
                    Intent intent=new Intent(context, LanguesActivity.class);
                    intent.putExtra("text",list.get(position).getItem());
                    intent.putExtra("image",list.get(position).getImage());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ReglagesViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView image;
        CardView cardView;

        public ReglagesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.item_reglages);
            image=(ImageView) itemView.findViewById(R.id.image_item_reglages);
            cardView=(CardView)itemView.findViewById(R.id.card_item_reglages);
        }
    }
}
