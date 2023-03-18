package com.example.exampleoop2.utils;

import com.example.exampleoop2.GameApplication;
import com.example.exampleoop2.gameEntities.Zombie;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZombieObjectManager {
    private static final List<Zombie> zombies = new ArrayList<>();
    public static void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }
    public static void removeZombie(int index) {
        try {
            Zombie zombieToRemove = getZombies().get(index);
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
    public static List<Zombie> getZombies() {
        return zombies;
    }
    public static List<String> getNames() {
        return getZombies().stream()
                .map(Zombie::toString)
                .collect(Collectors.toList());
    }
}
