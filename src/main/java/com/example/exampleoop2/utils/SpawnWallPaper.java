package com.example.exampleoop2.utils;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpawnWallPaper {
    private static final Group ON_SCREEN_TIMERS = new Group();
    private static Group root;

    public SpawnWallPaper() throws FileNotFoundException {
        Image mapImage = new Image(new FileInputStream("src/main/resources/com/example/exampleoop2/images/gameMap.jpg"));

        ImageView imageView = new ImageView(mapImage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        imageView.setFitWidth(screenSize.width);
        imageView.setFitHeight(screenSize.height);
        imageView.setPreserveRatio(false);

        Rectangle mapBorder = new Rectangle(0, 0, imageView.getFitWidth(), imageView.getFitHeight());
        mapBorder.setFill(Color.TRANSPARENT);
        mapBorder.setStroke(Color.RED);

        root = new Group(new Group(imageView, mapBorder), ON_SCREEN_TIMERS);
    }

    public Group getRoot() {
        return root;
    }
}