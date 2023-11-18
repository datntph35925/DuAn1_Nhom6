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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtDaodien,txtThoiluong,txtTheloai;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtDaodien= itemView.findViewById(R.id.txtDaodien);
            txtThoiluong= itemView.findViewById(R.id.txtThoiluong);
            txtTheloai= itemView.findViewById(R.id.txtTheloai);
        }
    }
}
