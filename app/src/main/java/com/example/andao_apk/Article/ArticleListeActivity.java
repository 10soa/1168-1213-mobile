package com.example.andao_apk.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleListeActivity extends AppCompatActivity {
    private List<ArticleClass> list = new ArrayList<>();
    private ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_liste);

        list.add(new ArticleClass("_id","Destination incontournable",R.drawable.destination_1,"lien","Parc national de l'Isalo","Une fenêtre ouverte sur la biodiversité du Sud Malgache","Situé dans le sud-ouest de Madagascar, ce parc national est réputé pour ses formations rocheuses spectaculaires, ses canyons profonds, ses piscines naturelles et sa faune variée.",R.drawable.photo));
        list.add(new ArticleClass("_id","Faunes et Flores",R.drawable.fauneflore,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));
        list.add(new ArticleClass("_id","Destination incontournable",R.drawable.destination_1,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));
        list.add(new ArticleClass("_id","Gastronomie malgache",R.drawable.gastronomie,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photo));
        list.add(new ArticleClass("_id","Hotels et activités",R.drawable.hotel_activite,"lien","Parc national de Ranomafana",null,"Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));

        RecyclerView recyclerView = findViewById(R.id.recyclerlistearticle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleAdapter = new ArticleAdapter(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(articleAdapter);
    }
}