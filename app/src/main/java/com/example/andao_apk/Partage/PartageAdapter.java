package com.example.andao_apk.Partage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andao_apk.R;

import java.util.List;

public class PartageAdapter extends RecyclerView.Adapter<PartageAdapter.PartageViewHolder>{
    private Context context;
    private List<PartageClass> list;

    public PartageAdapter(Context context, List<PartageClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PartageAdapter.PartageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cardview_liste_article, parent, false);
        return new PartageAdapter.PartageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartageAdapter.PartageViewHolder holder, int position) {
        holder.libelle.setText(list.get(position).getNom() + " "+ list.get(position).getPrenom());
        holder.description.setText(list.get(position).getLibelle());
        holder.categorie.setText(list.get(position).calculateTimeAgo());
        holder.imageCategorie.setImageResource(list.get(position).getSexe() == 0 ? R.drawable.femme : R.drawable.homme);
      //  holder.image.setImageBitmap(list.get(position).base64decoded());
        Glide.with(context)
                .load(list.get(position).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.destination)) // Image de remplacement en cas de chargement ou d'erreur
                .into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(context, MultimediaFiche.class);
                intent.putExtra("lien",list.get(position).getCategorie());
                intent.putExtra("image",list.get(position).getLien());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class PartageViewHolder extends RecyclerView.ViewHolder{

        TextView libelle;
        TextView categorie;
        TextView description;
        ImageView image;
        ImageView imageCategorie;

        CardView cardView;

        public PartageViewHolder(@NonNull View itemView) {
            super(itemView);
            libelle=(TextView) itemView.findViewById(R.id.libelle_article);
            description=(TextView) itemView.findViewById(R.id.court_description_article);
            categorie=(TextView) itemView.findViewById(R.id.categorie_article);
            image=(ImageView) itemView.findViewById(R.id.image_article);
            imageCategorie=(ImageView) itemView.findViewById(R.id.image_categorie_article);
            cardView=(CardView)itemView.findViewById(R.id.card_liste_article);
        }
    }
}
