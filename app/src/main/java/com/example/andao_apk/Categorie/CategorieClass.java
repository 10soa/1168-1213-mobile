package com.example.andao_apk.Categorie;

import com.example.andao_apk.Article.ArticleClass;

import java.util.List;

public class CategorieClass {
    private String lien;
    private String _id;
    private String categorie;

    public List<ArticleClass> articles;

    private int image;

    public CategorieClass(){

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public CategorieClass(String id, String lien, String categorie) {
        this.lien = lien;
        this._id = id;
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public String get_id() {
        return _id;
    }

    public String getLien() {
        return lien;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public CategorieClass(String lien, String categorie) {
        this.lien = lien;
        this.categorie = categorie;
    }

    public List<ArticleClass> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleClass> articles) {
        this.articles = articles;
    }

    public CategorieClass(String id,int image, String categorie) {
        this.image = image;
        this.categorie = categorie;
        this._id = id;
    }


    public List<CategorieClass> getCategories(){
        return null;
    }

}
