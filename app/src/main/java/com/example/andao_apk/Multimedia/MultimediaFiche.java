package com.example.andao_apk.Multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andao_apk.R;

public class MultimediaFiche extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia_fiche);

        textView=(TextView)findViewById(R.id.lien_multimedia_fiche);
        imageView=(ImageView) findViewById(R.id.image_multimedia_fiche);

        Intent intent=getIntent();
        String lien=intent.getExtras().getString("lien");
        int image=intent.getExtras().getInt("image");

        textView.setText(lien);
        imageView.setImageResource(image);

    }
}