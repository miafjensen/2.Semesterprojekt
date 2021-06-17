package org.example.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTester {

    private static String user = "root";
    private static String password = "1234mySQL"; //indtast jeres personlige password til localhost

    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        DBConn DBConnklasse = new DBConn();
        Connection conn = DBConnklasse.getConnectionobject(user, password);
        MeasurementDTO cm = new MeasurementDTO(conn);
        //System.out.println(connection.getClientInfo());

        // test  indsætning i table
       /* for (int i = 0; i < 50; i++) {
            int s = 10 * i;
            int cpr = 124365;
            cm.InsertIntoMeasurements(cpr, s);


        }*/

        //test udtræk fra db
        int cprTal = 767676;
        ArrayList<measurementObjects> result = cm.FindAllMeasurementResults(cprTal);
        for (measurementObjects r : result) {
           // System.out.print(" ID " + r.getId() + "   Måling " + r.getMåling() + "   Dato " + r.getDato() + "\n");
        }
    }
}
