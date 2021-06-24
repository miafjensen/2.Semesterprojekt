package org.example.Database;

import javafx.scene.control.Alert;

import java.sql.*;

public class DBConn {
    private  Connection connectionobject;
    String schema = "Semesterprojekt2"; //kan være forskellige på tværs af databaser
    String DB_url = "jdbc:mysql://localhost:3306/" + schema;
    private String JDBC_driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnectionobject() {

        try{
            Class.forName(JDBC_driver);
            String user = "root";
            String password = "1234mySQL";
            connectionobject = DriverManager.getConnection(DB_url+"?serverTimezone=Eur" +
                    "ope/Amsterdam&amp", user, password);


        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("Tjek om schema, user og password er det rigtige" +
                    " til din database i DBConn klassen, henholdvis linje 9, 17 og 18");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i forbindelse til Database");
            alert.setHeaderText("IKKE FORBUNDET TIL DATABASE");
            alert.setContentText("Tjek om schema, user og password er det rigtige"
                    + "\ntil din database i DBConn klassen, "
                    + "\nhenholdvis linje 9, 17 og 18");
            alert.showAndWait();
        }
        if(connectionobject!=null){
            System.out.println("forbindelse til din database oprettet");
        }
        return connectionobject;
    }

}
