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

import org.w3c.dom.Text;

import java.util.List;

public class PartageAdapter extends RecyclerView.Adapter<PartageAdapter.PartageViewHolder>{
    private Context context;
    private List<PartageClass> list;

    private int[] etoiles = { R.drawable.etoile_0, R.drawable.etoile_1,R.drawable.etoile_2, R.drawable.etoile_3,R.drawable.etoile_4, R.drawable.etoile_5};

    public PartageAdapter(Context context, List<PartageClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PartageAdapter.PartageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.card_partage, parent, false);
        return new PartageAdapter.PartageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartageAdapter.PartageViewHolder holder, int position) {
        holder.libelle.setText(list.get(position).getNom() + " "+ list.get(position).getPrenom());
        holder.description.setText(list.get(position).getLibelle());
        holder.categorie.setText(list.get(position).calculateTimeAgo());
        holder.vrai_desc.setText(list.get(position).getDescription());
        holder.imageCategorie.setImageResource(list.get(position).getSexe() == 0 ? R.drawable.femme : R.drawable.homme);
        holder.note.setImageResource(etoiles[list.get(position).getNote()]);
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

        ImageView note;

        TextView vrai_desc;

        CardView cardView;

        public PartageViewHolder(@NonNull View itemView) {
            super(itemView);
            libelle=(TextView) itemView.findViewById(R.id.nom_partage);
            vrai_desc = (TextView) itemView.findViewById(R.id.desc_partage) ;
            description=(TextView) itemView.findViewById(R.id.libelle_partage);
            categorie=(TextView) itemView.findViewById(R.id.date_partage);
            image=(ImageView) itemView.findViewById(R.id.image_article);
            imageCategorie=(ImageView) itemView.findViewById(R.id.image_user_partage);
            note = (ImageView) itemView.findViewById(R.id.note_partage_image);
            cardView=(CardView)itemView.findViewById(R.id.card_partage);
        }
    }
}
