package com.example.exampleoop2.gameEntities;

import com.example.exampleoop2.GameApplication;

import com.example.exampleoop2.utils.HumanObjectManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class Human {
    private Label labelName;
    private ImageView image;
    private Line lineOfHealth;
    private Line lineOfMaxHealth;
    private String name;
    private int health;
    private int damage;
    private double speed;
    private double x;
    private double y;
    private boolean isActive;

    static {
        System.out.println("Статичний блок ініціалізації класу Human");
    }

    {
        System.out.println("Нестатичний блок ініціалізації класу Human");
    }

    public Human(String name, int health, int damage, double speed,
                 double x, double y) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.isActive = false;

        labelName = new Label(name);
        labelName.setLayoutX(x + 20);
        labelName.setLayoutY(y - 30);
        labelName.setFont(Font.font("System", FontWeight.BOLD, 15));
        labelName.setAlignment(Pos.TOP_CENTER);
        labelName.setTextFill(Color.RED);
        Label label = new Label(" ");

        lineOfMaxHealth = new Line(x, y, x + 100, y);
        lineOfMaxHealth.setStrokeWidth(5);
        lineOfMaxHealth.setStroke(Color.LIGHTGRAY);

        lineOfHealth = new Line(x, y, x + health, y);
        lineOfHealth.setStrokeWidth(5);
        lineOfHealth.setStroke(Color.RED);

        image = new ImageView(new Image(Objects.requireNonNull(
                GameApplication.class.getResource("images/human.png")).toString(),90,150,false,false));
        image.setX(x);
        image.setY(y);

        System.out.println("Створено нову людину з ім'ям " + this.name);
        GameApplication.root.getChildren().addAll(image, labelName, lineOfMaxHealth, lineOfHealth, label);
    }

    public Human() {
        System.out.println("Створення нового зомбі з використанням конструктора без параметрів!");
        HumanObjectManager.getHumans().add(new Human("Human", 85, 15, 5.0, 100, 150));
    }

    public void move(double distance) {
        System.out.println(name + " пройшов " + distance + " метрів");
    }
    public void heal(int amount) {
        health += amount;
        System.out.println(name + " був вилікуваний і отримав " + amount +
                " здоров'я. Поточний стан здоров'я: " + health);
    }
    public void getAttack(int damage) {
        health -= damage;
        System.out.println(name + " був атакований і втратив " + damage +
                " здоров'я. Поточний стан здоров'я: " + health);
    }
    public void rest() {
        health += 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        labelName.setText(name);
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        try {
            this.health = health;
        }
        catch(Exception e){
            this.health = 0;
        }

        GameApplication.root.getChildren().remove(lineOfHealth);

        lineOfHealth = new Line(x, y, x + health, y);
        lineOfHealth.setStrokeWidth(5);
        lineOfHealth.setStroke(Color.RED);

        GameApplication.root.getChildren().add(lineOfHealth);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPosition(double x, double y) {
        try {
            this.x = x;
            this.y = y;
        } catch (Exception e) {
            this.x = 0.0;
            this.y = 0.0;
        }

        labelName.setLayoutX(x);
        labelName.setLayoutY(y);

        GameApplication.root.getChildren().remove(lineOfHealth);

        lineOfHealth = new Line(x, y, x + health, y);
        lineOfHealth.setStrokeWidth(5);
        lineOfHealth.setStroke(Color.RED);

        GameApplication.root.getChildren().add(lineOfHealth);

        image.setX(x);
        image.setY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getImage() {
        return image;
    }

    public Label getLabelName() {
        return labelName;
    }

    public Line getLineOfHealth() {
        return lineOfHealth;
    }

    public Line getLineOfMaxHealth() {
        return lineOfMaxHealth;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return health == human.health &&
                damage == human.damage &&
                Double.compare(human.speed, speed) == 0 &&
                Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, damage, speed);
    }

    @Override
    public String toString() {
        return "Zombie{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", speed=" + speed + '}';
    }
}
