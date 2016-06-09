package com.gio.ctic.doctor.Pacient;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giovanny on 08/06/16.
 */
public class Paciente implements Parcelable {

    private String nombre;
    private String dni;
    private String id;
    private String idDoc;

    public Paciente(String nombre, String dni, String id,String idDoc) {

        this.nombre = nombre;
        this.dni = dni;
        this.id = id;
        this.idDoc = idDoc;
    }

    protected Paciente(Parcel in) {
        nombre = in.readString();
        dni = in.readString();
        id = in.readString();
        idDoc = in.readString();
    }


    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdDoc() {
        return idDoc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(dni);
        dest.writeString(id);
        dest.writeString(idDoc);
    }
}
