package com.example.andao_apk.Reglages;

public class ReglagesClass {

    private int image;
    private String item;

    private String cle;

    public ReglagesClass(int image, String item, String cle) {
        this.image = image;
        this.item = item;
        this.cle = cle;
    }

    public ReglagesClass(){}

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }
}
