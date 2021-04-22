import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

		//Play Button
		Button playButton = new Button("Play");
		playButton.setPrefSize(200,100);
		playButton.setFont(baseFont);

		//How to Play Button
		Button htpButton = new Button("How to Play");
		htpButton.setPrefSize(200,50);
		htpButton.setFont(smallFont);


		//Credit Button
		Button creditButton = new Button("Credit");
		creditButton.setPrefSize(200,50);
		creditButton.setFont(smallFont);

		//Exit Button
		Button exitHomeButton = new Button("Exit");
		exitHomeButton.setPrefSize(200,50);
		exitHomeButton.setFont(smallFont);


		buttons2.getChildren().addAll(htpButton,creditButton);
		buttons.getChildren().addAll(playButton,buttons2,exitHomeButton);

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
		Pane gameOverCarl = new Pane();
		gameOverRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		Image img = new Image(new FileInputStream("images/gameOverCarl.png"));
		ImageView gameOverImage = new ImageView(img);

		Button mainMenuButton = new Button("Main Menu");
		mainMenuButton.setPrefSize(250,50);
		mainMenuButton.setFont(smallFont);

		Button exitGOButton = new Button("Exit");
		exitGOButton.setPrefSize(250,50);
		exitGOButton.setFont(smallFont);


		VBox goItems = new VBox(50);
		HBox goButtons = new HBox(10);


		goButtons.getChildren().addAll(mainMenuButton,exitGOButton);
		goItems.getChildren().addAll(gameOverImage,goButtons);

		gameOverCarl.getChildren().addAll(goItems);
		Group group = new Group(gameOverCarl);
		gameOverRoot.getChildren().add(group);
		goButtons.setAlignment(Pos.CENTER);
		goItems.setAlignment(Pos.CENTER);



		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);
		Scene gameOverScene = new Scene(gameOverRoot,1280,720);


		primaryStage.setScene(homeScene);


		//Button Interactions
		playButton.setOnAction(e -> {
			carl.resetPos(); //Resets Carl's X Position to 360

			try {
				gameStructure = new GameStructure(primaryStage,gameOverScene,playRoot,carl,rand);
//				if(gameStructure.getLevel() == 2){
//					gameStructure = new GameStructure(primaryStage,gameOverScene,playRoot,carl,rand,5,2,10);
//				}
			} catch (FileNotFoundException exception) {
			}

			primaryStage.setScene(playScene);
		});
		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		creditButton.setOnAction(e -> primaryStage.setScene(gameOverScene));
		exitHomeButton.setOnAction(event -> primaryStage.close());

		//Game Over Buttons
		exitGOButton.setOnAction(e -> primaryStage.close());
		mainMenuButton.setOnAction(event -> {
			primaryStage.setScene(homeScene);
			remove(playRoot);

		});



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
			if(e.getCode() == KeyCode.DELETE){ //Debug(keys)
				gameStructure.getLifeHeart().removeLives();
			}
			if(e.getCode() == KeyCode.INSERT){ //Debug(keys)
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

