package com.example.duan1_nhom6.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_nhom6.Database.DbHepler;
import com.example.duan1_nhom6.Model.PhimModel;

import java.util.ArrayList;

public class PhimDAO {

    private DbHepler dbHepler;

    public PhimDAO(Context context) {dbHepler = new DbHepler(context);}

   public ArrayList<PhimModel> selectAll(){
        ArrayList<PhimModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHepler.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from phim",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    PhimModel pm = new PhimModel();
                    pm.setMaphim(cursor.getInt(0));
                    pm.setTenphim(cursor.getString(1));
                    pm.setDaodien(cursor.getString(2));
                    pm.setThoiluong(cursor.getInt(3));
                    pm.setTheloai(cursor.getString(4));
                    pm.setMota(cursor.getString(5));
                    pm.setLinkanh((cursor.getString(6)));

                    list.add(pm);
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e){
            Log.i(TAG, "Lỗi",e);
        }
        return list;
   }


   public boolean insert(PhimModel pm){
        SQLiteDatabase db =dbHepler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenphim", pm.getTenphim());
        values.put("daodien",pm.getDaodien());
        values.put("thoiluong",pm.getThoiluong());
        values.put("theloai",pm.getTheloai());
        values.put("mota",pm.getMota());
        values.put("linkanh",pm.getLinkanh());


        long row = db.insert("phim",null,values);
        return (row >0);
   }
    public boolean update(PhimModel pm){
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        ContentValues values     = new ContentValues();
        values.put("tenphim", pm.getTenphim());
        values.put("daodien",pm.getDaodien());
        values.put("thoiluong",pm.getThoiluong());
        values.put("theloai",pm.getTheloai());
        values.put("linkanh",pm.getLinkanh());

        long row = db.update("phim",values, "maphim=?",new String[]{String.valueOf(pm.getMaphim())});
        return (row > 0);
    }

   public boolean delete (int maphim){
        SQLiteDatabase db = dbHepler.getWritableDatabase();
        long row = db.delete("phim","maphim=?",new String[] {String.valueOf(maphim)});
        return (row > 0);
   }

    public PhimModel selectid (int id){
        PhimModel phimModel = new PhimModel();
        SQLiteDatabase db = dbHepler.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from phim where maphim=?",new String[]{String.valueOf(id)});
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    PhimModel pm = new PhimModel();
                    pm.setMaphim(cursor.getInt(0));
                    pm.setTenphim(cursor.getString(1));
                    pm.setDaodien(cursor.getString(2));
                    pm.setThoiluong(cursor.getInt(3));
                    pm.setTheloai(cursor.getString(4));
                    pm.setMota(cursor.getString(5));
                    pm.setLinkanh(cursor.getString(6));

                    phimModel = pm;
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e){
            Log.i(TAG, "Lỗi",e);
        }
        return phimModel;
    }
}
