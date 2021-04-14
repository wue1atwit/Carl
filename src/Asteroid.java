import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Asteroid extends Sprite{


    public Asteroid(int xPos, int yPos) throws FileNotFoundException {
        super("asteroidc.png",xPos, yPos);
        Image img = new Image(new FileInputStream("asteroidc.png"));
        height = img.getHeight();
        width = img.getWidth();
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
        //hitbox.setFill(Color.YELLOW);
    }


    public void move(int change){
        xPos-=change;
    }

    public void updateHitbox(){
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
        //hitbox.setFill(Color.YELLOW);

    }

}
