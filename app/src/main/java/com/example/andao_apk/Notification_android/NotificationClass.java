package com.example.andao_apk.Notification_android;

public class NotificationClass {

    int image;

    String id;
    String libelle;
    String article_id;
    String description;
    String date;
    public NotificationClass(){

    }

    public NotificationClass(int image, String id, String libelle, String article_id, String description, String date) {
        this.image = image;
        this.id = id;
        this.libelle = libelle;
        this.article_id = article_id;
        this.description = description;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = "le : "+date;
    }
}
