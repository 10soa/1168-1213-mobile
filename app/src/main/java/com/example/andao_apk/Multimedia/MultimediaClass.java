package com.example.andao_apk.Multimedia;

public class MultimediaClass {

    private String lien;
    private String id;
    private int image;

    public MultimediaClass(){

    }

    public MultimediaClass(String lien, String id, int image) {
        this.lien = lien;
        this.id = id;
        this.image = image;
    }

    public MultimediaClass(String lien, int image) {
        this.lien = lien;
        this.image = image;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
