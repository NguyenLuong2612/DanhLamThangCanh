package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    Button btn_miennam, btn_mienbac, btn_mientrung;
    BottomNavigationView mnButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mnButtom = findViewById(R.id.navMenu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main");
        actionBar.setDisplayHomeAsUpEnabled(true);
        mnButtom.setOnItemSelectedListener(getListener());

        btn_mienbac = (Button) findViewById(R.id.btn_mienbac);
        btn_mientrung = (Button) findViewById(R.id.btn_mientrung);
        btn_miennam = (Button) findViewById(R.id.btn_miennam);
        btn_mienbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                Bundle b = new Bundle();
                b.putString("VungMien","VungMien/Bac");
                i.putExtras(b);
                startActivity(i);
            }
        });
        btn_mientrung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                Bundle b = new Bundle();
                b.putString("VungMien","VungMien/Trung");
                i.putExtras(b);
                startActivity(i);
            }
        });
        btn_miennam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                Bundle b = new Bundle();
                b.putString("VungMien","VungMien/Nam");
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    void loadFragment (Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                switch (item.getItemId()) {
                    case R.id.mnHome:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new HomeFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnList:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new ListFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnSetting:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new SettingFragment();
                        loadFragment(fmNew);
                        return true;
                }
                return true;
            }
        };
    }
}