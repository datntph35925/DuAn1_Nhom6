package com.example.duan1_nhom6.Views;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.duan1_nhom6.Fragment.frm_HoaDon;
import com.example.duan1_nhom6.Model.DatVeModel;
import com.example.duan1_nhom6.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DatVe extends AppCompatActivity {

    private ArrayList<String> danhSachRap = new ArrayList<>();
    private ArrayList<String> danhSachSuatChieu = new ArrayList<>();
    private ArrayList<String> danhSachPhongChieu = new ArrayList<>();
    private ArrayAdapter<String> adapterRap;
    private ArrayAdapter<String> adapterSuatChieu;
    private ArrayAdapter<String> adapterPhongChieu;
    private DatVeDAO datVeDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String linkAnh = intent.getStringExtra("linkAnh");

        //ánh xạ
        ImageView Img_poster = findViewById(R.id.Img_poster);
        TextView txtTen_dv = findViewById(R.id.txtTen_dv);
        Spinner spinnerRap = findViewById(R.id.spinnerRap);
        Spinner spinnerSuatChieu = findViewById(R.id.spinnerSuatChieu);
        Spinner spinnerPhongChieu = findViewById(R.id.spinnerPhongChieu);
//        ImageButton btnAdd_Rap = findViewById(R.id.btnAdd_ttdatve);
//        ImageButton btnDelete_ttdatve = findViewById(R.id.btnDelete_ttdatve);
        TextView txtgiave = findViewById(R.id.giave);

        EditText edt_soluongmua = findViewById(R.id.edt_soluongmua);
        Button btnThanhToan = findViewById(R.id.btnThanhToan);

        txtTen_dv.setText(tenPhim);
        Picasso.get().load(linkAnh).into(Img_poster);


        // Khởi tạo danh sách rạp, suất chiếu, và phòng chiếu (có thể lấy từ cơ sở dữ liệu hoặc nguồn dữ liệu khác)
        danhSachRap.add("Rạp A");
        danhSachRap.add("Rạp B");
        danhSachRap.add("Rạp C");

        danhSachSuatChieu.add("12:00");
        danhSachSuatChieu.add("14:30");
        danhSachSuatChieu.add("15:30");

        danhSachPhongChieu.add("Phòng 1");
        danhSachPhongChieu.add("Phòng 2");
        danhSachPhongChieu.add("Phòng 3");

        // Khởi tạo Adapter cho Spinner Rạp, Spinner Suất Chiếu, và Spinner Phòng Chiếu
        adapterRap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachRap);
        adapterRap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterSuatChieu = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachSuatChieu);
        adapterSuatChieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterPhongChieu = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachPhongChieu);
        adapterPhongChieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Thiết lập Adapter cho Spinner Rạp, Spinner Suất Chiếu, và Spinner Phòng Chiếu
        spinnerRap.setAdapter(adapterRap);
        spinnerSuatChieu.setAdapter(adapterSuatChieu);
        spinnerPhongChieu.setAdapter(adapterPhongChieu);



        datVeDAO = new DatVeDAO(this);


//        btnAdd_Rap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                openDialogAdd_Rap();
//            }
//        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenphim = txtTen_dv.getText().toString();
                String tenrap = spinnerRap.getSelectedItem().toString();
                String suatchieu = spinnerSuatChieu.getSelectedItem().toString();
                String phongchieu = spinnerPhongChieu.getSelectedItem().toString();
                int soluong = Integer.parseInt(edt_soluongmua.getText().toString());
                double giave = soluong * 50000;
                String trangthai = "Chưa thanh toán";

                // Hiển thị giá vé trên TextView
                txtgiave.setText("Giá vé: " + giave);

                // Kiểm tra số lượng vé còn lại
                int soluongConLai = datVeDAO.getSoLuongVeConLai(suatchieu);

                if (soluongConLai >= soluong) {
                    // Tạo đối tượng DatVeModel
                    DatVeModel datVeModel = new DatVeModel(tenphim, tenrap, suatchieu, phongchieu, giave, soluong,trangthai);

                    // Gọi phương thức insert trong DatVeDAO
                    if (datVeDAO.insert(datVeModel)) {
                        // Nếu insert thành công, cập nhật số lượng vé còn lại
                        datVeDAO.updateSoLuongVeConLai(suatchieu, soluongConLai - soluong);

                        // Hiển thị thông báo hoặc cập nhật giao diện nếu cần

                        Toast.makeText(DatVe.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
                        replaceFragment(new frm_HoaDon());
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



//    public void openDialogAdd_Rap(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.item_datve, null);
//
//        builder.setView(view);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        //ánh xạ
//        EditText edTenRap = view.findViewById(R.id.edTenRap);
//        EditText edSuatChieu = view.findViewById(R.id.edSuatChieu);
//        EditText edPhongChieu = view.findViewById(R.id.edPhongChieu);
//        Button btnAdd = view.findViewById(R.id.btnAdd_datve);
//
//
//            btnAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Xử lý khi nút "Thêm" được nhấn trong Dialog
//                    String tenRapMoi = edTenRap.getText().toString();
//                    String suatChieu = edSuatChieu.getText().toString();
//                    String phongChieu = edPhongChieu.getText().toString();
//
//                    // Thêm dữ liệu vào cơ sở dữ liệu
//                    DatVeModel datVeModel = new DatVeModel(tenRapMoi,suatChieu,phongChieu);
////                    datVeModel.setTenrap(tenRapMoi);
////                    datVeModel.setSuatchieu(suatChieu);
////                    datVeModel.setPhongchieu(phongChieu);
//
//
//
//                    if (datVeDAO.insert(datVeModel)) {
//                        list.clear();
//                        list.addAll(datVeDAO.selectAll());
//                        adapterRap.notifyDataSetChanged();
//                        adapterSuatChieu.notifyDataSetChanged();
//                        adapterPhongChieu.notifyDataSetChanged();
//                        dialog.dismiss();
//                    } else {
//                        // Thêm thất bại
//                        // Xử lý thông báo hoặc hiển thị lỗi
//                        Toast.makeText(DatVe.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//    }
}