import java.io.FileNotFoundException;

public class Asteroid extends Sprite{

    public Asteroid(int xPos, int yPos) throws FileNotFoundException {
        super("asteroid.png",xPos, yPos);
    }

    public void move() {

    }

    public void move(int change){
        xPos-=change;
    }
}
