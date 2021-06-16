package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SeDataController {


    @FXML
    Label cprLabel;

    @FXML
    private void switchToStartside() throws IOException {
               App.setRoot("startside");
    }

    @FXML
    private void switchToNyMaaling() throws IOException {
        App.setRoot("nyMaaling");
    }
    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }

    public void setCpr(String cpr) {
        cprLabel.setText(cpr);
    }
}
