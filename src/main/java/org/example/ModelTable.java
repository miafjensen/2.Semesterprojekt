package org.example;

import java.sql.Timestamp;

public class ModelTable {
    int id;
    int maaling;
    Timestamp dato;

    public ModelTable(int id, int maaling, Timestamp dato) {
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

    public int getMaaling() {
        return maaling;
    }

    public void setMaaling(int maaling) {
        this.maaling = maaling;
    }

    public Timestamp getDato() {
        return dato;
    }

    public void setDato(Timestamp dato) {
        this.dato = dato;
    }
}
