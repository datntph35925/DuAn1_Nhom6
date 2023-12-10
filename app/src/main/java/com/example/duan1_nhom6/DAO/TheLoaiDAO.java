package com.example.duan1_nhom6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.TheLoaiModel;

import java.util.ArrayList;

public class TheLoaiDAO {
    DbHepler dbHepler;

    public TheLoaiDAO (Context context){
        dbHepler = new DbHepler(context);
    }

    // Hàm lấy danh sách tất cả rạp từ bảng "rap"
    public ArrayList<TheLoaiModel> layDanhSachTheLoai(){
        ArrayList<TheLoaiModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHepler.getReadableDatabase();

        String selectQuery = "SELECT * FROM theloai";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TheLoaiModel theLoaiModel = new TheLoaiModel();
                theLoaiModel.setMatl(cursor.getInt(0)); // Index của cột "marap"
                theLoaiModel.setTheloai(cursor.getString(1)); // Index của cột "rap"
                list.add(theLoaiModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    // Hàm thêm rạp mới
    public boolean themTheLoai(TheLoaiModel theLoaiModel) {
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("theloai", theLoaiModel.getTheloai());

        long row = db.insert("theloai", null, values);
        db.close();

        // Trả về true nếu bản ghi được thêm thành công, ngược lại trả về false
        return (row > 0);
    }

    //hàm xoa thể loại
    public boolean xoaTheLoai (int matl){
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        long row = db.delete("theloai","matl=?",new String[] {String.valueOf(matl)});
        return (row > 0);
    }
}
