package org.example.Database;

import org.example.MeasurementObjects;
import java.sql.*;
import java.util.ArrayList;

public class MeasurementDTO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MeasurementDTO(Connection connection) {
        this.connection = connection;
        createTable();
        // opretter automatisk table når der oprettes forbindelse til sql,
        // hvis den ikke allerede eksisterer i databasen
    }

    public void createTable() {
        try {
            String SQLTable = "create table if not exists measurements ( id int auto_increment primary key," +
            "Cpr varchar(6) not null, maaling text not null, Dato timestamp default CURRENT_TIMESTAMP null);";
            Statement stmt = connection.createStatement();
            stmt.execute(SQLTable);
            System.out.println("table oprettet");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Test til indsætning i database table med et statement ad gangen
    public void InsertIntoMeasurements(int value1, int value2) {
        String SQLMeasurements = "INSERT INTO measurements (Cpr, maaling) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQLMeasurements);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, value2);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Indsætning af flere større mængder på en gang ved brug af batch
    public void InsertIntoMeasurementsArray(int value1, String[] value2) {
        String SQLMeasurementsArray = "INSERT INTO measurements (Cpr, maaling) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQLMeasurementsArray);
            for (int i = 0; i < value2.length; i++) {
                preparedStatement.setInt(1, value1);
                preparedStatement.setString(2, value2 [i]);
                preparedStatement.addBatch();
                System.out.println("i =" + i);
            }
            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<MeasurementObjects> FindAllMeasurementResults(int CPR) {

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

