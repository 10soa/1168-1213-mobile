package com.example.andao_apk.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.andao_apk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private ViewPager articleImagesViewPager;
    private TabLayout viewpagerIndicator;
    private static  boolean ADDED_FAVORIS = false;
    private FloatingActionButton addToFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Salut ny fianakams");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
       articleImagesViewPager =findViewById(R.id.article_images_viewpager);
        viewpagerIndicator = findViewById(R.id.article_tablayout);
        addToFavoris = findViewById(R.id.add_to_favoris);
        List<String> productImages = new ArrayList<>();
        productImages.add("https://th.bing.com/th?id=OIP.FIprt61j-IpnTzzgRKSieQHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2");
        productImages.add("https://th.bing.com/th/id/R.bade2ed85606117205dbdcdb34cef320?rik=w4pA4dV5r%2bfzQQ&pid=ImgRaw&r=0");
        productImages.add("https://th.bing.com/th/id/OIP.Yap99dz975BymlBjYkTKKQHaE8?pid=ImgDet&rs=1");
        ArticleImagesAdapter articleImagesAdapter = new ArticleImagesAdapter(productImages);
        articleImagesViewPager.setAdapter(articleImagesAdapter);
        viewpagerIndicator.setupWithViewPager(articleImagesViewPager,true);

     /*   new TabLayoutMediator(viewpagerIndicator, articleImagesViewPager,
                (tab, position) -> {
                    // Ici, vous n'avez pas besoin de définir de texte pour les onglets
                }).attach();*/
        addToFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ADDED_FAVORIS){
                    ADDED_FAVORIS = true;
                    addToFavoris.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#FF670924")));
                }else{
                    ADDED_FAVORIS = false;
                    addToFavoris.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }
}