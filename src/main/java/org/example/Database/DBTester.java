package org.example.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTester {

    private static String user = "root";
    private static String password = "1246Mfj777sql";
    public static void main(String[] args) throws SQLException {

        DBConn DBConnklasse = new DBConn();
        Connection conn = DBConnklasse.getConnectionobject(user,password);
       // System.out.println( connection.getClientInfo());
    }
}
