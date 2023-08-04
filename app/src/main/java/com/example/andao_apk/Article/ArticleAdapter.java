package com.example.andao_apk.Article;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andao_apk.Multimedia.MultimediaFiche;
import com.example.andao_apk.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{

    private Context context;
    private List<ArticleClass> list;

    public ArticleAdapter(Context context, List<ArticleClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cardview_liste_article, parent, false);
        return new ArticleAdapter.ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.libelle.setText(list.get(position).getLibelle());
        holder.description.setText(list.get(position).getCourt_description() == null ? list.get(position).getDescription() : list.get(position).getCourt_description());
        holder.categorie.setText(list.get(position).getCategorie());
        holder.imageCategorie.setImageResource(list.get(position).getImageCat());
        holder.image.setImageResource(list.get(position).getImageA());
       /* Glide.with(context)
                .load(list.get(position).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.photo)) // Image de remplacement en cas de chargement ou d'erreur
                .into(holder.image);*/

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

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

            TextView libelle;
            TextView categorie;
            TextView description;
            ImageView image;
            ImageView imageCategorie;

            CardView cardView;

            public ArticleViewHolder(@NonNull View itemView) {
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
