package org.example.Database;

import javafx.scene.control.Alert;

import java.sql.*;

public class DBConn {
    private  Connection connectionobject;
    String DB_url = "jdbc:mysql://localhost:3306/Semesterprojekt2";
    //String user = "root";
    //String password = "1234mySQL"; // personligt kodeord
    private String JDBC_driver = "com.mysql.cj.jdbc.Driver";

    //status fra William - jeres Connection er oprettet, I manglede lige et par dependencies fra Maven
    /*
    Derudover skulle I lige have lidt hjælp til syntaxen med at oprette forbindelse -
    Forslag - skriv i jeres afprøvningsafsnit at for at teste jeres forbindelse, har I sat en "If" struktur
    ind for at teste om forbindelsen er != null. Heraf kan I bygge videre med preparedStatements til inds
    at sætte ind og Statements til at finde dem igen.
     */



    public Connection getConnectionobject(String user, String password) {

        try{
            Class.forName(JDBC_driver);
            connectionobject = DriverManager.getConnection(DB_url+"?serverTimezone=Eur" +
                    "ope/Amsterdam&amp", "root", "Emmeb.10");


        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("Tjek om password og DB_url er den rigtige til din database i DBConn klassen, henholdvis linje 29 og 9");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i forbindelse til Database");
            alert.setHeaderText("IKKE FORBUNDET TIL DATABASE");
            alert.setContentText("Tjek om password og DB_url er den " +
                    "\nrigtige til din database i DBConn klassen" +
                    "\nhenholdsvis linje 29 og 9.");
            alert.showAndWait();
        }
        if(connectionobject!=null){
            System.out.println("forbindelse til din database oprettet");
        }
        return connectionobject;
    }




    public DBConn(){


    }

}
