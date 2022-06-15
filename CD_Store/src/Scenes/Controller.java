package Scenes;

import java.io.FileInputStream;

import Backend_Part.DataBase;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller extends Application {

	static Stage Stage1;

	public void start(Stage Stage) throws Exception {
		Stage1 = Stage;
		Image icon = new Image(new FileInputStream("Images/icon1.png"));
		Stage1.getIcons().add(icon);
		Stage1.setTitle("CD Store");
		Login_Scene.loginscene(Stage);
		Stage.show();
		Stage1.setOnCloseRequest(e -> DataBase.save());
	}

}
