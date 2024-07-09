package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	private static final String UIPath = "UI.fxml";

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(UIPath));
		stage.setTitle("Encrptor/Decryptor");
		stage.setScene(new Scene(loader.load()));
		stage.centerOnScreen();
		stage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
