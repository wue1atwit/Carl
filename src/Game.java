import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


public class Game extends Application {

	static int lives = 3;
	final Font baseFont = Font.font("OCR A Extended", 36);

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/**
		 * Home Screen
		 */
		VBox buttons = new VBox(10);
		HBox buttons2 = new HBox(10);
		StackPane homeRoot = new StackPane();
		Button playButton = new Button("Play");
		Button htpButton = new Button("How to Play");
		Button creditButton = new Button("Credit");

		//Play Button Size
		playButton.setPrefSize(200,100);
		playButton.setFont(baseFont);

		//How to Play Size
		htpButton.setPrefSize(200,50);
		htpButton.setFont(Font.font("OCR A Extended",20));


		//Credit
		creditButton.setPrefSize(200,50);
		creditButton.setFont(Font.font("OCR A Extended",20));


		buttons2.getChildren().addAll(htpButton,creditButton);
		buttons.getChildren().addAll(playButton,buttons2);

		homeRoot.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		buttons2.setAlignment(Pos.CENTER);

		//Background
		Image backgImg = new Image(new FileInputStream("spacebackground.jpg"));
		homeRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		//root.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));

		/**
		 * How to Play Screen
		 */
		StackPane htpRoot = new StackPane();
		Button exitButton = new Button("Exit");
		exitButton.setPrefSize(200,50);
		exitButton.setFont(Font.font("OCR A Extended",20));

		htpRoot.getChildren().addAll(exitButton);
		htpRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		/**
		 * Play Screen
		 */
		Pane playRoot = new Pane();
		playRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		ArrayList<ImageView> red = new ArrayList<>();
		HBox redHearts = new HBox(10);

		Random rand = new Random();
		Carl carl = new Carl();



		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);


		/**
		 * Set Scenes
		 */
		primaryStage.setScene(homeScene);
		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		playButton.setOnAction(e -> {
			playRoot.getChildren().add(carl.getGraphic());
			for(int i = 0; i < 3; i++) {
				try {
					red.add(new ImageView(new Image(new FileInputStream("redHearts.png"))));
				} catch (FileNotFoundException exception) {
				}
			}

			redHearts.setLayoutX(1100);
			redHearts.setLayoutY(0);
			redHearts.getChildren().addAll(red);
			playRoot.getChildren().add(redHearts);


			try {
				//Level 1
				levels(playRoot,carl,rand,70,5);
			} catch (FileNotFoundException exception) {
			}
			primaryStage.setScene(playScene);
		});
		exitButton.setOnAction(e -> primaryStage.setScene(homeScene));



		playRoot.requestFocus();
		playRoot.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.UP) {
				carl.changeY(-10);
			}
			if(e.getCode()==KeyCode.DOWN) {
				carl.changeY(10);
			}
			if(e.getCode() == KeyCode.SPACE){
				addLife(redHearts, red);
				System.out.println(lives);
			}
			if(e.getCode() == KeyCode.ENTER){
				removeLife(redHearts,red);
				System.out.println(lives);
			}
			if(e.getCode() == KeyCode.ESCAPE){
				primaryStage.setScene(homeScene);
				remove(playRoot);
			}
		});
		playRoot.setOnKeyReleased(e -> {

		});

		primaryStage.show();

	}


	public static void levels(Pane playRoot, Carl carl, Random rand, int numOfAsteroids, int lowSpeed) throws FileNotFoundException {
		ArrayList<Asteroid> asteroids = new ArrayList<>();
		for(int i = 0; i < numOfAsteroids; i++){
			asteroids.add(new Asteroid(rand.nextInt(10000-1500)+1500,rand.nextInt(720)));
		}

		for(Asteroid a : asteroids){
			playRoot.getChildren().add(a.getGraphic());
		}

		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int speed = rand.nextInt(15-lowSpeed)+lowSpeed;
				carl.draw();
				for(int i = 0; i < asteroids.size(); i++){
					asteroids.get(i).draw();
					asteroids.get(i).move(speed);
				}


			}
		};

		Timeline t = new Timeline(new KeyFrame(Duration.millis(30),handler));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
	}

	public static void remove(Pane playRoot){
		playRoot.getChildren().clear();

	}

	public static void addLife(HBox redHearts, ArrayList<ImageView> red){
		if(lives == 1 || lives == 2) {
			try {
				red.add(new ImageView(new Image(new FileInputStream("redHearts.png"))));
				redHearts.getChildren().add(new ImageView(new Image(new FileInputStream("redHearts.png"))));
			} catch (FileNotFoundException exception) {
			}
			lives++;
		}
	}

	public static void removeLife(HBox redHearts, ArrayList<ImageView> red){

			if(lives >= 1) {
				redHearts.getChildren().remove(red.get(lives-1));
				lives--;
			}
			if(lives == 0){

				System.exit(0);
			}

	}
}

