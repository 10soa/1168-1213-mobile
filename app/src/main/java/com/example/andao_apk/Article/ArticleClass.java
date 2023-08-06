package com.example.andao_apk.Article;

import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.Multimedia.Videos.VideosClass;
import java.util.List;

public class ArticleClass{
    private String _id;

    private int imageA;
    private String categorie;

    private int imageCat;
    private String image;
    private List<MultimediaClass> images;
    private String libelle;
    private String description;
    private double x;
    private double y;
    private String localisation;
    private String site;
    private String autres;
    private String court_description;
    private String mot_cle;
    private List<VideosClass> videos;

    public List<VideosClass> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosClass> videos) {
        this.videos = videos;
    }

    private String imageCategorie;

    public String getImageCategorie() {
        return imageCategorie;
    }

    public void setImageCategorie(String imageCategorie) {
        this.imageCategorie = imageCategorie;
    }

    public int getImageA() {
        return imageA;
    }

    public void setImageA(int imageA) {
        this.imageA = imageA;
    }



    public ArticleClass(String _id, String categorie, int imageCat, String image, String libelle, String court_description,String description, int imageA) {
        this._id = _id;
        this.categorie = categorie;
        this.imageCat = imageCat;
        this.image = image;
        this.libelle = libelle;
        this.court_description = court_description;
        this.description = description;
        this.imageA = imageA;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getImageCat() {
        return imageCat;
    }

    public void setImageCat(int imageCat) {
        this.imageCat = imageCat;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAutres() {
        return autres;
    }

    public void setAutres(String autres) {
        this.autres = autres;
    }

    public String getCourt_description() {
        return court_description;
    }

    public void setCourt_description(String court_description) {
        this.court_description = court_description;
    }

    public String getMot_cle() {
        return mot_cle;
    }

    public void setMot_cle(String mot_cle) {
        this.mot_cle = mot_cle;
    }

    public List<MultimediaClass> getImages() {
        return images;
    }


    public ArticleClass(String _id, String categorie, String image, List<MultimediaClass> images, String libelle, String description, double x, double y, String localisation, String site, String autres, String court_description, List<VideosClass> videos, int imageCategorie) {
        this._id = _id;
        this.categorie = categorie;
        this.image = image;
        this.images = images;
        this.libelle = libelle;
        this.description = description;
        this.x = x;
        this.y = y;
        this.localisation = localisation;
        this.site = site;
        this.autres = autres;
        this.court_description = court_description;
        this.videos = videos;
        this.imageCat = imageCategorie;
    }

    public void setImages(List<MultimediaClass> images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArticleClass(){}
}
