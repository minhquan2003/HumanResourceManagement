package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("QVP HR Management System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		javafx.application.Application.launch(args);
	}
}
