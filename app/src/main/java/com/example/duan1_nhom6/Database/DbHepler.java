package com.example.duan1_nhom6.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHepler extends SQLiteOpenHelper {

    public static final String DB_NAME = "DuAn1";
    public DbHepler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_khachhang = "Create table khachhang (makh integer primary key autoincrement, tendangnhap text , matkhau text , hoten text, sodu INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(tb_khachhang);

        String tb_phim = "Create table phim (maphim integer primary key autoincrement, tenphim text , daodien text , thoiluong integer , theloai text , mota text,linkanh text)";
        sqLiteDatabase.execSQL(tb_phim);

        String tb_admin = "Create table admin (maad integer primary key autoincrement, tendangnhap text , matkhau text , hoten text)";
        sqLiteDatabase.execSQL(tb_admin);

        String tb_theloai = "Create table theloai (matl integer primary key autoincrement, theloai text)";
        sqLiteDatabase.execSQL(tb_theloai);

        String tb_rap = "Create table rap (matl integer primary key autoincrement, theloai text)";
        sqLiteDatabase.execSQL(tb_rap);

        String tb_suatchieu = "Create table suatchieu (matl integer primary key autoincrement, theloai text)";
        sqLiteDatabase.execSQL(tb_suatchieu);

        String tb_datve = "CREATE TABLE datve (" +
                "madatve INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenphim TEXT NOT NULL," +
                "tenrap TEXT NOT NULL," +
                "suatchieu DATETIME NOT NULL," +
                "phongchieu TEXT NOT NULL," +
                "giave REAL NOT NULL," +
                "soluong INTEGER NOT NULL," +
                "soluongcon INTEGER NOT NULL DEFAULT 100," +
                "trangthai TEXT NOT NULL DEFAULT 'Chưa thanh toán'," +
                "ngaydat TEXT NOT NULL )";
        sqLiteDatabase.execSQL(tb_datve);

        String admin = "insert into admin values(1,'admin1','1','Ngô Thành Đạt')," + "(2,'admin2','1','Nguyễn Đăng Sao')";
        sqLiteDatabase.execSQL(admin);

        String khachhang = "insert into khachhang values(1,'khachhang1','123','Khuất Thị Ngọc Anh',0)," + "(2,'khachhang2','111','Nguyễn Đăng Sao',0)";
        sqLiteDatabase.execSQL(khachhang);

        String phim = "insert into phim values(1,'100 Days','Khuất Thị Ngọc Anh',133,'Hành động','Cuộc Đối Đầu Nảy Lửa: Nhóm Siêu Năng Lực Chiến Đấu Để Bảo Vệ Thế Giới Khỏi Hiểm Họa Toàn Cầu.','https://d1j8r0kxyu9tj8.cloudfront.net/images/1566809317niNpzY2khA3tzMg.jpg')," +
                                                "(2,'Gấu Đỏ Biến Hình','Nguyễn Đăng Sao',200,'Hoạt Hình','Phiêu Lưu Hài Hước: Nhóm Bạn Siêu Nhỏ Tìm Kiếm Kỳ Diệu Ẩn Trong Thế Giới Ngầm.','https://i.pinimg.com/564x/09/52/06/095206ef08f87da0bd92d5f810765a7e.jpg')";
        sqLiteDatabase.execSQL(phim);

        String datve = "insert into datve values(1,'Gấu Đỏ Biến Hình','Rạp Ba Đình - HN','22:00','Phòng 3',50000,100,100,'Chưa thanh toán','20-11-2023')";
        sqLiteDatabase.execSQL(datve);

        String theloai = "insert into theloai values(1,'Hoạt Hình')";
        sqLiteDatabase.execSQL(theloai);

        String rap = "insert into rap values(1,'Rạp Thanh Xuân - HN')";
        sqLiteDatabase.execSQL(rap);

        String suatchieu = "insert into suatchieu values(1,'22:00')";
        sqLiteDatabase.execSQL(suatchieu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS khachhang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS phim");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS admin");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS datve");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS theloai");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS rap");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS suatchieu");
            onCreate(sqLiteDatabase);
        }
    }
}
