import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Game extends Application {


	final Font baseFont = Font.font("OCR A Extended", 36);
	final Font smallFont = Font.font("OCR A Extended",20);

	GameStructure gameStructure;
	Random rand = new Random();


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
		Image backgImg = new Image(new FileInputStream("images/spacebackground.jpg"));
		homeRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		/**
		 * How to Play Screen
		 */
		StackPane htpRoot = new StackPane();
		Button exitHTPButton = new Button("Exit");
		exitHTPButton.setPrefSize(200,50);
		exitHTPButton.setFont(smallFont);

		htpRoot.getChildren().addAll(exitHTPButton);
		htpRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		/**
		 * Play Screen
		 */
		Pane playRoot = new Pane();
		playRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		Carl carl = new Carl();

		/**
		 * Game Over Screen
		 */
		StackPane gameOverRoot = new StackPane();
		gameOverRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		ImageView gameOverImage = new ImageView(new Image(new FileInputStream("images/gameOverCarl.png")));

		Button playAgainButton = new Button("Play Again");
		playAgainButton.setPrefSize(200,50);
		playAgainButton.setFont(smallFont);

		Button exitGOButton = new Button("Exit");
		exitGOButton.setPrefSize(200,50);
		exitGOButton.setFont(smallFont);

		VBox goItems = new VBox(10);
		HBox goButtons = new HBox(10);

		goButtons.getChildren().addAll(playAgainButton,exitGOButton);
		goItems.getChildren().addAll(gameOverImage,goButtons);


		gameOverRoot.getChildren().addAll(goItems);
		goButtons.setAlignment(Pos.CENTER);
		goItems.setAlignment(Pos.CENTER);




		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);
		Scene gameOverScene = new Scene(gameOverRoot,1280,720);





		//Button Interactions
		primaryStage.setScene(homeScene);
		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		playButton.setOnAction(e -> {
			carl.resetPos(); //Resets Carl's X Position to 360

			try {
				gameStructure = new GameStructure(primaryStage,homeScene,playRoot,carl,rand);
			} catch (FileNotFoundException exception) {
			}

			primaryStage.setScene(playScene);
		});

		creditButton.setOnAction(e -> primaryStage.setScene(gameOverScene));



		exitHTPButton.setOnAction(e -> primaryStage.setScene(homeScene));


		//Key Interactions
		playRoot.requestFocus();
		playRoot.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.UP || e.getCode()==KeyCode.W) {
				carl.changeY(-15);
			}
			if(e.getCode()==KeyCode.DOWN || e.getCode()==KeyCode.S) {
				carl.changeY(15);
			}
			if(e.getCode() == KeyCode.ESCAPE){
				gameStructure.getSound().stop();
				gameStructure.getTimeline().stop();
				primaryStage.setScene(homeScene);
				remove(playRoot);
			}
			if(e.getCode() == KeyCode.DELETE){
				gameStructure.getLifeHeart().removeLives();
			}
			if(e.getCode() == KeyCode.INSERT){
				gameStructure.getLifeHeart().addLives();
			}
		});


		primaryStage.getIcons().add(new Image(new FileInputStream("images/cIcon.png")));
		primaryStage.setResizable(false);
		primaryStage.show();

	}


	public static void remove(Pane playRoot){
		playRoot.getChildren().clear();

	}

}

