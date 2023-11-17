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
import com.example.duan1_nhom6.MainActivity;
import com.example.duan1_nhom6.R;

public class Login extends AppCompatActivity {

    private KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ánh xạ
        Button btnlg_fb = findViewById(R.id.btnDN_facebook);
        Button btnlg_gg = findViewById(R.id.btnDN_google);
        Button btnlg_admin = findViewById(R.id.btnDN_admin);
        Button btn_dangnhap = findViewById(R.id.btnDangnhap);
        EditText edt_tendangnhap = findViewById(R.id.edDN_tenDN);
        EditText edt_matkhau = findViewById(R.id.edDN_matkhau);
        TextView txt_quenmatkhau = findViewById(R.id.tvQuenmatkhau);
        TextView txt_dangky = findViewById(R.id.tvDangky);

        khachHangDAO = new KhachHangDAO(this);

        //Chức năng đâng nhập
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendangnhap =edt_tendangnhap.getText().toString();
                String matkhau =edt_matkhau.getText().toString();
                if (matkhau.equals("") || tendangnhap.equals("")){
                    Toast.makeText(Login.this, "Không được để trống Tên đăng nhập và Mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = khachHangDAO.checklogin(tendangnhap,matkhau);
                if (check){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}