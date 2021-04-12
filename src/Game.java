import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Game extends Application {


	final Font baseFont = Font.font("OCR A Extended", 36);
	final Font smallFont = Font.font("OCR A Extended",20);

	Timeline t;


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
		htpButton.setFont(smallFont);


		//Credit Size
		creditButton.setPrefSize(200,50);
		creditButton.setFont(smallFont);


		buttons2.getChildren().addAll(htpButton,creditButton);
		buttons.getChildren().addAll(playButton,buttons2);

		homeRoot.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		buttons2.setAlignment(Pos.CENTER);

		//Background
		Image backgImg = new Image(new FileInputStream("spacebackground.jpg"));
		homeRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

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
		GameStructure gameStructure = new GameStructure(playRoot,carl,rand,t);


		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);


		/**
		 * Set Scenes
		 */
		primaryStage.setScene(homeScene);
		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		playButton.setOnAction(e -> {
			carl.resetPos();
			playRoot.getChildren().add(carl.getGraphic());

			t = new Timeline(new KeyFrame(Duration.millis(30),gameStructure.getHandler()));
			t.setCycleCount(Timeline.INDEFINITE);
			t.play();


			primaryStage.setScene(playScene);
		});
		exitButton.setOnAction(e -> primaryStage.setScene(homeScene));



		playRoot.requestFocus();
		playRoot.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.UP || e.getCode()==KeyCode.W) {
				carl.changeY(-10);
			}
			if(e.getCode()==KeyCode.DOWN || e.getCode()==KeyCode.S) {
				carl.changeY(10);
			}
			if(e.getCode() == KeyCode.ESCAPE){
				t.stop();
				primaryStage.setScene(homeScene);
				remove(playRoot);
			}
			if(e.getCode() == KeyCode.SPACE){
				gameStructure.getLifeHeart().removeLives();
			}
		});

		primaryStage.setResizable(false);
		primaryStage.show();

	}


	public static void remove(Pane playRoot){
		playRoot.getChildren().clear();

	}

}

