package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StartsideController implements Initializable {

    LogInController logInController = new LogInController();

    @FXML
    Label cprLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cprLabel.setText("" + logInController.getCprTal());
            //inspireret af: https://stackoverflow.com/questions/24409550/how-to-pass-a-variable-through-javafx-application-to-the-controller
    }

    @FXML
    private void switchToNyMaaling() throws IOException {
        //App.setRoot();
        App.setRoot("nyMaaling");
    }

    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void switchToSeData() throws IOException {
        App.setRoot("seData");
    }


}
