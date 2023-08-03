package com.example.andao_apk.Multimedia;

public class MultimediaClass {

    private String lien;
    private String id;
    private String image;

    public MultimediaClass(){

    }

    public MultimediaClass(String lien, String id, String image) {
        this.lien = lien;
        this.id = id;
        this.image = image;
    }

    public MultimediaClass(String lien, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
