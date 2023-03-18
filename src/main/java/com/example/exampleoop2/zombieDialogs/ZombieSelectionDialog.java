package com.example.exampleoop2.zombieDialogs;

import com.example.exampleoop2.utils.ZombieObjectManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.IntStream;

public class ZombieSelectionDialog {
    public static void display(String textLabel, ObjectOperation operation) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ЛР №2. Маркевич Дмитро, 6ПІ-22Б");
        window.setMinWidth(250);

        Label label = new Label(textLabel);

        ComboBox<String> cBox = new ComboBox<>();

        List<String> zombies = ZombieObjectManager.getNames();

        IntStream.range(0, zombies.size())
                .mapToObj(i -> (i + 1) + " " + zombies.get(i))
                .forEach(cBox.getItems()::add);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Button okButton = new Button("OK");
        okButton.setOnAction(actionEvent -> {
            if(cBox.getValue() != null) {
                String[] strChoice = cBox.getValue().split(" ");
                int choice = Integer.parseInt(strChoice[0]) - 1;
                switch (operation) {
                    case CHANGE_OBJECT:
                        ZombieChangeDataDialog.display(choice);
                        break;
                    case DELETE_OBJECT:
                        ZombieObjectManager.removeZombie(choice);
                        break;
                }
            }
            window.close();
        });

        layout.getChildren().addAll(label, cBox, okButton);

        Scene scene = new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();
    }
}
