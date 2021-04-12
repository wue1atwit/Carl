import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LifeHeart {
    private static int lives;

    private ArrayList<ImageView> hearts = new ArrayList<>();
    HBox heartBox = new HBox(10);

    public LifeHeart(int lives) throws FileNotFoundException {
        this.lives = lives;
        for(int i = 0; i < lives; i++){
            hearts.add(new ImageView(new Image(new FileInputStream("redHearts.png"))));
            heartBox.getChildren().add(hearts.get(i));
        }

    }

    public Node getGraphic(){
        return heartBox;
    }

    public void removeLives(){
        heartBox.getChildren().remove(hearts.get(lives-1));
        lives--;
    }

    public void addLives(){
        if(lives >= 3){
            lives = 3;
        } else {
            heartBox.getChildren().add(hearts.get(lives));
            lives++;
        }
    }

    public int getLives(){
        return lives;
    }



}
