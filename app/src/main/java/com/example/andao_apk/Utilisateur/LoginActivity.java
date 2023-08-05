package com.example.andao_apk.Utilisateur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andao_apk.R;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText mdp;
    Button login;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        email = findViewById(R.id.email_login);
        mdp = findViewById(R.id.mdp_login);
        login = findViewById(R.id.do_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString() != null ){

                }
            }
        });
    }
}