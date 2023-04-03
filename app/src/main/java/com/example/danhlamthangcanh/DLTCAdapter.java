package com.example.danhlamthangcanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
// Bước 2: Muốn DLTCAdapter hoạt động thì phải kế thừa từ 1 lớp Adapter của RecyclerView có dạng:
//RecyclerView<DLTCAdapter.DLTCVH (lớp kế thừa từ ViewHolder đã tạo ở Bước 1)>
public class DLTCAdapter extends RecyclerView.Adapter<DLTCAdapter.DLTCVH> {

    //Bước 1: RecyclerView bắt buộc tạo ra 1 class con DLTCVH kế thừa từ ViewHolder
    //DLTCVH chính là controller cho view item_row
    class DLTCVH extends RecyclerView.ViewHolder{
        //Trong ViewHolder này sẽ chứa tất cả các view ta muốn liên kết (cụ thể là những view con
        // của mỗi item)
        CircleImageView imgFlag;
        TextView txName, txCity, txDiscrition;
        //Trong lớp con này sẽ thực hiện liên kết giữa controler vs view
        public DLTCVH(@NonNull View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            txName = itemView.findViewById(R.id.txName);
            txCity = itemView.findViewById(R.id.txCity);
            txDiscrition = itemView.findViewById(R.id.txDiscription);
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
            //holder.imgFlag.setImageResource(item.getImgflag());
            holder.txName.setText(item.getName());
            holder.txCity.setText("Tỉnh/Thành Phố: ".concat(item.getCity()));
            //holder.txDiscrition.setText(item.getDescription());
            //Gắn interface Listener vào sự kiện onlick để xác định từng itemview được click
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemListener(item);
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
    Listener listener;
    public DLTCAdapter(Listener listener, ArrayList<DanhLamThangCanh> listDLTC) {
        this.listener = listener;
        this.listDLTC = listDLTC;
    }

    //Khai báo 1 interface nghe event click bên trong tự định nghĩa 1 hàm truyền
    // vào đối số là 1 danhlamthangcanh. Sau đó khai báo interface này (line 73) và trong
    // contructor (line 74)
    interface Listener{
        void onItemListener(DanhLamThangCanh danhLamThangCanh);
    }
}
