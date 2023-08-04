package com.example.andao_apk.Partage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class PartageClass {
    private String _id;

    private int imageA;
    private String nom;

    private int imageUser;
    private String image;
    private String libelle;
    private String description;
    private int note;
    private String localisation;
    private String prenom;
    private String mot_cle;

    private Date datePublication;

    private int sexe;

    public int getSexe() {
        return sexe;
    }



    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public int getImageA() {
        return imageA;
    }

    public void setImageA(int imageA) {
        this.imageA = imageA;
    }

    public PartageClass(String _id, String nom, String image, String libelle, int note, String prenom, Date datePublication, int sexe) {
        this._id = _id;
        this.nom = nom;
        this.image = image;
        this.libelle = libelle;
        this.note = note;
        this.prenom = prenom;
        this.datePublication = datePublication;
        this.sexe = sexe;
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


    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getMot_cle() {
        return mot_cle;
    }

    public void setMot_cle(String mot_cle) {
        this.mot_cle = mot_cle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getImageUser() {
        return imageUser;
    }

    public void setImageUser(int imageUser) {
        this.imageUser = imageUser;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Bitmap base64decoded(){
        byte[] imageBytes = android.util.Base64.decode(getImage(), android.util.Base64.DEFAULT);

        // Créez un objet Bitmap à partir des bytes décodés
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return bitmap;
    }

    public String calculateTimeAgo() {
        Date date = getDatePublication();
        Instant instant = date.toInstant();
        long secondsAgo = Duration.between(instant, Instant.now()).getSeconds();

        if (secondsAgo < 60) {
            return "il y a " + secondsAgo + "s";
        } else if (secondsAgo < 3600) {
            long minutesAgo = secondsAgo / 60;
            return "il y a " + minutesAgo + "m";
        } else if (secondsAgo < 86400) {
            long hoursAgo = secondsAgo / 3600;
            return "il y a " + hoursAgo + "h";
        } else if (secondsAgo < 604800) {
            long daysAgo = secondsAgo / 86400;
            return "il y a " + daysAgo + "j";
        } else if (secondsAgo < 2419200) {
            long weeksAgo = secondsAgo / 604800;
            return "il y a " + weeksAgo + " semaines";
        } else {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            Period period = null;
            period = Period.between(localDate, now);

            int yearsAgo = period.getYears();
            if (yearsAgo > 0) {
                return "il y a " + yearsAgo + " an" + (yearsAgo > 1 ? "s" : "");
            }

            return "il y a " + period.getMonths() + " mois";
        }
    }
}
