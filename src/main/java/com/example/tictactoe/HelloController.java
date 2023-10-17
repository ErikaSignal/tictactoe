package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLetsPlayButtonAction() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}