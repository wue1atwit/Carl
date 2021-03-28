public class Carl extends Sprite{

    public Carl() {
        super(50,360,50);
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
