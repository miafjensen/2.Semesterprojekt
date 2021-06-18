package org.example.Database;

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
        //System.out.println(connection.getClientInfo());

        // test  indsætning i table
        for (int i = 0; i < 50; i++) {
            int s = (int)(Math.random()*4095);
            int cpr = 161198;
            cm.InsertIntoMeasurements(cpr, s);
        }

        //test udtræk fra db
        int cprTal = 161198;
        ArrayList<measurementObjects> result = cm.FindAllMeasurementResults(cprTal);
        for (measurementObjects r : result) {
           // System.out.print(" ID " + r.getId() + "   Måling " + r.getMaaling() + "   Dato " + r.getDato() + "\n");
        }
    }
}
