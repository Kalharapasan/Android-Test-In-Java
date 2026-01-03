package com.example.a20recyclerview;

public class DataClass {
    private String name;
    private int imgID;
    private int btnId;

    public DataClass(String name, int imgID, int btnId) {
        this.name = name;
        this.imgID = imgID;
        this.btnId = btnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getBtnId() {
        return btnId;
    }

    public void setBtnId(int btnId) {
        this.btnId = btnId;
    }
}
