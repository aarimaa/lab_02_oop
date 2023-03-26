package com.example.exampleoop2;

import com.example.exampleoop2.zombieDialogs.ObjectOperation;
import com.example.exampleoop2.zombieDialogs.HumanSelectionDialog;
import com.example.exampleoop2.zombieDialogs.HumanCreateDialog;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameApplication extends Application {
    private static Group root = new Group();
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Image mapImage = new Image(new FileInputStream("src/main/resources/com/example/exampleoop2/images/gameMap.png"));

        ImageView imageView = new ImageView(mapImage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        imageView.setFitWidth(screenSize.width);
        imageView.setFitHeight(screenSize.height);
        imageView.setPreserveRatio(false);

        root = new Group(new Group(imageView));
        Scene scene = new Scene(root, 1300, 750);

        stage.setTitle("Гарматюк Костянтин");
        stage.setScene(scene);
        stage.show();

        scene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                HumanCreateDialog.display(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.INSERT) {
                HumanSelectionDialog.display("Оберіть об'єкт для змін:", ObjectOperation.CHANGE_OBJECT);
            }
            else if (keyEvent.getCode() == KeyCode.DELETE){
                HumanSelectionDialog.display("Оберіть об'єкт для видалення:", ObjectOperation.DELETE_OBJECT);
            }
        });
    }

    public static Group getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}