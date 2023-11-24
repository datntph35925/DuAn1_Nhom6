package com.example.duan1_nhom6.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom6.DAO.AdminDAO;
import com.example.duan1_nhom6.DAO.KhachHangDAO;
import com.example.duan1_nhom6.R;

public class LoginAdmin extends AppCompatActivity {
    private AdminDAO adminDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        EditText edt_tendangnhap = findViewById(R.id.edDN_tenDN_AD);
        EditText edt_matkhau = findViewById(R.id.edDN_matkhau_AD);
        TextView txt_quenmatkhau = findViewById(R.id.tvQuenmatkhau_AD);
        Button btn_dangnhap = findViewById(R.id.btnDangnhap_AD);

        adminDAO  = new AdminDAO(this);


        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendangnhap =edt_tendangnhap.getText().toString();
                String matkhau =edt_matkhau.getText().toString();
                if (matkhau.equals("") || tendangnhap.equals("")){
                    Toast.makeText(LoginAdmin.this, "Không được để trống Tên đăng nhập và Mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = adminDAO.checkloginadmin(tendangnhap,matkhau);
                if (check){
                    Toast.makeText(LoginAdmin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    openHomeActivity("admin");
                }else {
                    Toast.makeText(LoginAdmin.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaglog_quenmk();
            }
        });
    }
    private void showDiaglog_quenmk(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_quenmatkhau, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        //ánh xạ
        EditText edtTendangnhap = view.findViewById(R.id.edt_tendangnhap);
        Button btnGui = view.findViewById(R.id.btn_guimk);
        Button btnHuy = view.findViewById(R.id.btn_huy);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangnhap = edtTendangnhap.getText().toString();
                String matkhau = adminDAO.forgot(tendangnhap);
                Toast.makeText(LoginAdmin.this, matkhau, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openHomeActivity(String userType) {
        Intent intent = new Intent(LoginAdmin.this, Home.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
}