package com.example.andao_apk.Utilisateur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andao_apk.R;
public class SignUpActivity extends AppCompatActivity {

    EditText email;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
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
                System.out.println("tato amtylo leizy e");
                String item = adapterView.getItemAtPosition(i).toString();
                System.out.println("tato amtylo leizy e" + item);
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

    }

    // Méthode pour afficher la liste déroulante du sexe
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

    // Méthode pour afficher la liste déroulante du pays
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