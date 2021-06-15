package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartsideController {


    @FXML
    Label cprLabel;

    @FXML
    private void switchToNyMaaling() throws IOException {
        App.setRoot("nyMaaling");
    }


    public void setCpr(String cpr) {
        cprLabel.setText(cpr);
    }
}
