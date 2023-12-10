package com.example.duan1_nhom6.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1_nhom6.DAO.DatVeDAO;
import com.example.duan1_nhom6.DAO.RapDAO;
import com.example.duan1_nhom6.DAO.SuatChieuDAO;
import com.example.duan1_nhom6.Fragment.frm_Danhsachphim;
import com.example.duan1_nhom6.Fragment.frm_HoaDon;
import com.example.duan1_nhom6.Model.DatVeModel;
import com.example.duan1_nhom6.Model.RapModel;
import com.example.duan1_nhom6.Model.SuatChieuModel;
import com.example.duan1_nhom6.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatVe extends AppCompatActivity {
    DatVeDAO datVeDAO;
    TextView txtgiave;

    EditText edt_soluongmua;
    double giaVe = 0;
    int soLuong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String linkAnh = intent.getStringExtra("linkAnh");
        String userType = intent.getStringExtra("userType");



        //ánh xạ
        ImageView Img_poster = findViewById(R.id.Img_poster);
        TextView txtTen_dv = findViewById(R.id.txtTen_dv);
        Spinner spinnerRap = findViewById(R.id.spinnerRap);
        Spinner spinnerSuatChieu = findViewById(R.id.spinnerSuatChieu);
         txtgiave = findViewById(R.id.giave);
         edt_soluongmua = findViewById(R.id.edt_soluongmua);
        Button btnThanhToan = findViewById(R.id.btnThanhToan);

        // Lấy danh sách rạp và suất chiếu
        RapDAO rapDAO = new RapDAO(this);
        ArrayList<RapModel> danhSachRap = rapDAO.layDanhSachRap();

        SuatChieuDAO suatChieuDAO = new SuatChieuDAO(this);
        ArrayList<SuatChieuModel> danhSachSuatChieu = suatChieuDAO.layDanhSachSuatChieu();

        // Thiết lập ArrayAdapter cho Spinner Rạp
        ArrayAdapter<RapModel> rapAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachRap);
        rapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRap.setAdapter(rapAdapter);

        // Thiết lập ArrayAdapter cho Spinner Suất Chiếu
        ArrayAdapter<SuatChieuModel> suatChieuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachSuatChieu);
        suatChieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSuatChieu.setAdapter(suatChieuAdapter);

        datVeDAO = new DatVeDAO(this);

        // Lắng nghe sự kiện thay đổi của EditText số lượng
        edt_soluongmua.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần xử lý trước sự thay đổi
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần xử lý trong quá trình thay đổi
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Khi số lượng thay đổi, cập nhật giá vé
                updateGiaVe();
            }
        });

        txtTen_dv.setText(tenPhim);
        Picasso.get().load(linkAnh).into(Img_poster);



        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ Spinner Rạp
                RapModel selectedRap = (RapModel) spinnerRap.getSelectedItem();
                String tenRap = selectedRap.getRap();

                // Lấy dữ liệu từ Spinner Suất Chiếu
                SuatChieuModel selectedSuatChieu = (SuatChieuModel) spinnerSuatChieu.getSelectedItem();
                String suatChieu = selectedSuatChieu.getSuatchieu();

                String tenphim = txtTen_dv.getText().toString();

                int soluong = Integer.parseInt(edt_soluongmua.getText().toString());


                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String dateString = simpleDateFormat.format(date);
//                hoaDon.setNgayDat(dateString);
                String trangthai = "Chưa thanh toán";

                // Hiển thị giá vé trên TextView (bạn có thể bỏ dòng này nếu muốn)
                txtgiave.setText(" " + giaVe);

                // Kiểm tra số lượng vé còn lại
                int soluongConLai = datVeDAO.getSoLuongVeConLai(suatChieu);

                if (soluongConLai >= soluong) {
                    // Tạo đối tượng DatVeModel
                    DatVeModel datVeModel = new DatVeModel(tenphim, tenRap, suatChieu, giaVe, soluong,trangthai,dateString);

                    // Gọi phương thức insert trong DatVeDAO
                    if (datVeDAO.insert(datVeModel)) {
                        // Nếu insert thành công, cập nhật số lượng vé còn lại
                        datVeDAO.updateSoLuongVeConLai(suatChieu, soluongConLai - soluong);

                        // Hiển thị thông báo hoặc cập nhật giao diện nếu cần

                        Toast.makeText(DatVe.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatVe.this, ThanhToan.class);
                        intent.putExtra("tenPhim", tenphim);
                        intent.putExtra("tenRap", tenRap);
                        intent.putExtra("suatChieu", suatChieu);
                        intent.putExtra("giaVe", giaVe);
                        startActivity(intent);
                    } else {
                        // Xử lý khi insert thất bại
                        Toast.makeText(DatVe.this, "Đặt vé thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo khi số lượng vé mua vượt quá số lượng vé còn lại
                    Toast.makeText(DatVe.this, "Số lượng vé không đủ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void updateGiaVe() {
        try {
            soLuong = Integer.parseInt(edt_soluongmua.getText().toString());
            giaVe = soLuong * 50000;
            txtgiave.setText("Giá vé: " + giaVe);
        } catch (NumberFormatException e) {
            Toast.makeText(DatVe.this, "Vui lòng nhập số lượng hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }




}