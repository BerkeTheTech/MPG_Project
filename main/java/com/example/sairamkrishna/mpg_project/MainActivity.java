package com.example.sairamkrishna.mpg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String date;
    private Button etkinlik, etkinlik_kaydet;
    private CalendarView calendarView;
    private EditText etkinlik_adı, etkinlik_detayı;
    private TextView tarih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putBoolean("yenietk", false);
        editor.commit();

        etkinlik = (Button) findViewById(R.id.etkinlikButton);
        etkinlik_kaydet = (Button) findViewById(R.id.etkinlikKaydet);
        tarih = (TextView) findViewById(R.id.tarih);
        etkinlik_adı = (EditText) findViewById(R.id.etkinlikAdi);
        etkinlik_detayı = (EditText) findViewById(R.id.etkinlikDetayi);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + (i1 + 1) + "/" + i;
                tarih.setText(date);
            }
        });

        etkinlik_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etk_ad = etkinlik_adı.getText().toString();
                String etk_det = etkinlik_detayı.getText().toString();
                if(etk_ad.matches("") || etk_det.matches("")){
                    Toast.makeText(MainActivity.this, "Lütfen Etkinlik Adını Ve Detayını Doldurunuz!", Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putString("tarih", date);
                    editor.putString("ad", etk_ad);
                    editor.putString("detay", etk_det);
                    editor.putBoolean("yenietk", true);
                    editor.commit();

                    Toast.makeText(MainActivity.this, "Etkinlik Kaydedildi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etkinlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Etkinlikler.class));
            }
        });
    }
}
