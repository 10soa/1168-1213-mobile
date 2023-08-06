package com.example.andao_apk.Utilisateur;

public class ShareClass {

    private String libelle;
    private String description;
    private String image;
    private String localisation;
    private int note;

    public ShareClass(String libelle, String description, String image, String localisation, int note) {
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.localisation = localisation;
        this.note = note;
    }

    public ShareClass(){}

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
