package com.example.andao_apk.Multimedia.Onglet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.andao_apk.MainActivity;
import com.example.andao_apk.R;
import com.example.andao_apk.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class OngletActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    OngletAdapter ongletAdapter;

    ImageView imageView;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onglet);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_page);

        ongletAdapter = new OngletAdapter(this);
        viewPager2.setAdapter(ongletAdapter);

        imageView=findViewById(R.id.retour_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OngletActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}
