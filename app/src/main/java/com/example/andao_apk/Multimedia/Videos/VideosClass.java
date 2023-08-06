package com.example.andao_apk.Multimedia.Videos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class VideosClass implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(lien);
        dest.writeString(libelle);
    }

    protected VideosClass(Parcel in) {
        id = in.readString();
        lien = in.readString();
        libelle = in.readString();
    }


    public static final Parcelable.Creator<VideosClass> CREATOR = new Parcelable.Creator<VideosClass>() {
        @Override
        public VideosClass createFromParcel(Parcel in) {
            return new VideosClass(in);
        }

        @Override
        public VideosClass[] newArray(int size) {
            return new VideosClass[size];
        }
    };
}
