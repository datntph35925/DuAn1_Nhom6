package com.example.duan1_nhom6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.RapModel;

import java.util.ArrayList;


public class RapDAO {
    DbHepler dbHepler;

    public RapDAO (Context context){
        dbHepler = new DbHepler(context);
    }

    // Hàm lấy danh sách tất cả rạp từ bảng "rap"
    public ArrayList<RapModel> layDanhSachRap(){
        ArrayList<RapModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHepler.getReadableDatabase();

        String selectQuery = "SELECT * FROM rap";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                RapModel rapModel = new RapModel();
                rapModel.setMarap(cursor.getInt(0)); // Index của cột "marap"
                rapModel.setRap(cursor.getString(1)); // Index của cột "rap"
                list.add(rapModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    // Hàm thêm rạp mới
    public boolean themRap(RapModel rapModel) {
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rap", rapModel.getRap());

        long row = db.insert("rap", null, values);
        db.close();

        // Trả về true nếu bản ghi được thêm thành công, ngược lại trả về false
        return (row > 0);
    }

    // Hàm cập nhật thông tin của rạp
//    public void capNhatRap(RapModel rapModel) {
//        SQLiteDatabase db = dbHepler.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("rap", rapModel.getRap());
//
//        db.update("rap", values, "marap=?", new String[]{String.valueOf(rapModel.getMarap())});
//        db.close();
//    }

    // Hàm xóa rạp
    public boolean xoaRap (int marap){
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        long row = db.delete("rap","marap=?",new String[] {String.valueOf(marap)});
        return (row > 0);
    }
}
