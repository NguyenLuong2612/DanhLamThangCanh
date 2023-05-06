package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// class kế thừa từ interface dùng implements
public class DanhSachDanLamThangCanh_Activity extends AppCompatActivity implements DLTCAdapter.Listener {
    //Khai báo RecyclerView
    RecyclerView rvDLTC;
    ImageButton imgBack;
    Button btn_sort;
    Button btn_sortZtoA;
    SearchView btn_search;
    //Khởi tạo 1 danh sách
    ArrayList<DanhLamThangCanh> listDLTC;
    //Khởi tạo Adapter
    DLTCAdapter dltcAdapter;
    FirebaseFirestore db;
    Comparator<DanhLamThangCanh> comparator = Comparator.comparing(DanhLamThangCanh::getName);

  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
           case R.id.mnSortAtoZ:
                Collections.sort(listDLTC, comparator);
                dltcAdapter = new DLTCAdapter(DanhSachDanLamThangCanh_Activity.this, listDLTC);
                rvDLTC.setAdapter(dltcAdapter);
                dltcAdapter.notifyDataSetChanged();
                break;
          case R.id.mnSortZtoA:
                Collections.sort(listDLTC, new Comparator<DanhLamThangCanh>() {
                    @Override
                    public int compare(DanhLamThangCanh danhLamThangCanh, DanhLamThangCanh t1) {
                        return -danhLamThangCanh.getName().compareToIgnoreCase(t1.getName());
                    }
                });
                dltcAdapter = new DLTCAdapter(DanhSachDanLamThangCanh_Activity.this, listDLTC);
                rvDLTC.setAdapter(dltcAdapter);
                dltcAdapter.notifyDataSetChanged();
                break;
            case R.id.Favor:
                Intent i = new Intent(getApplicationContext(), DMYTActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachdanhlamthangcanh);
        Bundle b = getIntent().getExtras();
        String VungMien = b.getString("VungMien").toString();
        rvDLTC = findViewById(R.id.rvDLTC);
        btn_search = findViewById(R.id.btn_search);
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        db = FirebaseFirestore.getInstance();
        // Đang đợi gán database vào listDLTC
         listDLTC = new ArrayList<>();
        // Lưu listDLTC đã được gắn dữ liệu vào Activity
         dltcAdapter = new DLTCAdapter(DanhSachDanLamThangCanh_Activity.this, listDLTC);
        // Tạo khung danh sách để hiển thị dữ liệu trong RecyclerView = linearlayout
         rvDLTC.setLayoutManager(new LinearLayoutManager(DanhSachDanLamThangCanh_Activity.this, LinearLayoutManager.VERTICAL, false));
        // Tạo đường kẻ cách ngăn mỗi item
         rvDLTC.addItemDecoration(new DividerItemDecoration(DanhSachDanLamThangCanh_Activity.this, LinearLayout.VERTICAL));
        // Gán toàn bộ dữ liệu vào
         rvDLTC.setAdapter(dltcAdapter);

