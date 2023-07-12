package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			//ajustar o menuBar com a janela
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);
			
			Scene mainScene = new Scene(scrollPane); //cena principal
			primaryStage.setScene(mainScene); //palco da cena
			primaryStage.setTitle("Sample JavaFX application"); //titulo do palco
			primaryStage.show(); //mostra
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
