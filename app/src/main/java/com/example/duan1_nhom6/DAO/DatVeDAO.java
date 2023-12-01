package com.example.duan1_nhom6.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.DatVeModel;
import com.example.duan1_nhom6.Model.PhimModel;

import java.util.ArrayList;

public class DatVeDAO {

    private DbHepler dbHepler;

    public DatVeDAO(Context context) {dbHepler = new DbHepler(context);}

    public ArrayList<DatVeModel> selectAll(){
        ArrayList<DatVeModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHepler.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from datve",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    DatVeModel dv = new DatVeModel();
                    dv.setMadatve(cursor.getInt(0));
                    dv.setTenphim(cursor.getString(1));
                    dv.setTenrap(cursor.getString(2));
                    dv.setSuatchieu(cursor.getString(3));
                    dv.setPhongchieu(cursor.getString(4));
                    dv.setGiave(cursor.getDouble(5));
                    dv.setSoluong((cursor.getInt(6)));
                    dv.setSoluongcon((cursor.getInt(7)));
                    dv.setTrangthai((cursor.getString(8)));

                    list.add(dv);
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e){
            Log.i(TAG, "Lỗi",e);
        }
        return list;
    }

    public boolean insert(DatVeModel pm){
        SQLiteDatabase db =dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenphim", pm.getTenphim());
        values.put("tenrap",pm.getTenrap());
        values.put("suatchieu",pm.getSuatchieu());
        values.put("phongchieu",pm.getPhongchieu());
        values.put("giave",pm.getGiave());
        values.put("soluong",pm.getSoluong());
        values.put("soluongcon",pm.getSoluongcon());
        values.put("trangthai",pm.getTrangthai());


        long row = db.insert("datve",null,values);
        return (row >0);
    }

    public boolean updateTrangThai(int madatve, String trangthai) {
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai", trangthai);

        long row = db.update("datve", values, "madatve=?", new String[]{String.valueOf(madatve)});
        return (row > 0);
    }
//    public boolean update(DatVeModel dv){
//        SQLiteDatabase db = dbHepler.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("tenphim", dv.getTenphim());
//        values.put("tenrap",dv.getTenrap());
//        values.put("suatchieu",dv.getSuatchieu());
//        values.put("phongchieu",dv.getPhongchieu());
//        values.put("giave",dv.getGiave());
//        values.put("soluong",dv.getSoluong());
//        values.put("soluonngcon",dv.getSoluongcon());
//
//        long row = db.update("datve",values, "madatve=?",new String[]{String.valueOf(dv.getMadatve())});
//        return (row > 0);
//    }

    public int getSoLuongVeConLai(String suatChieu) {
        SQLiteDatabase db = dbHepler.getReadableDatabase();
        int soLuongConLai = 0;

        try {
            String query = "SELECT soluongcon FROM datve WHERE suatchieu = ?";
            Cursor cursor = db.rawQuery(query, new String[]{suatChieu});

            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("soluongcon");
                soLuongConLai = cursor.getInt(columnIndex);
                Log.d("DEBUG", "Column Index for soluongcon: " + columnIndex);
            } else {
                // Nếu không có dữ liệu, mặc định số lượng còn lại là 100
                soLuongConLai = 100;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return soLuongConLai;
    }

    public void updateSoLuongVeConLai(String suatChieu, int soLuongConLai) {
        SQLiteDatabase db = dbHepler.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("soluongcon", soLuongConLai);

            db.update("datve", values, "suatchieu = ?", new String[]{suatChieu});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
