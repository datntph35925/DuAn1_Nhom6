package com.example.duan1_nhom6.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.duan1_nhom6.Fragment.frm_Danhsachphim;
import com.example.duan1_nhom6.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Lấy giá trị có tên là userType từ Intent
        String userType = getIntent().getStringExtra("userType");

        //Tạo fragment
        Fragment fragment = new frm_Danhsachphim();

        //tạo bundel
        Bundle bundle = new Bundle();
        bundle.putString("userType", userType);//Nhét giá trị userType có key là "userType"
        fragment.setArguments(bundle);//đẩy vào fragment

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment).commit();
    }
}