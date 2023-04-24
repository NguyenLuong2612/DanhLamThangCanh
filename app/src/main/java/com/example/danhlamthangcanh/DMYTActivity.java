package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DMYTActivity extends AppCompatActivity implements DMYTAdapter.Listener{
    //Khai báo RecyclerView
    RecyclerView rvDLTC;
    Button btn_sort;
    Button btn_sortZtoA;
    SearchView btn_search;
    //Khởi tạo 1 danh sách
    ArrayList<DanhLamThangCanh> listDLTC;
    //Khởi tạo Adapter
    DMYTAdapter dmytAdapter;
    FirebaseFirestore db;
    Comparator<DanhLamThangCanh> comparator = Comparator.comparing(DanhLamThangCanh::getName);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmytactivity);

        rvDLTC = findViewById(R.id.rvDLTC);
        btn_search = findViewById(R.id.btn_search);
        btn_sort=findViewById(R.id.btn_sort);
        btn_sortZtoA=findViewById(R.id.btn_sortZtoA);
        db = FirebaseFirestore.getInstance();


        // Đang đợi gán database vào listDLTC
        listDLTC = new ArrayList<>();

        // Lưu listDLTC đã được gắn dữ liệu và MienBac_Activity
        dmytAdapter = new DMYTAdapter(DMYTActivity.this, listDLTC);


        // Tạo khung danh sách để hiển thị dữ liệu trong RecyclerView = linearlayout
        rvDLTC.setLayoutManager(new LinearLayoutManager(DMYTActivity.this, LinearLayoutManager.VERTICAL, false));
        // Tạo đường kẻ cách ngăn mỗi item
        rvDLTC.addItemDecoration(new DividerItemDecoration(DMYTActivity.this, LinearLayout.VERTICAL));
        // Gán toàn bộ dữ liệu vào
        rvDLTC.setAdapter(dmytAdapter);

        DLTC_DB idDB = new DLTC_DB(DMYTActivity.this);
        ArrayList<String> ids = idDB.getAllDocumentIds();
        for(String id : ids){
            //Lấy toàn bộ dữ liệu từ collection MienBac
            db.collection("DanhLamThangCanh").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String city = document.getString("city");
                            String id = document.getId();
                            String name = document.getString("name");
                            String content1 = document.getString("content1");
                            String content2 = document.getString("content2");
                            String contentname = document.getString("contentname");
                            String imgcontent1 = document.getString("imgcontent1");
                            String imgcontent2 = document.getString("imgcontent2");
                            String description = document.getString("description");
                            String imgflag = document.getString("imgflag");
                            String video = document.getString("video");
                            DanhLamThangCanh DLTC1 = new DanhLamThangCanh(id, name, contentname, imgflag, imgcontent1, imgcontent2, description, city, content1, content2, video);
                            // Xử lý dữ liệu tại đây
                            listDLTC.add(DLTC1);
                        } else {
                            Log.d(String.valueOf(DMYTActivity.this), "Không tìm thấy document");
                        }
                    } else {
                        Log.d(String.valueOf(DMYTActivity.this), "Lỗi khi lấy document: ", task.getException());
                    }
                    dmytAdapter.notifyDataSetChanged();
                }
            });
        }
        btn_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dmytAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               dmytAdapter.getFilter().filter(newText);
                return false;
            }
        });
        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sắp xếp theo thứ tự tăng dần bản chữ cái
                Collections.sort(listDLTC, comparator);
                dmytAdapter = new DMYTAdapter(DMYTActivity.this, listDLTC);
                rvDLTC.setAdapter(dmytAdapter);
            }
        });
        btn_sortZtoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(listDLTC, new Comparator<DanhLamThangCanh>() {
                    @Override
                    public int compare(DanhLamThangCanh danhLamThangCanh, DanhLamThangCanh t1) {
                        return -danhLamThangCanh.getName().compareToIgnoreCase(t1.getName());
                    }
                });
                dmytAdapter = new DMYTAdapter(DMYTActivity.this, listDLTC);
                rvDLTC.setAdapter(dmytAdapter);
                dmytAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onItemListener(DanhLamThangCanh danhLamThangCanh) {
        // Hàm này dùng để truyền dữ liệu của 1 danhlamThangCanh sang 1 activity mới
        // bằng Bundle và Intent với dữ liệu truyện theo dạng: danhLamThangCanh.name,...
        Intent i = new Intent(DMYTActivity.this,ChiTietDLTC.class);
        Bundle b = new Bundle();
        b.putString("contentname", danhLamThangCanh.getContentname().toString());
        b.putString("content2", danhLamThangCanh.getContent2().toString());
        b.putString("imgcontent1", danhLamThangCanh.getImgcontent1().toString());
        b.putString("imgcontent2", danhLamThangCanh.getImgcontent2().toString());
        b.putString("content1", danhLamThangCanh.getContent1().toString());
        b.putString("video", danhLamThangCanh.getVideo().toString());
        i.putExtras(b);
        startActivity(i);
    }


    }
