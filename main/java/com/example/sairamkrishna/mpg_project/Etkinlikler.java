package com.example.sairamkrishna.mpg_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Etkinlikler extends AppCompatActivity {

    private TextView etkinlik;
    String etkinlik_tarih, etkinlik_ad, etkinlik_detay, liste;
    String gecmisText;
    Boolean etkgir;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etkinlikler_screen);

        sharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        etkinlik = (TextView) findViewById(R.id.textView2);

        etkinlik_tarih = getSharedPreferences("main",MODE_PRIVATE).getString("tarih", "");
        etkinlik_ad = getSharedPreferences("main",MODE_PRIVATE).getString("ad", "");
        etkinlik_detay = getSharedPreferences("main",MODE_PRIVATE).getString("detay", "");
        liste = getSharedPreferences("main",MODE_PRIVATE).getString("liste", "");
        etkgir = getSharedPreferences("main", MODE_PRIVATE).getBoolean("yenietk", false);

        if(etkgir){
            etkinlik.setText(liste + "\n\nTarih:" + etkinlik_tarih + "\nAd:" + etkinlik_ad + "\nDetay:" + etkinlik_detay);
        }
        else{
            etkinlik.setText(liste.toString());
        }


        editor.putString("liste", etkinlik.getText().toString());
        editor.putBoolean("yenietk", false);
        editor.commit();
    }
}
