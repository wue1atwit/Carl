import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Sprite implements Drawable{
    protected ImageView graphic;
    protected int xPos;
    protected int yPos;
    protected double height;
    protected double width;
    protected Rectangle hitbox;

    public Sprite(String file, int xPos, int yPos) throws FileNotFoundException {
        this.xPos=xPos;
        this.yPos=yPos;
        graphic = new ImageView(new Image(new FileInputStream(file)));

    }

    public void draw() {
        graphic.setX(xPos);
        graphic.setY(yPos);
    }

    public void updateHitbox(){
        hitbox.setX(this.getXPos());
        hitbox.setY(this.getYPos());
    }

    public int getXPos() {
        return this.xPos;
    }
    public int getYPos() {
        return this.yPos;
    }
    public void setXPos(int xPos){
        this.xPos = xPos;
    }
    public void setYPos(int yPos){
        this.yPos = yPos;
    }
    public ImageView getGraphic() {
        return graphic;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }




}
