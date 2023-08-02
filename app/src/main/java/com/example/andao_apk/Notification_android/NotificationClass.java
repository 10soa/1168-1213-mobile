package com.example.andao_apk.Notification_android;

public class NotificationClass {

    int image;
    String titre;
    String description;

    public NotificationClass(int image, String titre,String description) {
        this.image = image;
        this.titre = titre;
        this.description=description;
    }

    public NotificationClass(){

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String lien) {
        this.titre = lien;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
