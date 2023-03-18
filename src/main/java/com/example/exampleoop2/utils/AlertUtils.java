package com.example.exampleoop2.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    public static void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
