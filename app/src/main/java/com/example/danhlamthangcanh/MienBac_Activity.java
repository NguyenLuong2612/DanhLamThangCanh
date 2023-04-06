package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// class kế thừa từ interface dùng implements
public class MienBac_Activity extends AppCompatActivity implements DLTCAdapter.Listener {
    //Khai báo RecyclerView
    RecyclerView rvDLTC;
    SearchView btn_search;
    //Khởi tạo 1 danh sách
    ArrayList<DanhLamThangCanh> listDLTC;
    //Khởi tạo Adapter
    DLTCAdapter dltcAdapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mienbac);

        rvDLTC = findViewById(R.id.rvDLTC);

        db = FirebaseFirestore.getInstance();

        // Đang đợi gán database vào listDLTC
         listDLTC = new ArrayList<>();

        // Lưu listDLTC đã được gắn dữ liệu và MienBac_Activity
         dltcAdapter = new DLTCAdapter(MienBac_Activity.this, listDLTC);


        // Tạo khung danh sách để hiển thị dữ liệu trong RecyclerView = linearlayout
         rvDLTC.setLayoutManager(new LinearLayoutManager(MienBac_Activity.this, LinearLayoutManager.VERTICAL, false));
        // Tạo đường kẻ cách ngăn mỗi item
         rvDLTC.addItemDecoration(new DividerItemDecoration(MienBac_Activity.this, LinearLayout.VERTICAL));
        // Gán toàn bộ dữ liệu vào
         rvDLTC.setAdapter(dltcAdapter);

        //Lấy toàn bộ dữ liệu từ collection MienBac
        db.collection("MienTrung")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()){
                            String city = document.get("city").toString();
                            String name = document.get("name").toString();
                            String description = document.get("description").toString();
                            // ----------------------Đang làm-------------------------
                            DanhLamThangCanh DLTC1 = new DanhLamThangCanh(name, city, description);
                            listDLTC.add(DLTC1);
                        }
                        dltcAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MienBac_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onItemListener(DanhLamThangCanh danhLamThangCanh) {
        // Hàm này dùng để truyền dữ liệu của 1 danhlamThangCanh sang 1 activity mới
        // bằng Bundle và Intent với dữ liệu truyện theo dạng: danhLamThangCanh.name,...
    }
}