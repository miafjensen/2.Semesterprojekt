package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartsideController {


    @FXML
    Label cprLabel;

    @FXML
    private void switchToNyMaaling() throws IOException {
        //App.setRoot();
        LineChartApp.setRoot("nyMaaling");
    }
    @FXML
    private void switchToLogIn() throws IOException {
        LineChartApp.setRoot("logIn");
    }

    @FXML
    private void switchToSeData() throws IOException {
        App.setRoot("seData");
    }

    public void setCpr(String cprTal) {
        cprLabel.setText("" + cprTal);
    }
}
