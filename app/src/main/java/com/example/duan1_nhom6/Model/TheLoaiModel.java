package com.example.duan1_nhom6.Model;

public class TheLoaiModel {

    private int matl;
    private String theloai;

    public TheLoaiModel() {
    }

    public TheLoaiModel(String theloai) {
        this.theloai = theloai;
    }

    public TheLoaiModel(int matl, String theloai) {
        this.matl = matl;
        this.theloai = theloai;
    }

    public int getMatl() {
        return matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    @Override
    public String toString() {
        return theloai;
    }
}
