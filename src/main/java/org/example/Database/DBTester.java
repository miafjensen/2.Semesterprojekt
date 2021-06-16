package org.example.Database;

import java.sql.Connection;
import java.sql.SQLException;

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
        cm.InsertIntoMeasurements((int) 140499,(int) 5667);


    }
}
