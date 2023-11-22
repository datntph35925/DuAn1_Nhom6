package com.example.duan1_nhom6.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.R;
import com.squareup.picasso.Picasso;

public class ChiTietPhim extends AppCompatActivity {
        int maphim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietphim);
        Intent intent = getIntent();
        maphim = intent.getIntExtra("id",0);
        PhimDAO phimDAO = new PhimDAO(this);
        PhimModel phimModel = phimDAO.selectid(maphim);


        TextView txtTen = findViewById(R.id.txtTen);
        TextView txtDaodien = findViewById(R.id.txtDaodien);
        TextView txtThoiluong = findViewById(R.id.txtThoiluong);
        TextView txtTheloai = findViewById(R.id.txtTheloai);
        TextView txtMota = findViewById(R.id.txtmota);
        ImageView txtPoster = findViewById(R.id.txtPoster);
        Button btnDatve = findViewById(R.id.btndatve);



        txtTen.setText(phimModel.getTenphim().toString());
        txtDaodien.setText(phimModel.getDaodien().toString());
        txtThoiluong.setText(String.valueOf(phimModel.getThoiluong()));
        txtTheloai.setText(phimModel.getTheloai().toString());
        txtMota.setText(phimModel.getMota().toString());

        Picasso.get().load(phimModel.getLinkanh().toString()).into(txtPoster);

    }
}