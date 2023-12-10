package com.example.duan1_nhom6.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1_nhom6.Fragment.frm_Danhsachphim;
import com.example.duan1_nhom6.R;

public class ThanhToan extends AppCompatActivity {

    TextView tenrap_ck,tenphim_ck,suatchieu_ck,giave_ck;

    Button btnXNCK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        tenrap_ck = findViewById(R.id.tenrap_ck);
        tenphim_ck = findViewById(R.id.tenphim_ck);
        suatchieu_ck = findViewById(R.id.suatchieu_ck);
        giave_ck = findViewById(R.id.giave_ck);
        btnXNCK = findViewById(R.id.btnXNCK);

        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String tenRap = intent.getStringExtra("tenRap");
        String suatChieu = intent.getStringExtra("suatChieu");
        double giaVe = intent.getDoubleExtra("giaVe", 0);

        tenphim_ck.setText("" + tenPhim);
        tenrap_ck.setText("" + tenRap);
        suatchieu_ck.setText("" + suatChieu);
        giave_ck.setText("" + giaVe);

        btnXNCK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển từ Fragment sang Activity
                Intent intent = new Intent(ThanhToan.this, Home.class);

                // Thêm userType vào Intent
                intent.putExtra("userType", "khachhang");

                // Khởi chạy Activity Home
                startActivity(intent);

                // Đóng Fragment hiện tại nếu cần
                finish();
            }
        });
    }
}