package com.example.duan1_nhom6.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom6.Adapter.PhimAdapter;
import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frm_Danhsachphim extends Fragment {
    RecyclerView rccDS;
    FloatingActionButton fltAdd;
    PhimAdapter adapter;
    PhimDAO phimDAO;
    Context context;

    private ArrayList<PhimModel> list = new ArrayList<>();

    public frm_Danhsachphim(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm__danhsachphim, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rccDS = view.findViewById(R.id.rccDS);
        fltAdd = view.findViewById(R.id.btnAdd);
        context = getContext();
        phimDAO = new PhimDAO(context);
        list = phimDAO.selectAll();

        LinearLayoutManager layout = new LinearLayoutManager(context);
        rccDS.setLayoutManager(layout);

        //Lấy giá trị từ Bundel bằng key "userType"
        String userType = getArguments().getString("userType");

        // Tạo adapter và thiết lập userType
        adapter = new PhimAdapter(context, list);
        adapter.setUserType(userType);

        //Ddiều kiện nếu userType là admin thì hiện fltAdd và ngược lại
        if ("admin".equals(userType)){
            fltAdd.setVisibility(View.VISIBLE);
        }else if ("khachhang".equals(userType)){
            fltAdd.setVisibility(View.GONE);
        }
        rccDS.setAdapter(adapter);



        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openDialogAdd();
            }
        });



    }
    public void openDialogAdd(){
        context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_add,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText edTen_ad = view.findViewById(R.id.edTen_ad);
        EditText edDaodien_ad = view.findViewById(R.id.edDaodien_ad);
        EditText edThoiluong_ad = view.findViewById(R.id.edThoiluong_ad);
        EditText edTheloai_ad = view.findViewById(R.id.edTheloai_ad);
        EditText edMota_ad = view.findViewById(R.id.edMota_ad);
        EditText Poster_ad = view.findViewById(R.id.Poster_ad);
        Button btnAdd_ad = view.findViewById(R.id.btnAdd_ad);

        btnAdd_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenphim = edTen_ad.getText().toString();
                String daodien = edDaodien_ad.getText().toString();
                int thoiluong = Integer.parseInt(edThoiluong_ad.getText().toString());
                String theloai = edTheloai_ad.getText().toString();
                String mota = edMota_ad.getText().toString();
                String linkanh = Poster_ad.getText().toString();

                PhimModel phimModel = new PhimModel(tenphim,daodien,thoiluong,theloai,mota,linkanh);

                if (phimDAO.insert(phimModel)){
                    list.clear();
                    list.addAll(phimDAO.selectAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                Toast.makeText(context, "Insert succ", Toast.LENGTH_SHORT).show();
            }
                else {
                    Toast.makeText(context, "Insert fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}