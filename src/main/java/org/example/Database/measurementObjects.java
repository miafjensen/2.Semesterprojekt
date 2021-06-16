package org.example.Database;

import java.sql.Timestamp;
import java.util.ArrayList;

public class measurementObjects {


    private Timestamp Dato;
    private int Cpr, Måling, Id;

    public int getCPR() {
        return Cpr;
    }

    public void setCPR(int value1) {
        this.Cpr = Cpr;
    }


    public int getMåling() {
        return Måling;
    }

    public void setMåling(int value2) {
        this.Måling = Måling;
    }

    public int getId() {return Id;}

    public Timestamp getDato() { return Dato; }
}
