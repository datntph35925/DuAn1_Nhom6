package com.example.duan1_nhom6.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom6.Adapter.PhimAdapter;
import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.DAO.TheLoaiDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.Model.TheLoaiModel;
import com.example.duan1_nhom6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
//        Button btn_HoaDon = view.findViewById(R.id.btn_HoaDon);
        context = getContext();
        phimDAO = new PhimDAO(context);
        list = phimDAO.selectAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rccDS.setLayoutManager(layoutManager);

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

        ImageView btnCloseDialog = view.findViewById(R.id.btnCloseDialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        EditText edTen_ad = view.findViewById(R.id.edTen_ad);
        EditText edDaodien_ad = view.findViewById(R.id.edDaodien_ad);
        EditText edThoiluong_ad = view.findViewById(R.id.edThoiluong_ad);
        Spinner spTheloai_ad = view.findViewById(R.id.spTheloai_ad);
        EditText edMota_ad = view.findViewById(R.id.edMota_ad);
        EditText Poster_ad = view.findViewById(R.id.Poster_ad);
        Button btnAdd_ad = view.findViewById(R.id.btnAdd_ad);

        // Khởi tạo TheLoaiDAO và lấy danh sách thể loại từ cơ sở dữ liệu
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        ArrayList<TheLoaiModel> danhSachTheLoai = theLoaiDAO.layDanhSachTheLoai();

        ArrayList<String> tenTheLoaiList = new ArrayList<>();
        for (TheLoaiModel theLoaiModel : danhSachTheLoai) {
            tenTheLoaiList.add(theLoaiModel.getTheloai());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, tenTheLoaiList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTheloai_ad.setAdapter(adapter1);



        btnAdd_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenphim = edTen_ad.getText().toString();
                String daodien = edDaodien_ad.getText().toString();
                String strThoiLuong = edThoiluong_ad.getText().toString();
                String theloai = spTheloai_ad.getSelectedItem().toString();
                String mota = edMota_ad.getText().toString();
                String linkanh = Poster_ad.getText().toString();

                // Kiểm tra trường không được để trống
                if (tenphim.isEmpty() || daodien.isEmpty() || strThoiLuong.isEmpty() || mota.isEmpty() || linkanh.isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra edThoiluong_ad có phải là số không
                int thoiluong;
                try {
                    thoiluong = Integer.parseInt(strThoiLuong);
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Thời lượng phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                PhimModel phimModel = new PhimModel(tenphim, daodien, thoiluong, theloai, mota, linkanh);

                if (phimDAO.insert(phimModel)) {
                    list.clear();
                    list.addAll(phimDAO.selectAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Snackbar.make(view, "Insert success", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Insert fail", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}