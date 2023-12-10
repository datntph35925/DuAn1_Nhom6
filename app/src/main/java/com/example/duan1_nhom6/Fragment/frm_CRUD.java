package com.example.duan1_nhom6.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_nhom6.Adapter.HoaDonApdapter;
import com.example.duan1_nhom6.DAO.DatVeDAO;
import com.example.duan1_nhom6.DAO.RapDAO;
import com.example.duan1_nhom6.DAO.SuatChieuDAO;
import com.example.duan1_nhom6.DAO.TheLoaiDAO;
import com.example.duan1_nhom6.Model.RapModel;
import com.example.duan1_nhom6.Model.SuatChieuModel;
import com.example.duan1_nhom6.Model.TheLoaiModel;
import com.example.duan1_nhom6.R;

import java.util.ArrayList;


public class frm_CRUD extends Fragment {

    Spinner spinnerRap_crud,spinnerSuatChieu_crud,spinnerTheLoai_crud;
    ImageButton btnAdd_rap,btnDelete_rap,btnAdd_suatchieu,btnDelete_suatchieu,btnAdd_theloai,btnDelete_theloai;

    Button btnSubmit_crud;

    RapDAO rapDAO;
    SuatChieuDAO suatChieuDAO;
    TheLoaiDAO theLoaiDAO;

    public frm_CRUD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frm__c_r_u_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rapDAO = new RapDAO(getContext());
        suatChieuDAO = new SuatChieuDAO(getContext());
        theLoaiDAO = new TheLoaiDAO(getContext());

        spinnerRap_crud = view.findViewById(R.id.spinnerRap_crud);
        spinnerSuatChieu_crud = view.findViewById(R.id.spinnerSuatChieu_crud);
        spinnerTheLoai_crud = view.findViewById(R.id.spinnerTheLoai_crud);

        btnAdd_rap = view.findViewById(R.id.btnAdd_rap);
        btnDelete_rap = view.findViewById(R.id.btnDelete_rap);
        btnAdd_suatchieu = view.findViewById(R.id.btnAdd_suatchieu);
        btnDelete_suatchieu = view.findViewById(R.id.btnDelete_suatchieu);
        btnAdd_theloai = view.findViewById(R.id.btnAdd_theloai);
        btnDelete_theloai = view.findViewById(R.id.btnDelete_theloai);
        btnSubmit_crud = view.findViewById(R.id.btnSubmit_crud);

        String userType = getArguments().getString("userType");

