package com.example.duan1_nhom6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.SuatChieuModel;

import java.util.ArrayList;

public class SuatChieuDAO {
    DbHepler dbHepler;

    public SuatChieuDAO (Context context){
        dbHepler = new DbHepler(context);
    }

    // Hàm lấy danh sách tất cả rạp từ bảng "rap"
    public ArrayList<SuatChieuModel> layDanhSachSuatChieu(){
        ArrayList<SuatChieuModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHepler.getReadableDatabase();

        String selectQuery = "SELECT * FROM suatchieu";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                SuatChieuModel suatChieuModel = new SuatChieuModel();
                suatChieuModel.setMasc(cursor.getInt(0)); // Index của cột "marap"
                suatChieuModel.setSuatchieu(cursor.getString(1)); // Index của cột "rap"
                list.add(suatChieuModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    // Hàm thêm rạp mới
    public boolean themSuatChieu(SuatChieuModel suatChieuModel) {
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("suatchieu", suatChieuModel.getSuatchieu());

        long row = db.insert("suatchieu", null, values);
        db.close();

        // Trả về true nếu bản ghi được thêm thành công, ngược lại trả về false
        return (row > 0);
    }

    public boolean xoaSuatChieu (int masc){
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        long row = db.delete("suatchieu","masc=?",new String[] {String.valueOf(masc)});
        return (row > 0);
    }
}
