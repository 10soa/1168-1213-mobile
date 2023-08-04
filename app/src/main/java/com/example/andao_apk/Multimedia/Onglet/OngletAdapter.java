package com.example.andao_apk.Multimedia.Onglet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.andao_apk.Multimedia.MultimediaFragment;
import com.example.andao_apk.Multimedia.Videos.VideosFragment;

public class OngletAdapter extends FragmentStateAdapter {
    public OngletAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new MultimediaFragment();
            case 1 :
                return new VideosFragment();
            default:
                return new VideosFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
