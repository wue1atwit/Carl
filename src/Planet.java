import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Planet extends Sprite{


    public Planet(String image,int xPos, int yPos) throws FileNotFoundException {
        super(image,xPos, yPos);
        Image img = new Image(new FileInputStream(image));
        height = img.getHeight();
        width = img.getWidth();
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
        hitbox.setFill(Color.YELLOW);
    }


    public void move(int change){
        xPos-=change;
    }


}
