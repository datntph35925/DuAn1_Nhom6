package com.example.duan1_nhom6.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {

    DbHepler dbHepler;

    public ThongKeDao(Context context){
        dbHepler = new DbHepler(context);
    }



    // Thong ke doanh thu
//    @SuppressLint("Range")
//    public double getDoanhThu(String tuNgay, String denNgay) {
//        String sqlDoanhThu = "SELECT SUM(giave) as doanhThu FROM datve WHERE suatchieu >= ? AND suatchieu <= ?";
//        List<Double> list = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
//
//        while (cursor.moveToNext()) {
//            try {
//                list.add(cursor.getDouble(cursor.getColumnIndex("doanhThu")));
//            } catch (Exception e) {
//                list.add(0.0);
//            }
//        }
//
//        if (list.isEmpty()) {
//            return 0.0;
//        } else {
//            return list.get(0);
//        }
//    }

    public int getDoanhThu(String ngayBatDau, String ngayKetThuc) {//yyyy-mm-dd
        SQLiteDatabase sqLiteDatabase = dbHepler.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(giave) FROM datve  WHERE trangthai = \"Đã thanh toán\" AND ngaydat BETWEEN ? AND ?", new String[] {ngayBatDau, ngayKetThuc});        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }else {
            return 0;
        }
    }
}
