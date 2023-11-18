package com.example.duan1_nhom6.Fragment;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom6.Adapter.PhimAdapter;
import com.example.duan1_nhom6.DAO.PhimDAO;
import com.example.duan1_nhom6.Model.PhimModel;
import com.example.duan1_nhom6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frm_Danhsachphim extends Fragment {
    RecyclerView rccDS;
    PhimAdapter adapter;
    PhimDAO phimDAO;
    Context context;

    private ArrayList<PhimModel> list = new ArrayList<>();

    public frm_Danhsachphim(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm__danhsachphim, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rccDS = view.findViewById(R.id.rccDS);
        context = getContext();
        phimDAO = new PhimDAO(context);
        list = phimDAO.selectAll();

        LinearLayoutManager layout = new LinearLayoutManager(context);
        rccDS.setLayoutManager(layout);

        adapter = new PhimAdapter(context, list);
        rccDS.setAdapter(adapter);
    }
}