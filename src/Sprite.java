import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract public class Sprite implements Moveable, Drawable{
    protected Rectangle graphic;
    protected int xPos;
    protected int yPos;
    protected int prevXPos;
    protected int prevYPos;
    protected String dir; //direction of motion
    protected int size; //size of the rectangle and how much we move

    public Sprite(int xPos, int yPos, int size) {
        this.xPos=xPos;
        this.yPos=yPos;
        this.size=size;
        graphic = new Rectangle(xPos,yPos,size,size);
        graphic.setFill(Color.CYAN);
        prevXPos=xPos;
        prevYPos=yPos;
        dir="UP";
    }

    public void draw() {
        graphic.setX(xPos);
        graphic.setY(yPos);
    }

    public int getXPos() {
        return this.xPos;
    }
    public int getYPos() {
        return this.yPos;
    }
    public int getPrevXPos() {
        return this.prevXPos;
    }
    public int getPrevYPos() {
        return this.prevYPos;
    }
    public String getDir() {
        return dir;
    }
    public int getSize() {
        return size;
    }
    public Rectangle getGraphic() {
        return graphic;
    }
    public void setDir(String dir) {
        this.dir=dir;
    }

}
