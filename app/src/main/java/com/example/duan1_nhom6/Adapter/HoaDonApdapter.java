package com.example.duan1_nhom6.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom6.DAO.DatVeDAO;
import com.example.duan1_nhom6.Model.DatVeModel;
import com.example.duan1_nhom6.R;

import java.util.ArrayList;

public class HoaDonApdapter extends RecyclerView.Adapter<HoaDonApdapter.viewHolder> {

    private final Context context;

    private final ArrayList<DatVeModel> list;
    DatVeDAO datVeDAO;

    public HoaDonApdapter(Context context, ArrayList<DatVeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonApdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hoadon,null);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonApdapter.viewHolder holder, int position) {
        holder.txtTenphim_hd.setText(list.get(position).getTenphim());
        holder.txtTenRap_hd.setText(list.get(position).getTenrap());
        holder.txtSuatChieu_hd.setText(list.get(position).getSuatchieu());
        holder.txtPhongChieu_hd.setText(list.get(position).getPhongchieu());
        holder.txtGia_hd.setText(String.valueOf(list.get(position).getGiave()));
        holder.txtSoLuong_hd.setText(String.valueOf(list.get(position).getSoluong()));
        holder.txtSoLuongcon_hd.setText(String.valueOf(list.get(position).getSoluongcon()));
        holder.txtTrangThai_hd.setText(list.get(position).getTrangthai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenphim_hd,txtTenRap_hd,txtSuatChieu_hd,txtPhongChieu_hd,txtGia_hd,txtSoLuong_hd,txtTrangThai_hd,txtSoLuongcon_hd;
        Button btnSubmit;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenphim_hd = itemView.findViewById(R.id.txtTenphim_hd);
            txtTenRap_hd = itemView.findViewById(R.id.txtTenRap_hd);
            txtSuatChieu_hd = itemView.findViewById(R.id.txtSuatChieu_hd);
            txtPhongChieu_hd = itemView.findViewById(R.id.txtPhongChieu_hd);
            txtGia_hd = itemView.findViewById(R.id.txtGia_hd);
            txtSoLuong_hd = itemView.findViewById(R.id.txtSoLuong_hd);
            txtSoLuongcon_hd = itemView.findViewById(R.id.txtSoLuongcon_hd);
            txtTrangThai_hd = itemView.findViewById(R.id.txtTrangThai_hd);
            btnSubmit = itemView.findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
