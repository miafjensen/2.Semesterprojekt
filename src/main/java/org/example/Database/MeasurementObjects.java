package org.example;

import java.sql.Timestamp;

public class MeasurementObjects {
    int id;
    String maaling;
    Timestamp dato;

    public MeasurementObjects(int id, String maaling, Timestamp dato) {
        this.id = id;
        this.maaling = maaling;
        this.dato = dato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaaling() {
        return maaling;
    }

    public void setMaaling(String maaling) {
        this.maaling = maaling;
    }

    public Timestamp getDato() {
        return dato;
    }

    public void setDato(Timestamp dato) {
        this.dato = dato;
    }
}
