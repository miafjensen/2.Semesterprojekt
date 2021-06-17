package org.example.Database;
// hej der
import java.sql.*;

public class DBConn {
    private  Connection connectionobject;
    String DB_url = "jdbc:mysql://localhost:3306/semesterprojekt2";
    private String user = "root";
    private String password = "1234mySQL"; // personligt kodeord
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
                    "ope/Amsterdam&amp", user, password);


        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        if(connectionobject!=null){
            System.out.println("forbindelse til mias db oprettet");
        }
        return connectionobject;
    }




    public DBConn(){


    }

}
