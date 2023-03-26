package com.example.exampleoop2.utils;

import com.example.exampleoop2.GameApplication;
import com.example.exampleoop2.gameEntities.Human;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HumanObjectManager {
    private static final List<Human> zombies = new ArrayList<>();
    public static void addHuman(Human zombie) {
        zombies.add(zombie);
    }
    public static void removeZombie(int index) {
        try {
            Human zombieToRemove = getHumans().get(index);
            GameApplication.root.getChildren().remove(zombieToRemove.getImage());
            GameApplication.root.getChildren().remove(zombieToRemove.getLineOfHealth());
            GameApplication.root.getChildren().remove(zombieToRemove.getLineOfMaxHealth());
            GameApplication.root.getChildren().remove(zombieToRemove.getLabelName());
            zombies.remove(zombieToRemove);

            AlertUtils.showAlert("Ви успішно видалили мікрооб'єкт!", Alert.AlertType.INFORMATION);
        }
        catch (Exception e) {
            AlertUtils.showAlert("Помилка, щось пішло не так!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    public static List<Human> getHumans() {
        return zombies;
    }
    public static List<String> getNamesOfHuman() {
        return getHumans().stream()
                .map(Human::toString)
                .collect(Collectors.toList());
    }
}
