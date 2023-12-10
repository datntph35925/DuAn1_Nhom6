package com.example.duan1_nhom6.Model;

public class SuatChieuModel {

    private int masc;
    private String suatchieu;

    public SuatChieuModel() {
    }

    public SuatChieuModel(String suatchieu) {
        this.suatchieu = suatchieu;
    }

    public SuatChieuModel(int masc, String suatchieu) {
        this.masc = masc;
        this.suatchieu = suatchieu;
    }

    public int getMasc() {
        return masc;
    }

    public void setMasc(int masc) {
        this.masc = masc;
    }

    public String getSuatchieu() {
        return suatchieu;
    }

    public void setSuatchieu(String suatchieu) {
        this.suatchieu = suatchieu;
    }

    @Override
    public String toString() {
        return suatchieu;
    }
}
