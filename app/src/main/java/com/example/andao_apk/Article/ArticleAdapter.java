package com.example.andao_apk.Article;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.Multimedia.MultimediaFiche;
import com.example.andao_apk.Multimedia.Videos.VideosClass;
import com.example.andao_apk.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        Glide.with(context)
                .load(list.get(position).getImage())
                .apply(new RequestOptions().placeholder(list.get(position).getImageCat())) // Image de remplacement en cas de chargement ou d'erreur
                .into(holder.image);
        holder.imageCategorie.setImageResource(list.get(position).getImageCat());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent articleDetails = new Intent(context,ArticleActivity.class);
                articleDetails.putExtra("description",list.get(position).getDescription());
                articleDetails.putExtra("libelle",list.get(position).getLibelle());
                articleDetails.putExtra("site",list.get(position).getSite());
                articleDetails.putExtra("court_description",list.get(position).getCourt_description());
                articleDetails.putExtra("categorie",list.get(position).getCategorie());
                articleDetails.putExtra("localisation",list.get(position).getLocalisation());
                articleDetails.putParcelableArrayListExtra("multimedia", (ArrayList<MultimediaClass>)  list.get(position).getImages());
                articleDetails.putParcelableArrayListExtra("video", (ArrayList<VideosClass>)  list.get(position).getVideos());
                context.startActivity(articleDetails);
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
