package org.example.Database;

import org.example.MeasurementObjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTester {

    private static String user;
    private static String password;
    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        DBConn DBConnklasse = new DBConn();
        Connection conn = DBConnklasse.getConnectionobject(user, password);
        MeasurementDTO cm = new MeasurementDTO(conn);


        // test  indsætning i table enkelt row ad gangen
        /*for (int i = 0; i < 50; i++) {
            int s = (int)(Math.random()*4095);
            int cpr = 161198;
            cm.InsertIntoMeasurements(cpr, s);
        }*/

        // test  indsætning i table med batch
        String[] data = new String[20];
        for (int i = 0; i < data.length; i++) {
            int s = (int) (Math.random() * 4095);
            data[i] = ""+ s;
        }
        int cpr = 123456;
       // cm.InsertIntoMeasurementsArray(cpr, data);


        //test udtræk fra db
        int cprTal = 161198;

        //cm.FindAllMeasurementResults(cprTal);

        //test af create teble
        cm.createTable();
    }
}

