package com.example.duan1_nhom6.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom6.DAO.TheLoaiDAO;
import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.AdminModel;
import com.example.duan1_nhom6.Model.TheLoaiModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.R;
import com.example.duan1_nhom6.Views.ChiTietPhim;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.viewHolder> {
    private final  Context context;

    private final ArrayList<PhimModel> list;
    PhimDAO phimDAO;

    public PhimAdapter(Context context, ArrayList<PhimModel> list) {
        this.context = context;
        this.list = list;
        phimDAO = new PhimDAO(context);
    }
    @NonNull
    @Override
    public PhimAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_phim,null);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.txtTen.setText(list.get(position).getTenphim());
    holder.txtDaodien.setText(list.get(position).getDaodien());
    holder.txtThoiluong.setText(String.valueOf(list.get(position).getThoiluong()));
    holder.txtTheloai.setText(list.get(position).getTheloai());

    //Hàm ẩn hiện sửa xóa
    setVisibility(holder);

    PhimModel sp = list.get(position);

    // Load image from URL using Picasso
    Picasso.get().load(sp.getLinkanh()).into(holder.ImvPoster);

    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cảnh Bao");
            builder.setIcon(R.drawable.baseline_warning_24);
            builder.setMessage("Bạn có chắc chắn muốn xóa không?");

            // nút yes
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if(phimDAO.delete(sp.getMaphim())){
                        list.clear();
                        list.addAll(phimDAO.selectAll());
                        notifyDataSetChanged();
                        Toast.makeText(context,"Delete true",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context,"Delete false",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // nút no
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(context, "No delete", Toast.LENGTH_SHORT).show();
                }
            });

            // show Dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    });

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ChiTietPhim.class);
            intent.putExtra("id",list.get(holder.getAdapterPosition()).getMaphim());
            intent.putExtra("userType", userType); // Thêm dòng này
            context.startActivity(intent);
        }
    });
    holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { openDialogUpdate(sp);}
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView ImvPoster;
        TextView txtTen, txtDaodien,txtThoiluong,txtTheloai;
        ImageButton btnDelete, btnUpdate;

        LinearLayout layout_up_de; //ánh xạ layout
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtDaodien= itemView.findViewById(R.id.txtDaodien);
            txtThoiluong= itemView.findViewById(R.id.txtThoiluong);
            txtTheloai= itemView.findViewById(R.id.txtTheloai);
            ImvPoster = itemView.findViewById(R.id.ImvPoster);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            layout_up_de = itemView.findViewById(R.id.layout_up_de);


        }
    }

    private String userType;

    // Thêm phương thức này để đặt userType(Setter)
    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Hiển thị layout_up_de nếu là admin, ngược lại ẩn đi
    public void setVisibility(viewHolder holder) {
        if ("admin".equals(userType)) {
            holder.layout_up_de.setVisibility(View.VISIBLE);
        } else {
            holder.layout_up_de.setVisibility(View.GONE);
        }
    }
    public void  openDialogUpdate (PhimModel pm){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_update,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        ImageView btnCloseDialog_up = view.findViewById(R.id.btnCloseDialog_up);
        btnCloseDialog_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        EditText edTen_ud = view.findViewById(R.id.edTen_ud);
        EditText edDaodien_ud = view.findViewById(R.id.edDaodien_ud);
        EditText edThoiluong_ud = view.findViewById(R.id.edThoiluong_ud);
        Spinner spTheloai_ud = view.findViewById(R.id.spTheloai_ud);
        Button btnUpdate_ud = view.findViewById(R.id.btnUpdate_ud);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTheloai_ud.setAdapter(adapter2);

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        ArrayList<TheLoaiModel> danhSachTheLoai = theLoaiDAO.layDanhSachTheLoai();

        ArrayList<String> tenTheLoaiList = new ArrayList<>();
        for (TheLoaiModel theLoaiModel : danhSachTheLoai) {
            tenTheLoaiList.add(theLoaiModel.getTheloai());
        }

        // Đặt dữ liệu vào Adapter
        adapter2.addAll(tenTheLoaiList);
        adapter2.notifyDataSetChanged();

        edTen_ud.setText(pm.getTenphim());
        edDaodien_ud.setText(pm.getDaodien());
        edThoiluong_ud.setText(String.valueOf(pm.getThoiluong()));
        spTheloai_ud.setSelection(tenTheLoaiList.indexOf(pm.getTheloai()));

        btnUpdate_ud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edTen_ud.getText().toString();
                String daodien = edDaodien_ud.getText().toString();
                String thoiluong = edThoiluong_ud.getText().toString();

                if (!validateInputs(ten, daodien, thoiluong)) {
                    return; // Nếu dữ liệu không hợp lệ, dừng lại
                }

                pm.setTenphim(ten);
                pm.setDaodien(daodien);
                pm.setThoiluong(Integer.parseInt(thoiluong));
                pm.setTheloai(spTheloai_ud.getSelectedItem().toString());

                if (phimDAO.update(pm)) {
                    list.clear();
                    list.addAll(phimDAO.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Update fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateInputs(String ten, String daodien, String thoiluong) {
        if (ten.trim().isEmpty() || daodien.trim().isEmpty() || thoiluong.trim().isEmpty()) {
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Integer.parseInt(thoiluong); // Kiểm tra xem thoiluong có phải là số hay không
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Thời lượng phải là số", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
