package com.example.andao_apk.Multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andao_apk.R;
import com.squareup.picasso.Picasso;

public class MultimediaFiche extends AppCompatActivity {

    private ImageView imageView;
    private ImageView closed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia_fiche);

        imageView=(ImageView) findViewById(R.id.image_multimedia_fiche);
        closed=(ImageView) findViewById(R.id.closed_multimedia_fiche);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        String image=intent.getExtras().getString("image");
        Picasso.get().load(image).into(imageView);
        //imageView.setImageResource(image);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,height);

        WindowManager.LayoutParams params= getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);

    }
}