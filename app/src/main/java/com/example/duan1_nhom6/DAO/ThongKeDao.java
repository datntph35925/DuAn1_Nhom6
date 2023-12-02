package com.example.duan1_nhom6.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThongKeDao(Context context) {
        this.context = context;
        DbHepler dbHelper = new DbHepler(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    // Thong ke doanh thu
    @SuppressLint("Range")
    public double getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(giave) as doanhThu FROM datve WHERE suatchieu >= ? AND suatchieu <= ?";
        List<Double> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while (cursor.moveToNext()) {
            try {
                list.add(cursor.getDouble(cursor.getColumnIndex("doanhThu")));
            } catch (Exception e) {
                list.add(0.0);
            }
        }

        if (list.isEmpty()) {
            return 0.0;
        } else {
            return list.get(0);
        }
    }
}
