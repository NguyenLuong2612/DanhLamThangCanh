package com.example.danhlamthangcanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
// Bước 2: Muốn DLTCAdapter hoạt động thì phải kế thừa từ 1 lớp Adapter của RecyclerView có dạng:
//RecyclerView<DLTCAdapter.DLTCVH (lớp kế thừa từ ViewHolder đã tạo ở Bước 1)>
public class DLTCAdapter extends RecyclerView.Adapter<DLTCAdapter.DLTCVH> {
    //Bước 1: RecyclerView bắt buộc tạo ra 1 class con DLTCVH kế thừa từ ViewHolder
    //DLTCVH chính là controller quản lý item_row
    class DLTCVH extends RecyclerView.ViewHolder{
        //Trong ViewHolder này sẽ chứa tất cả các view ta muốn liên kết (cụ thể là những view con
        // của item_row)
        CircleImageView imgFlag;
        TextView txName, txCity, txDescrition;
        Button btn_Add;
        //Trong lớp con này sẽ thực hiện liên kết giữa controler vs view
        public DLTCVH(@NonNull View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            btn_Add=itemView.findViewById(R.id.btn_add);
            txName = itemView.findViewById(R.id.txName);
            txCity = itemView.findViewById(R.id.txCity);
            txDescrition = itemView.findViewById(R.id.txDescription);
        }
    }

    //onCreateViewHolder -> getItemCount do DLTCAdapter : RecyclerView.Adapter nên 3 lớp này bắt
    //buộc phải implement vào
    @NonNull
    @Override
    //Lớp khởi tạo giao diện
    public DLTCVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Khởi tạo đối tượng, load giao diện
        View  view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent,false);
        return new DLTCVH(view);
    }
    //Lớp render giao diện, khởi tạo đối tượng holder thuộc lớp DLTCVH
    @Override
    public void onBindViewHolder(@NonNull DLTCVH holder, int position) {
        //Input đầu vào là 1 danh sách
        DanhLamThangCanh item = listDLTC.get(position);
        //holder truy xuất đến những thành phần liên kết trong DLTCVH của Bước 1 để hiển thị dữ liệu ra.
            holder.txName.setText(item.getName());
            holder.txCity.setText("Tỉnh/Thành Phố: ".concat(item.getCity()));
            holder.txDescrition.setText(item.getDescription());
            Glide.with(holder.itemView.getContext()).load(item.getImgflag()).into(holder.imgFlag);
            //lay id
            //Gắn interface Listener vào sự kiện onlick để xác định từng itemview được click
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemListener(item);
                }
            });
            holder.btn_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DLTC_DB dltc_db = new DLTC_DB(view.getContext());
                    dltc_db.insert(item.getId());
                    Toast.makeText(holder.itemView.getContext(), "Đã thêm vào danh sách", Toast.LENGTH_SHORT).show();
                }
            });
    }
    //Lớp hiển thị số lượng dòng dữ liệu cần hiển thị
    @Override
    public int getItemCount() {
        return listDLTC.size();
    }

    //Khởi tạo dữ liệu
    ArrayList<DanhLamThangCanh> listDLTC;
    ArrayList<DanhLamThangCanh> listDLTC2;
    Listener listener;
    public DLTCAdapter(Listener listener, ArrayList<DanhLamThangCanh> listDLTC) {
        this.listener = listener;
        this.listDLTC = listDLTC;
        this.listDLTC2 = listDLTC;
    }
    //Khai báo 1 interface nghe event click bên trong tự định nghĩa 1 hàm truyền
    // vào đối số là 1 danhlamthangcanh. Sau đó khai báo interface này (line 73) và trong
    // contructor (line 74)
    interface Listener{
        void onItemListener(DanhLamThangCanh danhLamThangCanh);
    }

    public void searchlist(ArrayList<DanhLamThangCanh> searchList){
        this.listDLTC = searchList;
        notifyDataSetChanged();
    }
}
