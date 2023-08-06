package com.example.andao_apk.Article;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.andao_apk.Article.Onglet.FicheTabArticleAdapter;
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
        articleViewPager.setAdapter(new FicheTabArticleAdapter(this));
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