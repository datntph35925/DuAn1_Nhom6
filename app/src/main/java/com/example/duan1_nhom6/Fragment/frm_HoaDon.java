package com.example.duan1_nhom6.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom6.Adapter.HoaDonApdapter;
import com.example.duan1_nhom6.DAO.DatVeDAO;
import com.example.duan1_nhom6.Model.DatVeModel;
import com.example.duan1_nhom6.R;

import java.util.ArrayList;

public class frm_HoaDon extends Fragment {

    RecyclerView rccDS;

    HoaDonApdapter adapter;
    DatVeDAO datVeDAO;
    Context context;

    ArrayList<DatVeModel> list ;
    public frm_HoaDon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frm__hoa_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rccDS = view.findViewById(R.id.rccDS_hd);
        context = getContext();
        datVeDAO = new DatVeDAO(context);
        list = datVeDAO.selectAll();
        adapter = new HoaDonApdapter(context, list);

        LinearLayoutManager layout = new LinearLayoutManager(context);
        rccDS.setLayoutManager(layout);
        rccDS.setAdapter(adapter);
    }
}