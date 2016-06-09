package com.gio.ctic.doctor.Otros;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giovanny on 09/06/16.
 */
public class Doctor implements Parcelable {
    private String id_doc;
    private String nom_doc;
    private String esp_doc;

    public Doctor(String nom_doc,String id_doc, String esp_doc) {
        this.id_doc = id_doc;
        this.nom_doc = nom_doc;
        this.esp_doc = esp_doc;
    }

    protected Doctor(Parcel in) {
        id_doc = in.readString();
        nom_doc = in.readString();
        esp_doc = in.readString();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public String getId_doc() {
        return id_doc;
    }

    public String getNom_doc() {
        return nom_doc;
    }

    public String getEsp_doc() {
        return esp_doc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_doc);
        dest.writeString(nom_doc);
        dest.writeString(esp_doc);
    }
}
