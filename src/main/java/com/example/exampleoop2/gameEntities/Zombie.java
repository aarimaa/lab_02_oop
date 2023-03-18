package com.example.exampleoop2.gameEntities;

import com.example.exampleoop2.GameApplication;
import static com.example.exampleoop2.utils.SoundPlayer.ZOMBIE_SOUND_PATH;

import com.example.exampleoop2.utils.SoundPlayer;
import com.example.exampleoop2.utils.ZombieObjectManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class Zombie {
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
        System.out.println("Статичний блок ініціалізації класу Zombie");
    }

    {
        System.out.println("Нестатичний блок ініціалізації класу Zombie");
    }

    public Zombie(String name, int health, int damage, double speed,
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
        labelName.setFont(Font.font("System", FontWeight.BOLD, 14));
        labelName.setAlignment(Pos.TOP_CENTER);
        labelName.setTextFill(Color.WHITE);

        Label hotFixError = new Label(" ");

        lineOfMaxHealth = new Line(x, y, x + 100, y);
        lineOfMaxHealth.setStrokeWidth(5);
        lineOfMaxHealth.setStroke(Color.LIGHTGRAY);

        lineOfHealth = new Line(x, y, x + health, y);
        lineOfHealth.setStrokeWidth(5);
        lineOfHealth.setStroke(Color.RED);

        image = new ImageView(new Image(Objects.requireNonNull(
                GameApplication.class.getResource("images/zombie.png")).toString(),100,100,false,false));
        image.setX(x);
        image.setY(y);

        System.out.println("Створено нового зомбі з ім'ям " + this.name);
        GameApplication.root.getChildren().addAll(image, labelName, lineOfMaxHealth, lineOfHealth, hotFixError);

        makeSound();
    }

    public Zombie() {
        ZombieObjectManager.getZombies().add(this);
    }

    public void move(double distance, Direction direction) {
        System.out.println(name + " пройшов " + distance + " метрів на " + direction.name());
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
    public void makeSound() {
        SoundPlayer.playSound(ZOMBIE_SOUND_PATH, false);
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
        Zombie zombie = (Zombie) o;
        return health == zombie.health &&
                damage == zombie.damage &&
                Double.compare(zombie.speed, speed) == 0 &&
                Objects.equals(name, zombie.name);
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
