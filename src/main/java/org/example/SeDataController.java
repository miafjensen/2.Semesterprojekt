package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.Database.measurementObjects;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SeDataController implements Initializable {

    @FXML
    TextField cprField;
    @FXML
    Label cprLabel;
    @FXML
    private TableView<ModelTable> tableView;

    @FXML
    private TableColumn<ModelTable, String> idColumn;
    @FXML
    private TableColumn<ModelTable, String> maalingColumn;
    @FXML
    private TableColumn<ModelTable, String> datoColumn;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    String cpr;
    int cprSearched;
    LogInController logInController = new LogInController();
    private static String user;
    private static String password; //ret personligt kodeord i DBConn klassen line 29
    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject(user, password);
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    measurementObjects mObjects = new measurementObjects();


    @FXML
    private void switchToStartside() throws IOException {
        App.setRoot("startside");
    }


    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cprLabel.setText("" + logInController.getCprTal());
    }


    @FXML
    private void searchByCpr(ActionEvent ae) throws IOException, SQLException {
        if (cprField.getText().matches("\\d{6}")) {
            cpr = cprField.getText();
            cprSearched = Integer.parseInt(cpr);

            ResultSet rs = conn.createStatement().executeQuery("SELECT id, maaling, Dato FROM measurements WHERE Cpr =" + cprSearched + ";");
            //tableView lavet ved hjælp af: https://www.youtube.com/watch?v=LoiQVoNil9Q&ab_channel=RashidIqbal
            while (rs.next()) {
                oblist.add(new ModelTable(rs.getInt("id"), rs.getInt("maaling"), rs.getTimestamp("Dato")));
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            maalingColumn.setCellValueFactory(new PropertyValueFactory<>("maaling"));
            datoColumn.setCellValueFactory(new PropertyValueFactory<>("Dato"));

            tableView.setItems(oblist);

        } else {

            //https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "\nEksempel: 120101");
            alert.showAndWait();
        }


        System.out.println(cpr + "  " + cprSearched);
    }

   /*
Dynamisk tabel fra: https://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/comment-page-1/
 */
    /*public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT * from measurements";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            for(int i = 0; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param){
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableView.getColumns().addAll(col);
                System.out.println("Column[" + i + "] ");
            }
            while (rs.next()){
                ObservableList
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/
}
