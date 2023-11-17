package com.example.duan1_nhom6.Model;

public class PhimModel {
    private int maphim;
    private String tenphim;
    private String daodien;
    private int thoiluong;
    private String theloai;
    private String mota;

    public PhimModel() {
    }

    public PhimModel(int maphim, String tenphim, String daodien, int thoiluong, String theloai, String mota) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.daodien = daodien;
        this.thoiluong = thoiluong;
        this.theloai = theloai;
        this.mota = mota;
    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public int getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(int thoiluong) {
        this.thoiluong = thoiluong;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
