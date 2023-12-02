package com.example.duan1_nhom6.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.duan1_nhom6.Fragment.frm_Danhsachphim;
import com.example.duan1_nhom6.Fragment.admin;
import com.example.duan1_nhom6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_item1) {
                    frm_Danhsachphim v = new frm_Danhsachphim();
                    goiphim(v);
                } else if (item.getItemId() == R.id.menu_item2) {
                    admin a = new admin();
                    replaceFrg(a);
                }
                return true;
            }
        });
        frm_Danhsachphim v = new frm_Danhsachphim();
        goiphim(v);

    }

    public void goiphim(Fragment frg){
        //Lấy giá trị có tên là userType từ Intent
        String userType = getIntent().getStringExtra("userType");
        //Tạo fragment
        frg = new frm_Danhsachphim();
        //tạo bundel
        Bundle bundle = new Bundle();
        bundle.putString("userType", userType);//Nhét giá trị userType có key là "userType"
        frg.setArguments(bundle);//đẩy vào fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContent, frg).commit();
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, frg).commit();
    }
}