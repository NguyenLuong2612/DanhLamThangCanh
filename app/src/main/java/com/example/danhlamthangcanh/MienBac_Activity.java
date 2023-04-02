package com.example.danhlamthangcanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

public class MienBac_Activity extends AppCompatActivity {
    //Khai báo RecyclerView
    RecyclerView rvDLTC;
    SearchView btn_search;
    //Khởi tạo 1 danh sách
    ArrayList<DanhLamThangCanh> listDLTC;
    //Khởi tạo Adapter
    DLTCAdapter dltcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mienbac);

        rvDLTC = findViewById(R.id.rvDLTC);

        // Đang đợi gán database vào listDLTC
        // listDLTC =

        // Lưu listDLTC đã được gắn dữ liệu và MienBac_Activity
        // dltcAdapter = new DLTCAdapter(MienBac_Activity.this, listDLTC);


        // Tạo khung danh sách để hiển thị dữ liệu trong RecyclerView = linearlayout
        // rvDLTC.setLayoutManager(new LinearLayoutManager(MienBac_Activity.this, LinearLayoutManager.VERTICAL, false));
        // Tạo đường kẻ cách ngăn mỗi item
        // rvDLTC.addItemDecoration(new DividerItemDecoration(MienBac_Activity, VERTICAL));
        // Gán toàn bộ dữ liệu vào
        // rvDLTC.setAdapter(dltcAdapter);
    }

}