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
            String SQLTable = "create table if not exists measurements\n" +
                    "(\n" +
                    "    id      int auto_increment\n" +
                    "        primary key,\n" +
                    "    Cpr     varchar(6)                          not null,\n" +
                    "    maaling text                                 not null,\n" +
                    "    Dato    timestamp default CURRENT_TIMESTAMP null\n" +
                    ");";

            Statement stmt = connection.createStatement();
            stmt.execute(SQLTable);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void InsertIntoMeasurements(int value1, int value2) {
        //createTable();
        String SQLMeasurements = "INSERT INTO measurements (Cpr, maaling) VALUES (?,?)";
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void InsertIntoMeasurementsArray(int value1, String[] value2) {
        String SQLMeasurementsArray = "INSERT INTO measurements (Cpr, maaling) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQLMeasurementsArray);
            for (int i = 0; i < value2.length; i++) {
                preparedStatement.setInt(1, value1);
                preparedStatement.setString(2, value2 [i]);
                //preparedStatement.execute();
                //kan den bruges til at udføre større opdateringer? fx. med 1000 målinger? 2000?
                //kig på execute batch updates i MySQL.
                preparedStatement.addBatch();
                System.out.println("i =" + i);
            }
            preparedStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public ArrayList<measurementObjects> FindAllMeasurementResults(int CPR) {
        measurementObjects msObject = new measurementObjects();
        ArrayList liste = new ArrayList();

        String SQLResults = "SELECT id, maaling, Dato FROM measurements WHERE Cpr = " + CPR + ";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLResults);

            while (resultSet.next()) {
                System.out.println(
                        "id: " + resultSet.getInt("id") +
                                "   Måling: " + resultSet.getInt("maaling") +
                                "   Dato: " + resultSet.getTimestamp("Dato") + "\n"

                );
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }


}

/*public ArrayList<MeasurementObjects> FindAllMeasurementResultsByCPR (int cprTal) {
        ArrayList<MeasurementObjects> liste = new ArrayList<>();

        String SQLResults = "SELECT temperature, spO2, heartrate, time FROM measurements WHERE cpr = " + cprTal + ";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLResults);

            while (resultSet.next()) {
                liste.add(new MeasurementObjects(cprTal, resultSet.getDouble("temperature"), resultSet.getDouble("spO2"), resultSet.getDouble("heartrate")));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }*/
