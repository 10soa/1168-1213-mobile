package com.example.andao_apk.Multimedia;

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

import com.example.andao_apk.R;

import java.util.List;

public class MultimediaAdapter extends RecyclerView.Adapter<MultimediaAdapter.MultimediaViewHolder> {

    private Context context;
    private List<MultimediaClass> list;

    public MultimediaAdapter(Context context,List<MultimediaClass> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MultimediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.cardview_multimedia,parent,false);
        return new MultimediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultimediaViewHolder holder,int position) {
        holder.textView.setText(list.get(position).getLien());
        holder.image.setImageResource(list.get(position).getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MultimediaFiche.class);
                intent.putExtra("lien",list.get(position).getLien());
                intent.putExtra("image",list.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MultimediaViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView image;

        CardView cardView;

        public MultimediaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.lien_multimedia);
            image=(ImageView) itemView.findViewById(R.id.image_multimedia);
            cardView=(CardView)itemView.findViewById(R.id.card_multimedia );
        }
    }
}
