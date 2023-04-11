package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
        db.collection("DanhLamThangCanh").whereEqualTo("regions",FirebaseFirestore.getInstance().document("VungMien/Bac"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()){
                            String city = document.get("city").toString();
                            String idDLTC = document.get("id").toString();
                            String name = document.get("name").toString();
                            String content1 = document.get("content1").toString();
                            String content2 = document.get("content2").toString();
                            String contentname = document.get("contentname").toString();
                            String imgcontent1 = document.get("imgcontent1").toString();
                            String imgcontent2 = document.get("imgcontent2").toString();
                            String regions = document.get("regions").toString();
                            String description = document.get("description").toString();
                            String imgflag = document.get("imgflag").toString();
                            int id = Integer.parseInt(idDLTC);
                            // ----------------------Đang làm-------------------------
                            DanhLamThangCanh DLTC1 = new DanhLamThangCanh(id, name, contentname, imgflag, imgcontent1, imgcontent2, description, city, content1, content2, regions );
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
        Intent i = new Intent(MienBac_Activity.this,ChiTietDLTC.class);
        Bundle b = new Bundle();
        b.putString("contentname", danhLamThangCanh.getContentname().toString());
        b.putString("content2", danhLamThangCanh.getContent2().toString());
        b.putString("imgcontent1", danhLamThangCanh.getImgcontent1().toString());
        b.putString("imgcontent2", danhLamThangCanh.getImgcontent2().toString());
        b.putString("content1", danhLamThangCanh.getContent1().toString());
        i.putExtras(b);
        startActivity(i);
    }
}