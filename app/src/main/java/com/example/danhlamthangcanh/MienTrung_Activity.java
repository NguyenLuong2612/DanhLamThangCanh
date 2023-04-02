package com.example.danhlamthangcanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

public class MienTrung_Activity extends AppCompatActivity {
    //Khai báo RecyclerView
    RecyclerView rvDLTCMT;
    SearchView btn_search;
    //Khởi tạo 1 danh sách
    ArrayList<DanhLamThangCanh> listDLTCMT;
    //Khởi tạo Adapter
    DLTCAdapter dltcAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mientrung);

        rvDLTCMT =findViewById(R.id.rvDLTCMT);

        // Đang đợi gán database vào listDLTCMT
        // listDLTCMT =

        // Lưu listDLTCMT đã được gắn dữ liệu và MienBac_Activity
        // dltcAdapter = new DLTCAdapter(MienBac_Activity.this, listDLTCMT);


        // Tạo khung danh sách để hiển thị dữ liệu trong RecyclerView = linearlayout
        // rvDLTCMT.setLayoutManager(new LinearLayoutManager(MienTrung_Activity.this, LinearLayoutManager.VERTICAL, false));
        // Tạo đường kẻ cách ngăn mỗi item
        // rvDLTCMT.addItemDecoration(new DividerItemDecoration(MienBac_Activity, VERTICAL));
        // Gán toàn bộ dữ liệu vào
        // rvDLTCMT.setAdapter(dltcAdapter);
    }

}
