package com.example.andao_apk.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andao_apk.Constante.Constante;
import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.Multimedia.Videos.VideosClass;
import com.example.andao_apk.Notification_android.NotificationAdapter;
import com.example.andao_apk.Notification_android.NotificationClass;
import com.example.andao_apk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleListeActivity extends AppCompatActivity {
    private List<ArticleClass> list = new ArrayList<>();
    private ArticleAdapter articleAdapter;

    private RequestQueue requestQueue;

    private String motcle;

    private String idCat;

    private ProgressBar progressBar;


    ArticleClass articleClass = new ArticleClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_liste);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent=getIntent();
        if (intent != null) {
            if (intent.hasExtra("idCat")) {
                idCat = intent.getStringExtra("idCat");
            }
        }
        progressBar = findViewById(R.id.progressbar_liste_article);
        progressBar.setVisibility(View.VISIBLE);
        datainitialize();

    }


    private void datainitialize(){
        requestQueue = Volley.newRequestQueue(this);
        String url = Constante.api_url+"Categorie/articles";
        if(idCat != null && !idCat.trim().isEmpty()){
            url = url + "?categorie=" + idCat;
        }
        if(motcle != null && !motcle.trim().isEmpty()){
            url = url + "?motcle=" + motcle;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseR) {
                try {
                    JSONObject response = new JSONObject(responseR);
                    int status = response.getInt("status");
                    System.out.println(status);
                    if (status == 200) {
                        JSONArray dataArray = response.getJSONArray("data");
                        System.out.println(dataArray.length());
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject articleObject =  dataArray.getJSONObject(i).getJSONObject("article");
                            String _id = articleObject.getString("_id");
                            String categorie =  dataArray.getJSONObject(i).getString("categorie");
                            String lienCat =  dataArray.getJSONObject(i).getString("lien");
                            System.out.println("salut "+lienCat);
                            String libelle = articleObject.getString("libelle");
                            String description = articleObject.getString("description");
                            double x = articleObject.getDouble("x");
                            double y = articleObject.getDouble("y");
                            String localisation = articleObject.getString("localisation");
                            String site = articleObject.getString("site");
                            String autres = articleObject.getString("autres");
                            String court_description = articleObject.getString("court_description");
                            String image = lienCat;
                            System.out.println("festival == "+ R.drawable.festival);
                            System.out.println("faune et flore == "+ R.drawable.fauneflore);
                            System.out.println("culture == "+ R.drawable.culture);
                            System.out.println("hotel == "+ R.drawable.hotel_activite);
                            System.out.println("gastro == "+ R.drawable.gastronomie);
                            // Extraire les données des images
                            List<MultimediaClass> images = new ArrayList<>();
                            JSONArray imagesArray = articleObject.getJSONArray("images");
                            for (int j = 0; j < imagesArray.length(); j++) {
                                String lien = imagesArray.getJSONObject(j).getString("lien");
                                String imageId = imagesArray.getJSONObject(j).getString("_id");
                                MultimediaClass multimedia = new MultimediaClass(lien, imageId, image);
                                images.add(multimedia);
                            }
                            List<VideosClass> videos = new ArrayList<>();
                            // Extraire les données des vidéos (si nécessaire)
                            JSONArray videosArray = articleObject.getJSONArray("videos");
                            for (int k = 0; k < videosArray.length(); k++) {
                                String videoId = videosArray.getJSONObject(k).getString("_id");
                                String lien = videosArray.getJSONObject(k).getString("lien");
                                String libelleVideo = libelle;
                                VideosClass video = new VideosClass(videoId, lien, libelleVideo);
                                videos.add(video);
                            }

                            if(imagesArray.length() > 0){
                                image = imagesArray.getJSONObject(0).getString("lien");
                            }
                            ArticleClass article = new ArticleClass(
                                    _id,
                                    categorie,
                                    image,
                                    images,
                                    libelle,
                                    description,
                                    x,
                                    y,
                                    localisation,
                                    site,
                                    autres,
                                    court_description,
                                    videos,
                                    Integer.valueOf(lienCat)
                            );
                            list.add(article);
                        }

                        System.out.println("list ato am activity = "+list.size());
                        RecyclerView recyclerView = findViewById(R.id.recyclerlistearticle);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ArticleListeActivity.this));
                        articleAdapter = new ArticleAdapter(ArticleListeActivity.this,list);
                        recyclerView.setLayoutManager(new GridLayoutManager(ArticleListeActivity.this,1));
                        recyclerView.setAdapter(articleAdapter);
                        progressBar.setVisibility(View.GONE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSONN ", error.toString());
                        // En cas d'erreur, masquer la barre de progression également
                        progressBar.setVisibility(View.GONE);
                    }
                });
        requestQueue.add(stringRequest);
    }



}