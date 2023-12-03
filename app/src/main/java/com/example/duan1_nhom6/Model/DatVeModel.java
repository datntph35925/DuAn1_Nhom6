package com.example.duan1_nhom6.Model;

public class DatVeModel {

    private int madatve;

    private String tenphim;
    private String tenrap;
    private String suatchieu;
    private String phongchieu;
    private double giave;
    private int soluong;
    private int soluongcon;
    private String trangthai;
    private String ngaydat;

    public DatVeModel() {
    }

    public DatVeModel(int madatve, String tenphim, String tenrap, String suatchieu, String phongchieu, double giave, int soluong, int soluongcon, String trangthai, String ngaydat) {
        this.madatve = madatve;
        this.tenphim = tenphim;
        this.tenrap = tenrap;
        this.suatchieu = suatchieu;
        this.phongchieu = phongchieu;
        this.giave = giave;
        this.soluong = soluong;
        this.soluongcon = soluongcon;
        this.trangthai = trangthai;
        this.ngaydat = ngaydat;
    }

    public DatVeModel(String tenphim, String tenrap, String suatchieu, String phongchieu, double giave, int soluong, String trangthai, String ngaydat) {
        this.tenphim = tenphim;
        this.tenrap = tenrap;
        this.suatchieu = suatchieu;
        this.phongchieu = phongchieu;
        this.giave = giave;
        this.soluong = soluong;
        this.trangthai = trangthai;
        this.ngaydat = ngaydat;
    }

    public int getMadatve() {
        return madatve;
    }

    public void setMadatve(int madatve) {
        this.madatve = madatve;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getTenrap() {
        return tenrap;
    }

    public void setTenrap(String tenrap) {
        this.tenrap = tenrap;
    }

    public String getSuatchieu() {
        return suatchieu;
    }

    public void setSuatchieu(String suatchieu) {
        this.suatchieu = suatchieu;
    }

    public String getPhongchieu() {
        return phongchieu;
    }

    public void setPhongchieu(String phongchieu) {
        this.phongchieu = phongchieu;
    }

    public double getGiave() {
        return giave;
    }

    public void setGiave(double giave) {
        this.giave = giave;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluongcon() {
        return soluongcon;
    }

    public void setSoluongcon(int soluongcon) {
        this.soluongcon = soluongcon;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }
}

