import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LifeHeart {
    private int initialLives; //The amount of hearts initially(not affected by updates)
    private static int lives; //The amount of hearts affected by updates

    private ArrayList<ImageView> hearts = new ArrayList<>(); //ArrayList of hearts
    HBox heartBox = new HBox(10); //HBox for the hearts

    public LifeHeart(int lives) throws FileNotFoundException {
        this.initialLives = lives;
        this.lives = lives;
        for(int i = 0; i < lives; i++){
            hearts.add(new ImageView(new Image(new FileInputStream("redHearts.png"))));
            heartBox.getChildren().add(hearts.get(i));
        }

    }

    public HBox getGraphic(){
        return heartBox;
    }

    public void removeLives(){
        heartBox.getChildren().remove(hearts.get(lives-1));
        lives--;
    }

    public void addLives(){
        if(lives >= initialLives){
            lives = initialLives;
        } else {
            heartBox.getChildren().add(hearts.get(lives));
            lives++;
        }
    }

    public int getLives(){
        return lives;
    }



}
