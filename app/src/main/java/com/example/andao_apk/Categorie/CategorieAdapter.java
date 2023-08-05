package com.example.andao_apk.Categorie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andao_apk.Article.ArticleActivity;
import com.example.andao_apk.Article.ArticleClass;
import com.example.andao_apk.Article.ArticleListeActivity;
import com.example.andao_apk.Article.ArticleListeFragment;
import com.example.andao_apk.Multimedia.MultimediaAdapter;
import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.Multimedia.MultimediaFiche;
import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.List;

public class CategorieAdapter  extends RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder>{

    private Context context;
    private List<CategorieClass> list;

    public CategorieAdapter(Context context,List<CategorieClass> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cardview_categorie, parent, false);
        return new CategorieAdapter.CategorieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getCategorie());
        holder.image.setImageResource(list.get(position).getImage());
       /* Glide.with(context)
                .load(list.get(position).getLien())
                .apply(new RequestOptions().placeholder(R.drawable.destination)) // Image de remplacement en cas de chargement ou d'erreur
                .into(holder.image);*/

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer une instance du nouveau fragment
                Intent articleDetails = new Intent(context, ArticleListeActivity.class);
                context.startActivity(articleDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class CategorieViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView image;

        ConstraintLayout cardView;

        public CategorieViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.lien_categorie);
            image=(ImageView) itemView.findViewById(R.id.image_categorie);
            cardView=(ConstraintLayout)itemView.findViewById(R.id.card_categorie );
        }
    }
}
