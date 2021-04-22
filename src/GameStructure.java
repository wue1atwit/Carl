import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class GameStructure {

    private Stage primaryStage;
    private Scene gameOverScene;
    private Pane playRoot;
    private Timeline t;
    private Random rand;

    MediaPlayer backgroundSound = new MediaPlayer(new Media(new File("sound/io1.mp3").toURI().toString()));


    private static int level = 1;

    private static ArrayList<Asteroid> asteroids = new ArrayList<>();
    private static ArrayList<LifeUp> lifeUp = new ArrayList<>();
    private static ArrayList<Planet> planets = new ArrayList<>();

    private EventHandler<ActionEvent> handler;

    LifeHeart lifeHeart = new LifeHeart(3);


    private int numOfAsteroids = 70;
    private int numOfLifeUp = 2;
    private int lowSpeed = 5;




    public GameStructure(Stage primaryStage, Scene gameOverScene, Pane playRoot, Carl carl, Random rand) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        this.gameOverScene = gameOverScene;
        this.playRoot = playRoot;
        this.rand = rand;


        backgroundSound.play();

        asteroids.clear();
        lifeUp.clear();
        planets.clear();

        playRoot.getChildren().add(lifeHeart.getGraphic()); //Adds the hearts to the screen

        //Add asteroids with bounded random x and random y positions
        for(int i = 0; i < numOfAsteroids; i++){
            asteroids.add(new Asteroid(rand.nextInt(10000-1500)+1500,rand.nextInt(720)));
        }

        //Add life-up with bounded random x and random y positions
        for(int i = 0; i < numOfLifeUp; i++){
            lifeUp.add(new LifeUp(rand.nextInt(6500-2500)+2500,rand.nextInt(380-340)+340));
        }

        planets.add(new Planet("images/planet1.png",1280,360));
        //planets.add(new Planet("planet2.png",1280,360));
        //planets.add(new Planet("planet3.png",1280,360));

        playRoot.getChildren().add(planets.get(0).getHitbox());
        playRoot.getChildren().add(planets.get(0).getGraphic());


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

                if(level == 1) {

                    int speed = rand.nextInt(15 - lowSpeed) + lowSpeed;


                    //Carl updates
                    carl.draw();
                    carl.updateHitbox();


                    //Screen bounds
                    if (carl.getYPos() < 0) {
                        carl.setYPos(0);
                    }
                    if (carl.getYPos() + carl.getHeight() > 720) {
                        carl.setYPos(669);
                    }

                    //Asteroid updates
                    for (int i = 0; i < asteroids.size(); i++) {
                        asteroids.get(i).updateHitbox();
                        asteroids.get(i).draw();
                        asteroids.get(i).move(speed);

                        //Remove asteroids when it goes off the left side
                        if (asteroids.get(i).getXPos() < -100) {
                            playRoot.getChildren().remove(asteroids.get(i).getGraphic());
                            asteroids.remove(i);
                        }

                    }

                    //LifeUp updates
                    for (int i = 0; i < lifeUp.size(); i++) {
                        lifeUp.get(i).updateHitbox();
                        lifeUp.get(i).draw();
                        lifeUp.get(i).move(speed);

                        //Remove life-up when it goes off the left side
                        if (lifeUp.get(i).getXPos() < -100) {
                            playRoot.getChildren().remove(lifeUp.get(i).getGraphic());
                            lifeUp.remove(i);
                        }
                    }

                    //Planet 1 updates
                    planets.get(0).draw();

                    if (asteroids.size() == 0 && lifeUp.size() == 0) {
                        planets.get(0).updateHitbox();
                        planets.get(0).move(speed);
                    }


                    //Asteroid collision w/ Carl
                    for (int i = 0; i < asteroids.size(); i++) {
                        if (carl.collidesWith(asteroids.get(i))) {
                            lifeHeart.removeLives();
                            playRoot.getChildren().remove(asteroids.get(i).getGraphic());
                            playRoot.getChildren().remove(asteroids.get(i).getHitbox()); //Debug(hitbox outline)
                            asteroids.remove(i);
                        }
                    }

                    //Life-Up collision w/ Carl
                    for (int i = 0; i < lifeUp.size(); i++) {
                        if (carl.collidesWith(lifeUp.get(i))) {
                            lifeHeart.addLives();
                            playRoot.getChildren().remove(lifeUp.get(i).getGraphic());
                            playRoot.getChildren().remove(lifeUp.get(i).getHitbox()); //Debug(hitbox outline)
                            lifeUp.remove(i);
                        }
                    }


                    //Planet collision w/ Carl
                    if (carl.collidesWith(planets.get(0))) {
                        level++;
                        playRoot.getChildren().remove(planets.get(0).getGraphic());
                        playRoot.getChildren().remove(planets.get(0).getHitbox()); //Debug(hitbox outline)
                        planets.remove(planets.get(0));
                        //System.exit(0);
                    } else if (planets.get(0).getXPos() < 0 && !carl.collidesWith(planets.get(0))) {
                        reset();
                    }


                    checkLife(); //Checks when the lives hit 0

                }




            }
        };

        t = new Timeline(new KeyFrame(Duration.millis(30),this.getHandler()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();



    }


    //Level ends when there is no more asteroids or when lives/hearts equals 0
    public void checkLife(){
        if(lifeHeart.getLives() == 0){
            reset();
        }
    }

    public void reset(){
        backgroundSound.stop();
        primaryStage.setScene(gameOverScene);
        t.stop();
        playRoot.getChildren().clear();
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

    public Timeline getTimeline(){
        return t;
    }

    public MediaPlayer getSound(){
        return backgroundSound;
    }

}
