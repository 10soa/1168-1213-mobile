package com.example.andao_apk.Reglages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.andao_apk.R;

public class ThemeActivity extends AppCompatActivity {

    Switch aSwitch;
    boolean nigth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        getSupportActionBar().hide();
        aSwitch =findViewById(R.id.switch_reglages);

        sharedPreferences=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nigth=sharedPreferences.getBoolean("night",false);
        if(nigth){
            aSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nigth){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("night",false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();
            }
        });
    }
}