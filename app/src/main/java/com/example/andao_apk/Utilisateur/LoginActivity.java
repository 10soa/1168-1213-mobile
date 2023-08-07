package com.example.andao_apk.Utilisateur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.andao_apk.Constante.Constante;
import com.example.andao_apk.Constante.Session;
import com.example.andao_apk.MainActivity;
import com.example.andao_apk.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText mdp;
    Button login;
    TextView signup;

    ImageView retour;
    TextView sign;

    private String session;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        email = findViewById(R.id.email_login);
        mdp = findViewById(R.id.search);
        login = findViewById(R.id.do_login);

        sign=findViewById(R.id.go_signup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        retour=findViewById(R.id.retour_profil);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString() == null || email.getText().toString().compareTo("")==0){
                    Toast.makeText(LoginActivity.this, "Champ Mail obligatoire", Toast.LENGTH_SHORT).show();
                }if(mdp.getText().toString() == null || mdp.getText().toString().compareTo("")==0){
                    Toast.makeText(LoginActivity.this, "Champ Mot de passe obligatoire", Toast.LENGTH_SHORT).show();
                }else{
                    getUser(email.getText().toString(),mdp.getText().toString());
                    Intent intent = new Intent(LoginActivity.this,ficheUtilisateur.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUser(String mail,String mdp) {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject body = new JSONObject();
        try {
            body.put("email", mail);
            body.put("mdp", mdp);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        String url = Constante.api_url +"Utilisateur/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject dataObject = response.getJSONObject("data");
                            String idUser = dataObject.getString("_id");
                            SharedPreferences sharedPreferences = getSharedPreferences("Preference", getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("sessionID",idUser);
                            editor.apply();
                            String valuesession = sharedPreferences.getString("sessionID", null);
                            Session.getInstance().setMyValue(valuesession);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

}