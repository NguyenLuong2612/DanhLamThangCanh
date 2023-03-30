package com.example.danhlamthangcanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn_miennam, btn_mienbac, btn_mientrung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_mienbac = (Button) findViewById(R.id.btn_mienbac);
        btn_mientrung = (Button) findViewById(R.id.btn_mientrung);
        btn_miennam = (Button) findViewById(R.id.btn_miennam);
        btn_mienbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MienBac_Activity.class);
                startActivity(i);
            }
        });
    }
}