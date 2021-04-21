import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LifeUp extends Sprite{


    public LifeUp(int xPos, int yPos) throws FileNotFoundException {
        super("images/redHearts.png",xPos, yPos);
        Image img = new Image(new FileInputStream("images/redHearts.png"));
        height = img.getHeight();
        width = img.getWidth();
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
        hitbox.setFill(Color.YELLOW);
    }


    public void move(int change){
        xPos-=change;
    }


}
