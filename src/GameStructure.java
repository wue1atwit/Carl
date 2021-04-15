import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class GameStructure {


    private static int level = 1;
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();
    private static ArrayList<LifeUp> lifeUp = new ArrayList<>();
    private EventHandler<ActionEvent> handler;
    private int numOfAsteroids = 70;
    private int numOfLifeUp = 2;
    private int lowSpeed = 5;
    LifeHeart lifeHeart = new LifeHeart(3);



    public GameStructure(Pane playRoot, Carl carl, Random rand, Timeline t) throws FileNotFoundException {
        asteroids.clear();
        lifeUp.clear();

        playRoot.getChildren().add(lifeHeart.getGraphic()); //Adds the hearts to the screen


        //Add asteroids with bounded random x and random y positions
        for(int i = 0; i < numOfAsteroids; i++){
            asteroids.add(new Asteroid(rand.nextInt(10000-1500)+1500,rand.nextInt(720)));
        }

        //Add life-up with bounded random x and random y positions
        for(int i = 0; i < numOfLifeUp; i++){
            lifeUp.add(new LifeUp(rand.nextInt(6500-2500)+2500,rand.nextInt(380-340)+340));
        }

        //Add the asteroids onto the screen
        for(Asteroid a : asteroids){
            playRoot.getChildren().add(a.getHitbox()); //Debug(hitbox outline)
            playRoot.getChildren().add(a.getGraphic());
        }

        //Add the life-up onto the screen
        for(LifeUp l : lifeUp){
            playRoot.getChildren().add(l.getHitbox()); //Debug(hitbox outline)
            playRoot.getChildren().add(l.getGraphic());
        }

        //Add Carl onto the screen
        playRoot.getChildren().add(carl.getHitbox()); //Debug(hitbox outline)
        playRoot.getChildren().add(carl.getGraphic());


        //Handler for the 30ms updates
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                int speed = rand.nextInt(15-lowSpeed)+lowSpeed;


                carl.draw();
                carl.updateHitbox();

                if(carl.getYPos() < 0){
                    carl.setYPos(0);
                }

                if(carl.getYPos()+carl.getHeight() > 720){
                    carl.setYPos(669);
                }

                for(int i = 0; i < asteroids.size(); i++){
                    asteroids.get(i).updateHitbox();
                    asteroids.get(i).draw();

                    asteroids.get(i).move(speed);





                    if(asteroids.get(i).getXPos() < -100){
                        playRoot.getChildren().remove(asteroids.get(i).getGraphic());
                        asteroids.remove(i);
                    }

                }

                //LifeUp stuff
                for(int i = 0; i < lifeUp.size(); i++){
                    lifeUp.get(i).updateHitbox();
                    lifeUp.get(i).draw();
                    lifeUp.get(i).move(speed);



                    if(lifeUp.get(i).getXPos() < -100){
                        playRoot.getChildren().remove(lifeUp.get(i).getGraphic());
                        lifeUp.remove(i);
                    }
                }

                //Life-Up collision
                for(int i = 0; i < lifeUp.size(); i++) {
                    if (carl.collidesWith(lifeUp.get(i))) {
                        lifeHeart.addLives();
                        playRoot.getChildren().remove(lifeUp.get(i).getGraphic());
                        playRoot.getChildren().remove(lifeUp.get(i).getHitbox()); //Debug(hitbox outline)
                        lifeUp.remove(i);
                    }
                }

                //Asteroid collision
                for(int i = 0; i < asteroids.size(); i++){
                    if(carl.collidesWith(asteroids.get(i))){
                        lifeHeart.removeLives();
                        playRoot.getChildren().remove(asteroids.get(i).getGraphic());
                        playRoot.getChildren().remove(asteroids.get(i).getHitbox()); //Debug(hitbox outline)
                        asteroids.remove(i);
                    }
                }

                System.out.println(asteroids.size());

                checkLevelEnd(); //Checks when the level ends

            }
        };



    }

    //Level ends when there is no more asteroids or when lives/hearts equals 0
    public void checkLevelEnd(){
        //System.out.println(lifeHeart.getLives());
        if(asteroids.size() == 0 && lifeUp.size() == 0){
            level++;
            System.exit(0);
        }
        if(lifeHeart.getLives() == 0){
            System.exit(0);
        }
    }

    public int getLevel(){
        return level;
    }

    public EventHandler<ActionEvent> getHandler(){
        return handler;
    }

    public LifeHeart getLifeHeart(){
        return lifeHeart;
    }

    public void reset(){

    }


}
