package org.example.Database;

import java.sql.*;
import java.util.ArrayList;

public class MeasurementDTO {

    // Baseret på kode lavet i løbet af semesteret i IT2

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public MeasurementDTO(Connection connection) {
        this.connection = connection;
        /*createTable()*/
        ;
    }

    public void createTable() {
        try {
        String SQLTable = "CREATE TABLE if not exists MeasurementsAgain (\n" +
                "                                            id int NOT NULL AUTO_INCREMENT,\n" +
                "                                            Cpr varchar(6) NOT NULL,\n" +
                "                                            Måling int NOT NULL,\n" +
                "                                            Dato   timestamp default CURRENT_TIMESTAMP null\n" +
                "                                            PRIMARY KEY (id)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

            Statement stmt = connection.createStatement();
            stmt.execute(SQLTable);

            //preparedStatement = connection.prepareStatement(SQLTable);
            //preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void InsertIntoMeasurements(int value1, int value2) {
        createTable();
        String SQLMeasurements = "INSERT INTO measurements (Cpr, Måling) VALUES (?,?)";
        //i afprøvning - beskriv at I først kører med en enkelt indsætning og derefter bygger om til batches
        //batches forudsættes at den enkelte værdi kan komme ind- derfor er det vigtigt, at I
        /*
        har den del med, men kan udelade den første.
         */
        try {
            preparedStatement = connection.prepareStatement(SQLMeasurements);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, value2);
            preparedStatement.execute();
            //kan den bruges til at udføre større opdateringer? fx. med 1000 målinger? 2000?
            //kig på execute batch updates i MySQL.


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    public void InsertIntoMeasurementsArray(int value1, ArrayList<Integer> value2) {
        String SQLMeasurementsArray = "INSERT INTO measurements (Cpr, Måling) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQLMeasurementsArray);
            preparedStatement.setInt(1, value1);
            preparedStatement.setArray(2, (Array) value2);
            preparedStatement.execute();
            //kan den bruges til at udføre større opdateringer? fx. med 1000 målinger? 2000?
            //kig på execute batch updates i MySQL.


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public ArrayList<measurementObjects> FindAllMeasurementResults(int CPR) {
        measurementObjects msObject = new measurementObjects();
        ArrayList liste = new ArrayList();

        String SQLResults = "SELECT Måling, Dato FROM measurements WHERE Cpr = " + CPR + ";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLResults);

            while (resultSet.next()) {
                System.out.println(
                        "id" + resultSet.getInt("id") + "\n" +
                                "Måling" + resultSet.getInt("Måling") + "\n" +
                                "Dato" + resultSet.getTimestamp("Dato") + "\n"
                );
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }


}
