package com.example.duan1_nhom6.Model;

public class RapModel {
    private int marap;
    private String rap;

    public RapModel() {
    }

    public RapModel(String rap) {
        this.rap = rap;
    }

    public RapModel(int marap, String rap) {
        this.marap = marap;
        this.rap = rap;
    }

    public int getMarap() {
        return marap;
    }

    public void setMarap(int marap) {
        this.marap = marap;
    }

    public String getRap() {
        return rap;
    }

    public void setRap(String rap) {
        this.rap = rap;
    }

    @Override
    public String toString() {
        return rap;
    }
}
