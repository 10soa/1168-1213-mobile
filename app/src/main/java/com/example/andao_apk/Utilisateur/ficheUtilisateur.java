package com.example.andao_apk.Utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andao_apk.MainActivity;
import com.example.andao_apk.R;

public class ficheUtilisateur extends AppCompatActivity {


    private ImageView retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_utilisateur);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        retour=findViewById(R.id.retour_profil);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ficheUtilisateur.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }
}