        btnAdd_rap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDialogThemRap();
            }
        });

        btnAdd_suatchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDialogThemSuatChieu();
            }
        });

        btnAdd_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDialogThemTheLoai();
            }
        });

        btnDelete_rap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy mã rạp được chọn từ Spinner
                RapModel selectedRap = (RapModel) spinnerRap_crud.getSelectedItem();
                int maRapCanXoa = selectedRap.getMarap();

                // Thực hiện xóa rạp và cập nhật Spinner
                boolean success = rapDAO.xoaRap(maRapCanXoa);
                if (success) {
                    // Nếu xóa thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Xóa Rạp thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi xóa không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Xóa Rạp thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete_suatchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy mã rạp được chọn từ Spinner
                SuatChieuModel selectedSuatChieu = (SuatChieuModel) spinnerSuatChieu_crud.getSelectedItem();
                int maSuatChieuCanXoa = selectedSuatChieu.getMasc();

                // Thực hiện xóa rạp và cập nhật Spinner
                boolean success = suatChieuDAO.xoaSuatChieu(maSuatChieuCanXoa);
                if (success) {
                    // Nếu xóa thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Xóa suất chiếu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi xóa không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Xóa suất chiếu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy mã rạp được chọn từ Spinner
                TheLoaiModel selectedTheLoai = (TheLoaiModel) spinnerTheLoai_crud.getSelectedItem();
                int maTheLoaiCanXoa = selectedTheLoai.getMatl();

                // Thực hiện xóa rạp và cập nhật Spinner
                boolean success = theLoaiDAO.xoaTheLoai(maTheLoaiCanXoa);
                if (success) {
                    // Nếu xóa thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Xóa thể loại thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi xóa không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Xóa thể loại thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit_crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDanhsachphimFragment(userType);
            }
        });

        loadSpinnerData();
    }

    private void hienThiDialogThemRap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.item_themrap, null);
        builder.setView(dialogView);

        EditText edTenRap = dialogView.findViewById(R.id.edTenRap);
        Button btnAdd_tr = dialogView.findViewById(R.id.btnAdd_tr);

        AlertDialog dialog = builder.create();

        btnAdd_tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ EditText
                String tenRap = edTenRap.getText().toString().trim();

                // Thêm rạp và cập nhật Spinner
                boolean success = rapDAO.themRap(new RapModel(tenRap));
                if (success) {
                    // Nếu thêm thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Thêm Rạp thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi thêm không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Thêm Rạp thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openDanhsachphimFragment(String userType) {
        // Tạo Fragment mới
        frm_Danhsachphim frmDanhsachphim = new frm_Danhsachphim();

        // Tạo bundle để truyền giá trị userType
        Bundle bundle = new Bundle();
        bundle.putString("userType", userType);
        frmDanhsachphim.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Bắt đầu một giao dịch để thêm, xóa hoặc thay thế Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới
        fragmentTransaction.replace(R.id.flContent,frmDanhsachphim);

        // Thêm vào back stack để có thể nhấn nút back để quay lại Fragment trước đó
        fragmentTransaction.addToBackStack(null);

        // Kết thúc giao dịch
        fragmentTransaction.commit();
    }

    private void hienThiDialogThemSuatChieu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.item_themsuatchieu, null);
        builder.setView(dialogView);

        EditText edSuatChieu = dialogView.findViewById(R.id.edSuatChieu);
        Button btnAdd_sc = dialogView.findViewById(R.id.btnAdd_sc);

        AlertDialog dialog = builder.create();

        btnAdd_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ EditText
                String suatChieu = edSuatChieu.getText().toString().trim();

                // Thêm rạp và cập nhật Spinner
                boolean success = suatChieuDAO.themSuatChieu(new SuatChieuModel(suatChieu));
                if (success) {
                    // Nếu thêm thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Thêm suất chiếu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi thêm không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Thêm suất chiếu thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void hienThiDialogThemTheLoai() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.item_themtheloai, null);
        builder.setView(dialogView);

        EditText edTheLoai = dialogView.findViewById(R.id.edTheLoai);
        Button btnAdd_tl = dialogView.findViewById(R.id.btnAdd_tl);

        AlertDialog dialog = builder.create();

        btnAdd_tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ EditText
                String theLoai = edTheLoai.getText().toString().trim();

                // Thêm rạp và cập nhật Spinner
                boolean success = theLoaiDAO.themTheLoai(new TheLoaiModel(theLoai));
                if (success) {
                    // Nếu thêm thành công, cập nhật lại dữ liệu cho Spinner
                    loadSpinnerData();
                    Toast.makeText(getContext(), "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi thêm không thành công (có thể thông báo lỗi)
                    Toast.makeText(getContext(), "Thêm thể loại thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }



    private void loadSpinnerData() {
        // Load data for Rap
        ArrayList<RapModel> rapList = rapDAO.layDanhSachRap();
        ArrayAdapter<RapModel> rapAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, rapList);
        rapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRap_crud.setAdapter(rapAdapter);

        // Load data for SuatChieu
        ArrayList<SuatChieuModel> suatChieuList = suatChieuDAO.layDanhSachSuatChieu();
        ArrayAdapter<SuatChieuModel> suatChieuAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, suatChieuList);
        suatChieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSuatChieu_crud.setAdapter(suatChieuAdapter);

        // Load data for TheLoai
        ArrayList<TheLoaiModel> theLoaiList = theLoaiDAO.layDanhSachTheLoai();
        ArrayAdapter<TheLoaiModel> theLoaiAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, theLoaiList);
        theLoaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheLoai_crud.setAdapter(theLoaiAdapter);
    }
}