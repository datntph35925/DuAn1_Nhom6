package com.example.duan1_nhom6.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.duan1_nhom6.R;
import com.example.duan1_nhom6.Views.Login;


public class admin extends Fragment {
    LinearLayout lntHoaDon, lnlDoanhThu, lnlDangXuat;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        lntHoaDon = view.findViewById(R.id.lnl_HoaDon);
        lnlDoanhThu = view.findViewById(R.id.lnl_doanhthu);
        lnlDangXuat = view.findViewById(R.id.lnl_dangxuat);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString("username", "");



        lnlDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xác nhận đăng xuất");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Xóa thông tin tài khoản đã lưu trữ
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.apply();

                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Hủy", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        lntHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHoaDonFragment();
            }
        });
        lnlDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendoanhThuFragment();
            }
        });
        return view;

    }

    private void openHoaDonFragment() {
        // Tạo Fragment mới
        frm_HoaDon frmHoaDon = new frm_HoaDon();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Bắt đầu một giao dịch để thêm, xóa hoặc thay thế Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới
        fragmentTransaction.replace(R.id.flContent,frmHoaDon);

        // Thêm vào back stack để có thể nhấn nút back để quay lại Fragment trước đó
        fragmentTransaction.addToBackStack(null);

        // Kết thúc giao dịch
        fragmentTransaction.commit();
    }

    private void opendoanhThuFragment() {
        // Tạo Fragment mới
        frm_Doanhthu dthu = new frm_Doanhthu();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Bắt đầu một giao dịch để thêm, xóa hoặc thay thế Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới
        fragmentTransaction.replace(R.id.flContent,dthu);

        // Thêm vào back stack để có thể nhấn nút back để quay lại Fragment trước đó
        fragmentTransaction.addToBackStack(null);

        // Kết thúc giao dịch
        fragmentTransaction.commit();
    }
}