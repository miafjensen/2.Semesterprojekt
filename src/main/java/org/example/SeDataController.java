package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Database.DBConn;
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
    private TableView<MeasurementObjects> tableView;
    @FXML
    private TableColumn<MeasurementObjects, String> idColumn;
    @FXML
    private TableColumn<MeasurementObjects, String> maalingColumn;
    @FXML
    private TableColumn<MeasurementObjects, String> datoColumn;

    ObservableList<MeasurementObjects> oblist = FXCollections.observableArrayList();
    String cpr;
    int cprSearched;
    LogInController logInController = new LogInController();
    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject();

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
        setCpr();
    }

    private void setCpr() {
        //inspireret af: https://stackoverflow.com/questions/24409550/how-to-pass-a-variable-through-javafx-application-to-the-controller
        cprLabel.setText("" + logInController.getCpr());  // henter og viser cpr fra LogIn på cprLabel
    }

    @FXML
    private void searchByCpr(ActionEvent ae) throws IOException, SQLException { //bruger indtastede cpr til at søge på matchene data i databasen
        if (cprField.getText().matches("\\d{6}")) {  //kontrollerer indtastningen, genbrugt fra logIn
            tableView.getItems().clear();
            cpr = cprField.getText();
            cprSearched = Integer.parseInt(cpr);
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, maaling, Dato FROM measurements WHERE Cpr =" + cprSearched + ";");
            //tableView lavet ved hjælp af: https://www.youtube.com/watch?v=LoiQVoNil9Q&ab_channel=RashidIqbal
            while (rs.next()) {
                oblist.add(new MeasurementObjects(rs.getInt("id"), rs.getString("maaling"), rs.getTimestamp("Dato")));
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            maalingColumn.setCellValueFactory(new PropertyValueFactory<>("maaling"));
            datoColumn.setCellValueFactory(new PropertyValueFactory<>("Dato"));
            tableView.setItems(oblist);
            System.out.println(cpr + " søgte på: " + cprSearched);
        } else {
            //Pop-up vindue ved fejl i CPR genbrugt fra logIn
            //https://code.makery.ch/blog/javafx-dialogs-official/
            System.out.println("fejl i CPR: " + cprField.getText());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "\nEksempel: 120101");
            alert.showAndWait();
        }
    }

}
