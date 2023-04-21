package com.example.danhlamthangcanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
// Bước 2: Muốn DMYTAdapter hoạt động thì phải kế thừa từ 1 lớp Adapter của RecyclerView có dạng:
//RecyclerView<DLTCAdapter.DMYTVH (lớp kế thừa từ ViewHolder đã tạo ở Bước 1)>
public class DMYTAdapter extends RecyclerView.Adapter<DMYTAdapter.DMYTVH> implements Filterable {
    //Bước 1: RecyclerView bắt buộc tạo ra 1 class con DMYTVH kế thừa từ ViewHolder
    //DMYTVH chính là controller cho view item_row
    class DMYTVH extends RecyclerView.ViewHolder{
        //Trong ViewHolder này sẽ chứa tất cả các view ta muốn liên kết (cụ thể là những view con
        // của mỗi item)
        CircleImageView imgFlag;
        TextView txName, txCity, txDescrition;
        ImageView imgdelete;
        //Trong lớp con này sẽ thực hiện liên kết giữa controler vs view
        // Hàm tạo bắt buộc
        public DMYTVH(@NonNull View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            imgdelete = itemView.findViewById(R.id.imgdelete);
            txName = itemView.findViewById(R.id.txName);
            txCity = itemView.findViewById(R.id.txCity);
            txDescrition = itemView.findViewById(R.id.txDescription);
        }
    }

    //onCreateViewHolder -> getItemCount do DMYTAdapter : RecyclerView.Adapter nên 3 lớp này bắt
    //buộc phải implement vào
    @NonNull
    @Override
    //Lớp khởi tạo giao diện
    public DMYTVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Khởi tạo đối tượng, load giao diện
        View  view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rowdmyt, parent,false);
        return new DMYTVH(view);
    }
    //Lớp render giao diện, khởi tạo đối tượng holder thuộc lớp DLTCVH
    @Override
    public void onBindViewHolder(@NonNull DMYTVH holder, int position) {
        //Input đầu vào là 1 danh sách
        DanhLamThangCanh item = listDLTC.get(position);
        //holder truy xuất đến những thành phần liên kết trong DMYTVH của Bước 1 để hiển thị dữ liệu ra.
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
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DLTC_DB dltc_db = new DLTC_DB(view.getContext());
                dltc_db.delete(item.getId());
                Toast.makeText(holder.itemView.getContext(), "Đã xóa khỏi danh sách", Toast.LENGTH_SHORT).show();
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
    public DMYTAdapter(Listener listener, ArrayList<DanhLamThangCanh> listDLTC) {
        this.listener = listener;
        this.listDLTC = listDLTC;
        this.listDLTC2 = listDLTC;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            //Search
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    listDLTC = listDLTC2;
                }
                else {
                    ArrayList<DanhLamThangCanh> list = new ArrayList<>();
                    for (DanhLamThangCanh danhLamThangCanh : listDLTC2){
                        if (danhLamThangCanh.getName().toLowerCase().contains(strSearch.toLowerCase())
                                ||danhLamThangCanh.getCity().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(danhLamThangCanh);
                        }
                    }
                    listDLTC = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listDLTC;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listDLTC = (ArrayList<DanhLamThangCanh>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    //Khai báo 1 interface nghe event click bên trong tự định nghĩa 1 hàm truyền
    // vào đối số là 1 danhlamthangcanh. Sau đó khai báo interface này (line 73) và trong
    // contructor (line 74)
    interface Listener{
        void onItemListener(DanhLamThangCanh danhLamThangCanh);
    }
}
