package com.example.andao_apk.Article;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andao_apk.R;

import java.util.List;

public class ArticleImagesAdapter extends PagerAdapter {

    private List<String> images;

    public ArticleImagesAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        ImageView articleImage = new ImageView(container.getContext());
        Glide.with(container.getContext())
                .load(images.get(position))
                .apply(new RequestOptions().placeholder(R.drawable.destination)) // Image de remplacement en cas de chargement ou d'erreur
                .into(articleImage);
        container.addView(articleImage,0);
        return articleImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container,int position, @NonNull Object object){
        container.removeView((ImageView)object);
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
