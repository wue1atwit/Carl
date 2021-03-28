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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;


public class Game extends Application {

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
		Random rand = new Random();
		Carl carl = new Carl();
		ArrayList<Asteroid> asteroids = new ArrayList<>();
		for(int i = 0; i < 70; i++){
			asteroids.add(new Asteroid(rand.nextInt(10000-1500)+1500,rand.nextInt(720),50)); //Size needs to go eventually
		}
		playRoot.getChildren().add(carl.getGraphic());
		for(Asteroid a : asteroids){
			playRoot.getChildren().add(a.getGraphic());
		}

		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int speed = rand.nextInt(15-5)+5;
				carl.draw();
				for(int i = 0; i < asteroids.size(); i++){
					asteroids.get(i).draw();
					asteroids.get(i).move(speed);
				}
//				asteroids.get(0).move(speed);
//				asteroids.get(1).move(speed);
//				asteroids.get(2).move(s);

//				asteroids.get(0).draw();
//				asteroids.get(1).draw();
//				asteroids.get(2).draw();
//				asteroids.get(0).move(1);
//				asteroids.get(1).move(5);
//				asteroids.get(2).move(10);

			}
		};

		Timeline t = new Timeline(new KeyFrame(Duration.millis(30),handler));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();





		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);


		/**
		 * Set Scenes
		 */
		primaryStage.setScene(homeScene);
		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		playButton.setOnAction(e -> primaryStage.setScene(playScene));
		exitButton.setOnAction(e -> primaryStage.setScene(homeScene));



		playRoot.requestFocus();
		playRoot.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.UP) {
				carl.changeY(-10);
			}
			if(e.getCode()==KeyCode.DOWN) {
				carl.changeY(10);
			}
			if(e.getCode() == KeyCode.ESCAPE){
				primaryStage.setScene(homeScene);
			}
		});
		playRoot.setOnKeyReleased(e -> {

		});

		primaryStage.show();

	}
}
