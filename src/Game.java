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
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Game extends Application {


	final Font bigFont = Font.font("OCR A Extended", 48);
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
		//ImageView playButton = new ImageView(new Image(new FileInputStream("images/playButton.png")));

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

		ImageView title = new ImageView(new Image(new FileInputStream("images/Title.png")));


		buttons2.getChildren().addAll(htpButton,creditButton);
		buttons.getChildren().addAll(title,playButton,buttons2,exitHomeButton);

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
		Button exitHTPButton = new Button("Main Menu");
		exitHTPButton.setPrefSize(200,50);
		exitHTPButton.setFont(smallFont);

		ImageView htp1 = new ImageView(new Image(new FileInputStream("images/htp1.png")));
		ImageView htp2 = new ImageView(new Image(new FileInputStream("images/htp2.png")));
		ImageView htp3 = new ImageView(new Image(new FileInputStream("images/htp3.png")));

		Label htpLabel = new Label("HOW TO PLAY");
		htpLabel.setFont(bigFont);
		htpLabel.setTextFill(Color.WHITE);

		VBox htpItems = new VBox(10);
		HBox htpFrames = new HBox(10);
		HBox htpButtons = new HBox(10);


		htpFrames.getChildren().addAll(htp1,htp2,htp3);
		htpButtons.getChildren().add(exitHTPButton);
		htpItems.getChildren().addAll(htpLabel,htpFrames,htpButtons);

		htpItems.setAlignment(Pos.CENTER);
		htpFrames.setAlignment(Pos.CENTER);
		htpButtons.setAlignment(Pos.CENTER);



		htpRoot.getChildren().addAll(htpItems);
		htpRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		/**
		 * Credit Screen
		 */
		StackPane creditRoot = new StackPane();
		creditRoot.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		Button mainMenuButtonCredit = new Button("Main Menu");
		mainMenuButtonCredit.setPrefSize(250,50);
		mainMenuButtonCredit.setFont(smallFont);


		Label credit = new Label("CREDIT:");
		credit.setFont(bigFont);
		credit.setTextFill(Color.WHITE);

		Label creditLabel = new Label("Ethan Wu, Ryan Baggett, Kristina Arabatzis");
		creditLabel.setFont(baseFont);
		creditLabel.setTextFill(Color.WHITE);

		Label carlLabel = new Label("Special thanks to Carl :)");
		carlLabel.setFont(smallFont);
		carlLabel.setTextFill(Color.WHITE);

		VBox creditItems = new VBox(50);
		HBox creditButtons = new HBox(10);

		creditButtons.getChildren().addAll(mainMenuButtonCredit);
		creditItems.getChildren().addAll(credit,creditLabel,carlLabel,creditButtons);

		creditRoot.getChildren().addAll(creditItems);
		creditButtons.setAlignment(Pos.CENTER);
		creditItems.setAlignment(Pos.CENTER);

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
		ImageView gameOverImage = new ImageView(new Image(new FileInputStream("images/gameOverCarl.png")));

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

		/**
		 * Win Screen
		 */
		StackPane winRoot = new StackPane();
		Pane winCarl = new Pane();
		winRoot.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("images/planetScape.jpg")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		ImageView winImage = new ImageView(new Image(new FileInputStream("images/Win.png")));

		Button mainMenuButtonWin = new Button("Main Menu");
		mainMenuButtonWin.setPrefSize(250,50);
		mainMenuButtonWin.setFont(smallFont);

		Button exitButtonWin = new Button("Exit");
		exitButtonWin.setPrefSize(250,50);
		exitButtonWin.setFont(smallFont);

		VBox winItems = new VBox(50);
		HBox winButtons = new HBox(10);

		winButtons.getChildren().addAll(mainMenuButtonWin,exitButtonWin);
		winItems.getChildren().addAll(winImage,winButtons);

		winCarl.getChildren().addAll(winItems);
		Group groupWin = new Group(winCarl);
		winRoot.getChildren().add(groupWin);
		winButtons.setAlignment(Pos.CENTER);
		winItems.setAlignment(Pos.CENTER);

		Scene homeScene = new Scene(homeRoot,1280,720);
		Scene playScene = new Scene(playRoot, 1280,720);
		Scene htpScene = new Scene(htpRoot,1280,720);
		Scene gameOverScene = new Scene(gameOverRoot,1280,720);
		Scene winScene = new Scene(winRoot,1280,720);
		Scene creditScene = new Scene(creditRoot, 1280, 720);

		primaryStage.setScene(homeScene); //Initial scene


		//Button Interactions
		playButton.setOnAction(e -> {
			carl.resetPos(); //Resets Carl's X Position to 360

			try {
				gameStructure = new GameStructure(primaryStage,gameOverScene,winScene,playRoot,carl,rand);

			} catch (FileNotFoundException exception) {
			}

			primaryStage.setScene(playScene);
		});
//		playButton.setOnMouseEntered(e -> {
//			try {
//				playButton.setImage(new Image(new FileInputStream("images/toggledPlayButton.png")));
//			} catch (FileNotFoundException fileNotFoundException) {
//			}
//		});
//		playButton.setOnMouseExited(e -> {
//			try {
//				playButton.setImage(new Image(new FileInputStream("images/playButton.png")));
//			} catch (FileNotFoundException fileNotFoundException) {
//			}
//		});

		htpButton.setOnAction(e -> primaryStage.setScene(htpScene));
		creditButton.setOnAction(e -> primaryStage.setScene(creditScene));
		exitHomeButton.setOnAction(event -> primaryStage.close());

		//Credit Buttons
		mainMenuButtonCredit.setOnAction(event -> {
			primaryStage.setScene(homeScene);
		});


		//Game Over Buttons
		exitGOButton.setOnAction(e -> primaryStage.close());
		mainMenuButton.setOnAction(event -> {
			primaryStage.setScene(homeScene);
			remove(playRoot);

		});

		//Win Buttons
		exitButtonWin.setOnAction(e -> primaryStage.close());
		mainMenuButtonWin.setOnAction(event -> {
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
				gameStructure.setLevel(1);
				gameStructure.getRoundOneTrack().stop();
				gameStructure.getRoundTwoTrack().stop();
				gameStructure.getRoundThreeTrack().stop();
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


		primaryStage.setTitle("The Adventures of Carl");
		primaryStage.getIcons().add(new Image(new FileInputStream("images/cIcon.png")));
		primaryStage.setResizable(false);
		primaryStage.show();

	}


	public static void remove(Pane playRoot){
		playRoot.getChildren().clear();

	}

}

