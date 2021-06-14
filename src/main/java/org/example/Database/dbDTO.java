package org.example.Database;
import java.sql.*;

public class dbDTO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public dbDTO(Connection connection) {
        this.connection = connection;

    }

    public void InsertInMeasurements (int value1, int value2){
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
}
/*


    public ArrayList<measurementObjects> FindAllMeasurementResultsByCPR (int cprTal) {
        ArrayList<measurementObjects> liste = new ArrayList<>();

        String SQLResults = "SELECT temperature, spO2, heartrate, time FROM measurements WHERE cpr = " + cprTal + ";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLResults);

            while (resultSet.next()) {
                liste.add(new measurementObjects(cprTal, resultSet.getDouble("temperature"), resultSet.getDouble("spO2"), resultSet.getDouble("heartrate")));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }*/