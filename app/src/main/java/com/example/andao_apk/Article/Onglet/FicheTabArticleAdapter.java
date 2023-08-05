package com.example.andao_apk.Article.Onglet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.andao_apk.Multimedia.MultimediaFragment;
import com.example.andao_apk.Multimedia.Videos.VideosFragment;

public class FicheTabArticleAdapter extends FragmentStateAdapter {

    public FicheTabArticleAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new DescriptionArticleFragment();
            case 1 :
                return new VideosFragment();
            default:
                return new DescriptionArticleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
