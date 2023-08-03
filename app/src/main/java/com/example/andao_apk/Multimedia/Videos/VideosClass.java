package com.example.andao_apk.Multimedia.Videos;

public class VideosClass {

    String id;
    String lien;
    String libelle;

    public VideosClass(){}

    public VideosClass(String id, String lien, String libelle) {
        this.id = id;
        this.lien = lien;
        this.libelle = libelle;
    }

    public VideosClass(String lien) {
        this.lien = lien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
