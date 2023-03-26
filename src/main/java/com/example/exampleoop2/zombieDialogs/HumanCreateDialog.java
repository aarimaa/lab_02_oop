package com.example.exampleoop2.zombieDialogs;

import com.example.exampleoop2.gameEntities.Human;
import com.example.exampleoop2.utils.AlertUtils;
import com.example.exampleoop2.utils.HumanObjectManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HumanCreateDialog {
    private static final int DIALOG_WIDTH = 350;
    private static final int DIALOG_HEIGHT = 430;
    private static final int MAX_HEALTH = 100;
    private static final int MAX_DAMAGE = 30;
    private static final double MAX_SPEED = 10.0;

    public static void display(double x, double y) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Введіть параметри нової людини!");
        window.setMinWidth(250);

        TextField nameText = new TextField();
        TextField healthText = new TextField();
        TextField damageText = new TextField();
        TextField speedText = new TextField();
        nameText.setPromptText("Ім'я");
        healthText.setPromptText("від 1 до 100");
        damageText.setPromptText("від 1 до 30");
        speedText.setPromptText("від 1 до 10");

        VBox layout = createLayout(nameText, healthText, damageText, speedText, x, y);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            String name = nameText.getText().trim();
            String health = healthText.getText().trim();
            String damage = damageText.getText().trim();
            String speed = speedText.getText().trim();

            try {
                if (name.isEmpty() && health.isEmpty() && damage.isEmpty() && speed.isEmpty()) {
                    HumanObjectManager.addHuman(new Human());
                    AlertUtils.showAlert("Ви успішно створили новий мікрооб'єкт", Alert.AlertType.INFORMATION);
                    window.close();
                    return;
                }

                int healthValue = Integer.parseInt(health);
                int damageValue = Integer.parseInt(damage);
                double speedValue = Double.parseDouble(speed);

                if (!isValidData(healthValue, damageValue, speedValue)) {
                    AlertUtils.showAlert(
                            "Значення здоров'я, урону та швидкості повинні бути в межах 1-100, 1-30 та 1-10 відповідно!",
                            Alert.AlertType.ERROR
                    );
                    return;
                }
                HumanObjectManager.addHuman(new Human(name, healthValue, damageValue, speedValue, x, y));
                AlertUtils.showAlert("Ви успішно створили новий мікрооб'єкт", Alert.AlertType.INFORMATION);
                window.close();

            } catch (NumberFormatException e) {
                AlertUtils.showAlert("Помилка, введені некоректні дані!", Alert.AlertType.ERROR);
            }
        });

        layout.getChildren().add(okButton);

        Scene scene = new Scene(layout, DIALOG_WIDTH, DIALOG_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }
    private static boolean isValidData(int health, int damage, double speed) {
        return (health >= 1 && health <= MAX_HEALTH)
                && (damage >= 1 && damage <= MAX_DAMAGE)
                && (speed >= 1.0 && speed <= MAX_SPEED);
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
                yLabel, yText
        );

        return layout;
    }
}