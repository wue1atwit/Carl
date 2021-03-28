public class Asteroid extends Sprite{

    public Asteroid(int xPos, int yPos, int size) {
        super(xPos, yPos, size);
    }

    public void move() {

    }

    public void move(int change){
        xPos-=change;
    }
}
