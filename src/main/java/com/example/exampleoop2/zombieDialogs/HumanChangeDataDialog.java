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

import java.util.ArrayList;
import java.util.List;

public class HumanChangeDataDialog {
    public static void display(int humanIndex) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Введіть нові параметри!");
        window.setMinWidth(250);

        List<String> paramsToChange = getHumanParameters(humanIndex);

        TextField nameText = new TextField(paramsToChange.get(0));
        TextField healthText = new TextField(paramsToChange.get(1));
        TextField damageText = new TextField(paramsToChange.get(2));
        TextField speedText = new TextField(paramsToChange.get(3));
        TextField xText = new TextField(paramsToChange.get(4));
        TextField yText = new TextField(paramsToChange.get(5));

        VBox layout = createLayout(nameText, healthText, damageText, speedText, xText, yText);

        Button okButton = new Button("OK");
        okButton.setOnAction(actionEvent -> {
            String name = nameText.getText().trim();
            String health = healthText.getText().trim();
            String damage = damageText.getText().trim();
            String speed = speedText.getText().trim();
            String x = xText.getText().trim();
            String y = yText.getText().trim();

            try {
                int healthValue = Integer.parseInt(health);
                int damageValue = Integer.parseInt(damage);
                double speedValue = Double.parseDouble(speed);
                double xValue = Double.parseDouble(x);
                double yValue = Double.parseDouble(y);


                if (healthValue > 100 || damageValue > 30 || speedValue > 10) {
                    AlertUtils.showAlert("Значення здоров'я, урону та швидкості не можуть бути більшими за 100, 30 та 10 відповідно!",
                            Alert.AlertType.ERROR);
                    return;
                }
                updateHumanData(humanIndex, name, healthValue, damageValue, speedValue, xValue, yValue);
                AlertUtils.showAlert("Ви успішно змінили дані мікрооб'єкту", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                AlertUtils.showAlert("Помилка, щось пішло не так!", Alert.AlertType.ERROR);
            } window.close();
        });

        layout.getChildren().add(okButton);

        Scene scene = new Scene(layout, 350, 450);
        window.setScene(scene);
        window.showAndWait();
    }
    private static VBox createLayout(TextField nameText, TextField healthText, TextField damageText,
                                     TextField speedText, TextField xText, TextField yText) {
        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Ім'я:");

        Label healthLabel = new Label("Здоров'я:");

        Label damageLabel = new Label("Очки пошкодження:");

        Label speedLabel = new Label("Швидкість:");

        Label xLabel = new Label("X:");

        Label yLabel = new Label("Y:");

        layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,
                damageLabel, damageText, speedLabel, speedText, xLabel, xText, yLabel, yText);

        return layout;
    }
    private static List<String> getHumanParameters(int index) {
        Human human = HumanObjectManager.getHumans().get(index);
        List<String> paramsToChange = new ArrayList<>();

        paramsToChange.add(human.getName());
        paramsToChange.add(String.valueOf(human.getHealth()));
        paramsToChange.add(String.valueOf(human.getDamage()));
        paramsToChange.add(String.valueOf(human.getSpeed()));
        paramsToChange.add(String.valueOf(human.getX()));
        paramsToChange.add(String.valueOf(human.getY()));

        return paramsToChange;
    }
    private static void updateHumanData(int index, String name, int health, int damage,
                                        double speed, double x, double y) {
        Human human = HumanObjectManager.getHumans().get(index);

        human.setName(name);
        human.setHealth(health);
        human.setDamage(damage);
        human.setSpeed(speed);
        human.setPosition(x, y);
    }
}
