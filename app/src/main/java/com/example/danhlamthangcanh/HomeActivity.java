package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity {
    Button btn_miennam, btn_mienbac, btn_mientrung, btn_dmyt;
    BottomNavigationView mnButtom;

    RelativeLayout layoutHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mnButtom = findViewById(R.id.navMenu);
        mnButtom.setOnItemSelectedListener(getListener());
        layoutHome = findViewById(R.id.layoutHome);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/danhlamthangcanh-9f639.appspot.com/o/BackGround.jpeg?alt=media&token=911495c2-2a6b-4941-b936-0ca22194135f")
                .centerCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        layoutHome.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        
    }
    void loadFragment (Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutophome, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Favor){
                Intent i = new Intent(getApplicationContext(), DMYTActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                switch (item.getItemId()) {
                    case R.id.mnMB:
                      /*  getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new HomeFragment();
                        loadFragment(fmNew);*/
                        Intent i = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                        Bundle b = new Bundle();
                        b.putString("VungMien","VungMien/Bac");
                        b.putString("regionname","Miền Bắc");
                        i.putExtras(b);
                        startActivity(i);
                        return true;
                    case R.id.mnMT:
                       /* getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new ListFragment();
                        loadFragment(fmNew);*/
                        Intent a = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                        Bundle c = new Bundle();
                        c.putString("VungMien","VungMien/Trung");
                        c.putString("regionname","Miền Trung");
                        a.putExtras(c);
                        startActivity(a);
                        return true;
                    case R.id.mnMN:
                       /* getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new SettingFragment();
                        loadFragment(fmNew);*/
                        Intent h = new Intent(getApplicationContext(), DanhSachDanLamThangCanh_Activity.class);
                        Bundle d = new Bundle();
                        d.putString("VungMien","VungMien/Nam");
                        d.putString("regionname","Miền Nam");
                        h.putExtras(d);
                        startActivity(h);
                        return true;
                }
                return true;
            }
        };
    }
}