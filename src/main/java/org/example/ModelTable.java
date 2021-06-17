package org.example;

import java.sql.Timestamp;

public class ModelTable {
    int id;
    int Måling;
    Timestamp dato;

    public ModelTable(int id, int Måling, Timestamp dato) {
        this.id = id;
        this.Måling = Måling;
        this.dato = dato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMåling(int måling) {
        return måling;
    }

    public void setMåling(int måling) {
        this.Måling = måling;
    }

    public Timestamp getDato() {
        return dato;
    }

    public void setDato(Timestamp dato) {
        this.dato = dato;
    }
}
