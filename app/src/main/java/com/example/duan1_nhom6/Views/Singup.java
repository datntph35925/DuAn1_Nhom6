package com.example.duan1_nhom6.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom6.DAO.KhachHangDAO;
import com.example.duan1_nhom6.R;

public class Singup extends AppCompatActivity {

    KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        EditText edt_tendangnhap = findViewById(R.id.edDK_tenDN);
        EditText edt_hoten = findViewById(R.id.edDK_hoten);
        EditText edt_matkhau = findViewById(R.id.edDK_matkhau);
        EditText edt_xnmatkhau = findViewById(R.id.edDK_xnmatkhau);
        Button btnsu_gg = findViewById(R.id.btnDK_google);
        Button btnsu_fb = findViewById(R.id.btnDK_facebook);
        Button btn_dangky = findViewById(R.id.btnDangky);
        TextView tv_dangnhap = findViewById(R.id.tvDangnhap);

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendangnhap = edt_tendangnhap.getText().toString();
                String hoten = edt_hoten.getText().toString();
                String matkhau = edt_matkhau.getText().toString();
                String xnmatkhau = edt_xnmatkhau.getText().toString();

                if (tendangnhap.equals("") || hoten.equals("") || matkhau.equals("")) {
                    Toast.makeText(Singup.this, "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra độ dài mật khẩu
                if (matkhau.length() < 8) {
                    Toast.makeText(Singup.this, "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (xnmatkhau.equals(matkhau)) {
                    khachHangDAO = new KhachHangDAO(Singup.this);
                    if (khachHangDAO.singup(tendangnhap, matkhau, hoten)) {
                        Toast.makeText(Singup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Singup.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Singup.this, "Xác nhận lại Mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        tv_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Singup.this, Login.class));
            }
        });

    }
}