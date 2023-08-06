package com.example.andao_apk.Article.Onglet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.andao_apk.Article.ArticleClass;
import com.example.andao_apk.Multimedia.MultimediaFragment;
import com.example.andao_apk.Multimedia.Videos.VideosFragment;

public class FicheTabArticleAdapter extends FragmentStateAdapter {
    private ArticleClass articleClass;
    public FicheTabArticleAdapter(@NonNull FragmentActivity fragmentActivity, ArticleClass a) {
        super(fragmentActivity);
        this.articleClass = a;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new DescriptionArticleFragment(articleClass.getSite(),articleClass.getDescription(),articleClass.getLocalisation());
            case 1 :
                return new VideosFragment(articleClass.getVideos());
            default:
                return new DescriptionArticleFragment(articleClass.getSite(),articleClass.getDescription(),articleClass.getLocalisation());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
