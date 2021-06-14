package org.example.Database;
import java.sql.*;
import java.util.ArrayList;

public class MeasurementConn {

    // Baseret på kode lavet i løbet af semesteret i IT2

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public MeasurementConn(Connection connection) { this.connection = connection; }

    public void createTable () {
        String SQLTable = "CREATE TABLE if not exists Measurements (\n" +
                "                                            id int NOT NULL AUTO_INCREMENT,\n" +
                "                                            Cpr varchar(6) NOT NULL,\n" +
                "                                            Måling int NOT NULL,\n" +
                "                                            Dato timestamp NOT NULL,\n" +
                "                                            PRIMARY KEY (id)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
        try{
            preparedStatement = connection.prepareStatement(SQLTable);
            preparedStatement.execute();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    public void InsertIntoMeasurements (int value1, int value2){
        String SQLMeasurements = "INSERT INTO measurements (Cpr, Måling) VALUES (?,?)";
        try{
            preparedStatement = connection.prepareStatement(SQLMeasurements);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, value2);
            preparedStatement.execute();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public ArrayList<measurementObjects> FindAllMeasurementResults (int cprTal) {
        measurementObjects msObject = new measurementObjects();
        ArrayList liste = new ArrayList();

        String SQLResults = "SELECT Måling, Dato FROM measurements WHERE Cpr = " + cprTal + ";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLResults);

            while (resultSet.next()) {
                System.out.println(
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
