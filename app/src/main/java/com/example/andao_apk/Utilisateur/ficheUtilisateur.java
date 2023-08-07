package com.example.andao_apk.Utilisateur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andao_apk.MainActivity;
import com.example.andao_apk.R;

public class ficheUtilisateur extends AppCompatActivity {


    private ImageView retour;
    private Button deconnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_utilisateur);
        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userId = preferences.getString("sessionID", "");*/

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
        deconnect=findViewById(R.id.button2);
        deconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Preference", ficheUtilisateur.this.MODE_PRIVATE);
                if (sharedPreferences.contains("sessionID")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("sessionID");
                    editor.apply();
                    Intent intent = new Intent(ficheUtilisateur.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ficheUtilisateur.this, "Une erreur est survenue!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}