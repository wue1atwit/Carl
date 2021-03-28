import java.io.FileNotFoundException;

public class Carl extends Sprite{

    public Carl() throws FileNotFoundException {
        super("carlos.png",50,360);
    }

    @Override
    public void move() {

    }

    public void changeX(int change){
        xPos += change;
    }

    public void changeY(int change){
        yPos += change;
    }
}
