package com.example.exampleoop2.utils;

import com.example.exampleoop2.GameApplication;
import com.example.exampleoop2.gameEntities.Human;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HumanObjectManager {
    private static final List<Human> humans = new ArrayList<>();
    public static void addHuman(Human human) {
        humans.add(human);
    }
    public static void removeHuman(int index) {
        try {
            Human humanToRemove = getHumans().get(index);
            GameApplication.getRoot().getChildren().remove(humanToRemove.getImage());
            GameApplication.getRoot().getChildren().remove(humanToRemove.getLineOfHealth());
            GameApplication.getRoot().getChildren().remove(humanToRemove.getLineOfMaxHealth());
            GameApplication.getRoot().getChildren().remove(humanToRemove.getLabelName());
            humans.remove(humanToRemove);

            AlertUtils.showAlert("Ви успішно видалили мікрооб'єкт!", Alert.AlertType.INFORMATION);
        }
        catch (Exception e) {
            AlertUtils.showAlert("Помилка, щось пішло не так!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    public static List<Human> getHumans() {
        return humans;
    }
    public static List<String> getNamesOfHuman() {
        return getHumans().stream()
                .map(Human::toString)
                .collect(Collectors.toList());
    }
}
