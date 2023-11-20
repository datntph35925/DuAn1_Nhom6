package com.example.duan1_nhom6.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.R;

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

    PhimModel sp = list.get(position);

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

        TextView txtTen, txtDaodien,txtThoiluong,txtTheloai;
        ImageButton btnDelete, btnUpdate;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtDaodien= itemView.findViewById(R.id.txtDaodien);
            txtThoiluong= itemView.findViewById(R.id.txtThoiluong);
            txtTheloai= itemView.findViewById(R.id.txtTheloai);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
    public void  openDialogUpdate (PhimModel pm){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_update,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText edTen_ud = view.findViewById(R.id.edTen_ud);
        EditText edDaodien_ud = view.findViewById(R.id.edDaodien_ud);
        EditText edThoiluong_ud = view.findViewById(R.id.edThoiluong_ud);
        EditText edTheloai_ud = view.findViewById(R.id.edTheloai_ud);
        Button btnUpdate_ud = view.findViewById(R.id.btnUpdate_ud);

        edTen_ud.setText(pm.getTenphim());
        edDaodien_ud.setText(pm.getDaodien());
        edThoiluong_ud.setText(String.valueOf(pm.getThoiluong()));
        edTheloai_ud.setText(pm.getTheloai());

        btnUpdate_ud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm.setTenphim(edTen_ud.getText().toString());
                pm.setDaodien(edDaodien_ud.getText().toString());
                pm.setThoiluong(Integer.parseInt(edThoiluong_ud.getText().toString()));
                pm.setTheloai(edTheloai_ud.getText().toString());
                if (phimDAO.update(pm)){
                    list.clear();
                    list.addAll(phimDAO.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Update fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
