package com.example.andao_apk.Multimedia.Videos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andao_apk.R;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosHolder> {

    private Context context;
    List<VideosClass> liste;

    public VideosAdapter(){}

    @NonNull
    @Override
    public VideosAdapter.VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.videoview_multimedia,parent,false);
        return new VideosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.VideosHolder holder, int position) {
        holder.videos.loadData(liste.get(position).getLien(),"text/html","utf-8");
        holder.bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FullScreen.class);
                intent.putExtra("lien",liste.get(position).getLien());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public VideosAdapter(List<VideosClass> liste,Context context){
        this.context=context;
        this.liste=liste;
    }

    public class VideosHolder extends RecyclerView.ViewHolder{
        WebView videos;
        Button bouton;

        public VideosHolder(View view){
            super(view);
            bouton=(Button)view.findViewById(R.id.videos_fullscreen);
            videos=(WebView) view.findViewById(R.id.cardvideos);
            videos.getSettings().setJavaScriptEnabled(true);
            videos.setWebViewClient(new WebViewClient());
            videos.setWebChromeClient(new WebChromeClient());
        }
    }


}