        //Lấy toàn bộ dữ liệu từ collection
        db.collection("DanhLamThangCanh").whereEqualTo("regions",FirebaseFirestore.getInstance().document(VungMien))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()){
                            String city = document.get("city").toString();
                            String id = document.getId();
                            String name = document.get("name").toString();
                            String content1 = document.get("content1").toString();
                            String content2 = document.get("content2").toString();
                            String contentname = document.get("contentname").toString();
                            String imgcontent1 = document.get("imgcontent1").toString();
                            String imgcontent2 = document.get("imgcontent2").toString();
                            String regions = document.get("regions").toString();
                            String description = document.get("description").toString();
                            String imgflag = document.get("imgflag").toString();
                            String video = document.get("video").toString();
                            // ----------------------Đang làm-------------------------
                            DanhLamThangCanh DLTC1 = new DanhLamThangCanh(id, name, contentname, imgflag, imgcontent1, imgcontent2, description, city, content1, content2, regions, video );
                            listDLTC.add(DLTC1);
                        }
                        dltcAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DanhSachDanLamThangCanh_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        btn_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }

        });

       /* btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sắp xếp theo thứ tự tăng dần bản chữ cái
                Collections.sort(listDLTC, comparator);
                dltcAdapter = new DLTCAdapter(DanhSachDanLamThangCanh_Activity.this, listDLTC);
                rvDLTC.setAdapter(dltcAdapter);
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
                dltcAdapter = new DLTCAdapter(DanhSachDanLamThangCanh_Activity.this, listDLTC);
                rvDLTC.setAdapter(dltcAdapter);
                dltcAdapter.notifyDataSetChanged();
            }
        });*/
    }

    public void search(String text){
      ArrayList<DanhLamThangCanh> searchList = new ArrayList<>();
        Query query = db.collection("DanhLamThangCanh")
                .orderBy("name")
                .startAt(text)
                .endAt(text + "\uf8ff");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String city = document.get("city").toString();
                        String id = document.getId();
                        String name = document.get("name").toString();
                        String content1 = document.get("content1").toString();
                        String content2 = document.get("content2").toString();
                        String contentname = document.get("contentname").toString();
                        String imgcontent1 = document.get("imgcontent1").toString();
                        String imgcontent2 = document.get("imgcontent2").toString();
                        String regions = document.get("regions").toString();
                        String description = document.get("description").toString();
                        String imgflag = document.get("imgflag").toString();
                        String video = document.get("video").toString();
                        // ----------------------Đang làm-------------------------
                        DanhLamThangCanh result = new DanhLamThangCanh(id, name, contentname, imgflag, imgcontent1, imgcontent2, description, city, content1, content2, regions, video );
                        searchList.add(result);
                        Log.d("TAG", "=>" + result.getName());
                    }
                    if (searchList.isEmpty()){
                        return;
                    }else {
                        dltcAdapter.searchlist(searchList);
                    }
                }else{
                    Log.d("TAG", "=>" + task.getException());
                    Log.e(String.valueOf(DanhSachDanLamThangCanh_Activity.this), "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void search(String text){
      ArrayList<DanhLamThangCanh> searchList = new ArrayList<>();
        Query query = db.collection("DanhLamThangCanh")
                .orderBy("name")
                .startAt(text)
                .endAt(text + "\uf8ff");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String city = document.get("city").toString();
                        String id = document.getId();
                        String name = document.get("name").toString();
                        String content1 = document.get("content1").toString();
                        String content2 = document.get("content2").toString();
                        String contentname = document.get("contentname").toString();
                        String imgcontent1 = document.get("imgcontent1").toString();
                        String imgcontent2 = document.get("imgcontent2").toString();
                        String regions = document.get("regions").toString();
                        String description = document.get("description").toString();
                        String imgflag = document.get("imgflag").toString();
                        String video = document.get("video").toString();
                        // ----------------------Đang làm-------------------------
                        DanhLamThangCanh result = new DanhLamThangCanh(id, name, contentname, imgflag, imgcontent1, imgcontent2, description, city, content1, content2, regions, video );
                        searchList.add(result);
                        Log.d("TAG", "=>" + result.getName());
                    }
                    if (searchList.isEmpty()){
                        return;
                    }else {
                        dltcAdapter.searchlist(searchList);
                    }
                }else{
                    Log.d("TAG", "=>" + task.getException());
                    Log.e(String.valueOf(DanhSachDanLamThangCanh_Activity.this), "Error getting documents: ", task.getException());
                }
            }
        });
    }

    @Override
    public void onItemListener(DanhLamThangCanh danhLamThangCanh) {
        // Hàm này dùng để truyền dữ liệu của 1 danhlamThangCanh sang 1 activity mới
        // bằng Bundle và Intent với dữ liệu truyện theo dạng: danhLamThangCanh.name,...
        Intent i = new Intent(DanhSachDanLamThangCanh_Activity.this,ChiTietDLTC.class);
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