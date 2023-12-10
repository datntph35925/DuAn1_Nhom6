package com.example.duan1_nhom6.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.txtGia_hd.setText(String.valueOf(list.get(position).getGiave()));
        holder.txtSoLuong_hd.setText(String.valueOf(list.get(position).getSoluong()));
        holder.txtSoLuongcon_hd.setText(String.valueOf(list.get(position).getSoluongcon()));
        holder.txtTrangThai_hd.setText(list.get(position).getTrangthai());
        holder.tvHoaDon.setText(list.get(position).getNgaydat());


        if ("admin".equals(userType)) {
            holder.btnSubmit.setVisibility(View.VISIBLE);
            if(list.get(position).getTrangthai().equals("Đã thanh toán")){
                holder.btnSubmit.setVisibility(View.GONE);
            }
        } else {
            holder.btnSubmit.setVisibility(View.GONE);
        }



        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datVeDAO = new DatVeDAO(context);
                DatVeModel datVeModel = list.get(holder.getAdapterPosition());
                datVeModel.setTrangthai("Đã thanh toán");
                if(datVeDAO.updateTrangThai(datVeModel)){
                    Toast.makeText(context, "Xác nhận thanh toán thành công", Toast.LENGTH_SHORT).show();
                    holder.btnSubmit.setVisibility(View.INVISIBLE);
                    list.clear();
                    list.addAll(datVeDAO.selectAll());
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private String userType;

    // Thêm phương thức này để đặt userType(Setter)
    public void setUserType(String userType) {
        this.userType = userType;
        notifyDataSetChanged();
    }




    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenphim_hd,txtTenRap_hd,txtSuatChieu_hd,txtPhongChieu_hd,txtGia_hd,txtSoLuong_hd,txtTrangThai_hd,txtSoLuongcon_hd;
        Button btnSubmit;

        TextView tvHoaDon;
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
            tvHoaDon = itemView.findViewById(R.id.txtNgaydat_hd);
        }
    }
}
