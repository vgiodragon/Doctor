package com.gio.ctic.doctor.Otros;

/**
 * Created by giovanny on 08/06/16.
 */
public class Historial {
    private String fecha;
    private String sintomas;
    private String descripcion;
    private String id_doc;
    private String id_pac;

    public Historial(){

    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIddoctor(String iddoctor) {
        this.id_doc = iddoctor;
    }

    public void setId_pac(String id_pac) {
        this.id_pac = id_pac;
    }

    public String getId_pac() {
        return id_pac;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDoctor() {
        return id_doc;
    }

    @Override
    public String toString() {
        return  fecha +"@!" +
                sintomas +"@!"+
                descripcion + "@!" +
                id_doc + "@!" +
                id_pac + "@!";
    }
}
