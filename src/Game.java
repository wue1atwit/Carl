import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class Game extends Application {

	final Font baseFont = Font.font("OCR A Extended", 36);

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox buttons = new VBox(10);
		HBox buttons2 = new HBox(10);
		StackPane root = new StackPane();
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

		root.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		buttons2.setAlignment(Pos.CENTER);

		//Background
		Image backgImg = new Image(new FileInputStream("spacebackground.jpg"));
		root.setBackground(new Background(new BackgroundImage(backgImg,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		//root.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene = new Scene(root,1280,720);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
