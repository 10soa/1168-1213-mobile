package com.example.andao_apk.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.andao_apk.Article.Onglet.FicheTabArticleAdapter;
import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.Multimedia.Videos.VideosClass;
import com.example.andao_apk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private ViewPager articleImagesViewPager;
    private TabLayout viewpagerIndicator;
    private static  boolean ADDED_FAVORIS = false;
    private FloatingActionButton addToFavoris;

    private ViewPager2 articleViewPager;
    private TabLayout articleViewPagerIndicator;

    private TextView libellev;
    private TextView catv;
    private TextView court_descv;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Salut ny fianakams");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        back=(ImageView)findViewById(R.id.liste_article_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
       articleImagesViewPager =findViewById(R.id.article_images_viewpager);
        viewpagerIndicator = findViewById(R.id.article_tablayout);
        addToFavoris = findViewById(R.id.add_to_favoris);
       articleViewPager = findViewById(R.id.view_pager_fiche_2);
       articleViewPagerIndicator = findViewById(R.id.tabLayout_article);

        Intent intent=getIntent();
        String libelle=intent.getExtras().getString("libelle");
        String categorie=intent.getExtras().getString("categorie");
        String court_desc=intent.getExtras().getString("court_description");
        String description=intent.getExtras().getString("description");
        String localisation=intent.getExtras().getString("localisation");
        String site=intent.getExtras().getString("site");
        List<MultimediaClass> multimediaList = intent.getParcelableArrayListExtra("multimedia");
        List<VideosClass> videoList = intent.getParcelableArrayListExtra("video");
        libellev = findViewById(R.id.article_fiche_libelle);
        catv = findViewById(R.id.categorie_article_fiche);
        court_descv = findViewById(R.id.fiche_article_court_description);
        libellev.setText(libelle);
        catv.setText(categorie);
        court_descv.setText(court_desc);
        ArticleClass a = new ArticleClass();
        a.setVideos(videoList);
        a.setDescription(description);
        a.setSite(site);
        System.out.println("localisation == "+ localisation);
        System.out.println("multi len == "+ multimediaList.size());
        a.setLocalisation(localisation);
        ArticleImagesAdapter articleImagesAdapter = new ArticleImagesAdapter(multimediaList);
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
        articleViewPager.setAdapter(new FicheTabArticleAdapter(this,a));
        articleViewPagerIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                articleViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        articleViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                articleViewPagerIndicator.getTabAt(position).select();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }
}