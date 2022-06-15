package Backend_Part;

import Scenes.Controller;
import javafx.application.Application;

public class Main{

	public static void main(String[] args) {
		DataBase.putDataInLists();
		Application.launch(Controller.class,args);
	}
}
