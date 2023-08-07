package com.example.andao_apk.Utilisateur;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.andao_apk.Constante.Constante;
import com.example.andao_apk.Constante.Session;
import com.example.andao_apk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    EditText email;
    private RequestQueue requestQueue;
    EditText prenom;
    EditText nom;
    EditText mdp;
    EditText pseudo;
    EditText naissance;
    EditText confirm_mdp;
    String[] genre = {"Homme","Femme"};
    String[] countries = {
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
            "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia",
            "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica",
            "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland",
            "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
            "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
            "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North",
            "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
            "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
            "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco",
            "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
            "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru",
            "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
            "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia",
            "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden",
            "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago",
            "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
            "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
    };
    AutoCompleteTextView sexe;
    AutoCompleteTextView pays;
    ArrayAdapter<String> sexeAdapter;
    ArrayAdapter<String> paysAdapter;

    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        sign=findViewById(R.id.do_signup);

        sexe = findViewById(R.id.sexe_signup);
        pays = findViewById(R.id.pays_signup);
        email = findViewById(R.id.email_signup);
        mdp = findViewById(R.id.mdp_signup);
        naissance = findViewById(R.id.date_naissance_signup);
        confirm_mdp = findViewById(R.id.confirm_mdp_signup);
        prenom = findViewById(R.id.prenom_signup);
        nom = findViewById(R.id.nom_signup);
        pseudo = findViewById(R.id.pseudo_signup);
        paysAdapter = new ArrayAdapter<String>(this,R.layout.custom_dropdown_item, countries);
        pays.setAdapter(paysAdapter);
        sexeAdapter = new ArrayAdapter<String>(this,R.layout.custom_dropdown_item, genre);
        sexe.setAdapter(sexeAdapter);
        sexe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(SignUpActivity.this,"Item:"+item, Toast.LENGTH_SHORT).show();
            }
        });
        pays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(SignUpActivity.this,"Item:"+item, Toast.LENGTH_SHORT).show();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomI=nom.getText().toString();
                String prenomI=prenom.getText().toString();
                String pseudoI=pseudo.getText().toString();
                String mailI=email.getText().toString();
                String datenaissanceI=naissance.getText().toString();
                String sexeI=sexe.getText().toString();
                String paysI=pays.getText().toString();
                String mdp1I=mdp.getText().toString();
                String mdp2I=confirm_mdp.getText().toString();
                getUser(nomI,prenomI,pseudoI,mailI,datenaissanceI,sexeI,paysI,mdp1I);

            }
        });

    }

    private void getUser(String nom,String prenom,String pseudo,String mail,String naissance,String sexe,String pays,String mdp) {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject body = new JSONObject();
        try {
            body.put("nom", nom);
            body.put("prenom", prenom);
            body.put("email", mail);
            body.put("pseudo", pseudo);
            body.put("naissance", naissance);
            body.put("pays", pays);
            body.put("mdp",mdp);
            if(sexe.compareTo("Homme")==0){
                body.put("sexe", 1);
            }else{
                body.put("sexe", 1);
            }
            body.put("partage", new JSONArray());
            body.put("favoris", new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        String url = Constante.api_url +"Utilisateur/inscription";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject dataObject = response.getJSONObject("result");
                            String idUser = dataObject.getString("_id");
                            SharedPreferences sharedPreferences = getSharedPreferences("Preference", getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("sessionID",idUser);
                            editor.apply();
                            String valuesession = sharedPreferences.getString("sessionID", null);
                            Session.getInstance().setMyValue(valuesession);

                            Intent intent = new Intent(SignUpActivity.this, ficheUtilisateur.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("idUser", idUser);
                            intent.putExtra("nom", dataObject.getString("nom"));
                            intent.putExtra("prenom", dataObject.getString("prenom"));
                            intent.putExtra("pseudo", dataObject.getString("pseudo"));
                            intent.putExtra("naissance", dataObject.getString("naissance"));
                            intent.putExtra("pays", dataObject.getString("pays"));
                            intent.putExtra("email", dataObject.getString("email"));
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(SignUpActivity.this,"Inscription réussie!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    public void showSexeDropdown(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionnez le sexe");
        builder.setItems(genre, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedSexe = genre[i];
                sexe.setText(selectedSexe);
            }
        });
        builder.show();
    }
    public void showPaysDropdown(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionnez le pays");
        builder.setItems(countries, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedPays = countries[i];
                pays.setText(selectedPays);
            }
        });
        builder.show();
    }
}