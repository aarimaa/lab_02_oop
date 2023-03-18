package com.example.exampleoop2;

import com.example.exampleoop2.utils.SoundPlayer;
import com.example.exampleoop2.zombieDialogs.ObjectOperation;
import com.example.exampleoop2.zombieDialogs.ZombieSelectionDialog;
import com.example.exampleoop2.zombieDialogs.ZombieCreateDialog;

import com.example.exampleoop2.utils.SpawnWallPaper;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import static com.example.exampleoop2.utils.SoundPlayer.BACKGROUND_MUSIC_PATH;

public class GameApplication extends Application {
    public static final Group root = new Group();
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        root.getChildren().add(new SpawnWallPaper().getRoot());

        SoundPlayer.playSound(BACKGROUND_MUSIC_PATH, true);

        Scene scene = new Scene(root, 1300, 750);

        stage.setTitle("ЛР №2. Маркевич Дмитро, 6ПІ-22Б");
        stage.setScene(scene);
        stage.show();

        scene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                ZombieCreateDialog.display(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.C) {
                ZombieSelectionDialog.display("Оберіть об'єкт для змін:", ObjectOperation.CHANGE_OBJECT);
            }
            else if (keyEvent.getCode() == KeyCode.DELETE){
                ZombieSelectionDialog.display("Оберіть об'єкт для видалення:", ObjectOperation.DELETE_OBJECT);
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}