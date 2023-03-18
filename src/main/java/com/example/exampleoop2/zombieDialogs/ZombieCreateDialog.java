package com.example.exampleoop2.zombieDialogs;

import com.example.exampleoop2.utils.ZombieObjectManager;
import com.example.exampleoop2.gameEntities.Zombie;
import com.example.exampleoop2.utils.AlertUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZombieCreateDialog {
    private static final int DIALOG_WIDTH = 350;
    private static final int DIALOG_HEIGHT = 430;

    public static void display(double x, double y) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Введіть параметри нового зомбі!");
        window.setMinWidth(250);

        TextField nameText = new TextField();
        TextField healthText = new TextField();
        TextField damageText = new TextField();
        TextField speedText = new TextField();

        VBox layout = createLayout(nameText, healthText, damageText, speedText, x, y);

        Button okButton = new Button("OK");

        okButton.setOnAction(event -> {
            String name = nameText.getText().trim();
            String health = healthText.getText().trim();
            String damage = damageText.getText().trim();
            String speed = speedText.getText().trim();

            try {
                int healthValue = Integer.parseInt(health);
                int damageValue = Integer.parseInt(damage);
                double speedValue = Double.parseDouble(speed);

                if (healthValue > 100 || damageValue > 30 || speedValue > 10) {
                    AlertUtils.showAlert(
                            "Значення здоров'я, урону та швидкості не можуть бути більшими за 100, 30 та 10 відповідно!",
                            Alert.AlertType.ERROR
                    );
                    return;
                }

                ZombieObjectManager.addZombie(new Zombie(name, healthValue, damageValue, speedValue, x, y));
                AlertUtils.showAlert("Ви успішно створили новий мікрооб'єкт", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                AlertUtils.showAlert("Помилка, щось пішло не так!", Alert.AlertType.ERROR);
            }

            window.close();
        });

        layout.getChildren().add(okButton);

        Scene scene = new Scene(layout, DIALOG_WIDTH, DIALOG_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }

    private static VBox createLayout(TextField nameText, TextField healthText,
                                     TextField damageText, TextField speedText,
                                     double x, double y) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Ім'я:");

        Label healthLabel = new Label("Здоров'я:");

        Label damageLabel = new Label("Очки пошкодження: ");

        Label speedLabel = new Label("Швидкість:");

        Label xLabel = new Label("X:");
        TextField xText = new TextField();
        xText.setText(Double.toString(x));

        Label yLabel = new Label("Y:");
        TextField yText = new TextField();
        yText.setText(Double.toString(y));

        layout.getChildren().addAll(
                nameLabel, nameText,
                healthLabel, healthText,
                damageLabel, damageText,
                speedLabel, speedText,
                xLabel, xText,
                yLabel, yText);

        return layout;
    }
}