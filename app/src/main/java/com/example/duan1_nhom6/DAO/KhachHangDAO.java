package com.example.duan1_nhom6.DAO;
// KhachHangDAO là để lấy và thao tác dữ liệu từ database

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom6.Database.DbHepler;

public class KhachHangDAO {

    //Bước 1: Gọi đến database cần thao tác
    private DbHepler dbHepler;

    public KhachHangDAO(Context context){
        dbHepler = new DbHepler(context);
    }

    //login
    //khi gọi đến hàm login thì nó sẽ trả về cho người dùng 2 giá trị Thành Công hoặc Thất Bại (Kiểu boolean)

    //tạo một biến có tên là checklogin với kiểu là boolean và tạo thêm 2 tên biến có kiểu là string để nhận giá trị
    public boolean checklogin(String tendangnhap, String matkhau){
        SQLiteDatabase database =dbHepler.getReadableDatabase(); //Đọc dữ liệu trong database
        //Để kiểm tra được thông tin ta sử dụng con trỏ (cursor)
        Cursor cursor = database.rawQuery("select * from khachhang where tendangnhap = ? and matkhau = ? " , new String[]{tendangnhap,matkhau});
        return (cursor.getCount() > 0);
    }
}
