import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Carl extends Sprite{
    private double height;
    private double width;
    private Rectangle hitbox;

    public Carl() throws FileNotFoundException {
        super("carls.png",50,360);
        Image img = new Image(new FileInputStream("carls.png"));
        height = img.getHeight();
        width = img.getWidth();
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
        //hitbox.setFill(Color.YELLOW);

    }

    public void updateHitbox(){
        hitbox = new Rectangle(this.getXPos(),this.getYPos(),width,height);
    }

    public void resetPos(){
        yPos = 360;
    }

    public void changeX(int change){
        xPos += change;
    }

    public void changeY(int change){
        yPos += change;
    }

    public boolean collidesWith(Sprite sprite){

        if(this.getHitbox().getBoundsInParent().intersects(sprite.getHitbox().getBoundsInParent())){
            return true;
        } else{
            return false;
        }

    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}
