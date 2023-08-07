package com.example.andao_apk.Utilisateur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.example.andao_apk.MainActivity;
import com.example.andao_apk.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ficheUtilisateur extends AppCompatActivity {


    private ImageView retour;
    private Button deconnect;

    private String idUser;

    private RequestQueue requestQueue;

    private TextView nom;
    private TextView pseudo;
    private TextView naissance;
    private TextView pays;
    private TextView mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_utilisateur);

        nom=findViewById(R.id.user_name);
        pseudo=findViewById(R.id.pseudo_user);
        pays=findViewById(R.id.pays);
        naissance=findViewById(R.id.datenaissance);
        mail=findViewById(R.id.email);

        nom.setText(getIntent().getStringExtra("nom")+" "+getIntent().getStringExtra("prenom"));
        pseudo.setText(getIntent().getStringExtra("pseudo"));
        pays.setText(getIntent().getStringExtra("pays"));
        mail.setText(getIntent().getStringExtra("email"));
        naissance.setText(formatDate(getIntent().getStringExtra("naissance")));
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        idUser=getIntent().getStringExtra("idUser");

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

    public static String formatDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}