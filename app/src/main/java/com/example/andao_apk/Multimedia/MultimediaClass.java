package com.example.andao_apk.Multimedia;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MultimediaClass implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(lien);
        dest.writeString(image);
    }


    protected MultimediaClass(Parcel in) {
        id = in.readString();
        lien = in.readString();
        image = in.readString();
    }

    public static final Creator<MultimediaClass> CREATOR = new Creator<MultimediaClass>() {
        @Override
        public MultimediaClass createFromParcel(Parcel in) {
            return new MultimediaClass(in);
        }

        @Override
        public MultimediaClass[] newArray(int size) {
            return new MultimediaClass[size];
        }
    };
}
