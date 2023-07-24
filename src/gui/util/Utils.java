package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	//acessa o stage onde o controler que recebeu o evento esta
	public static Stage currentStage(ActionEvent event) {
		return (Stage)((Node) event.getSource()).getScene().getWindow();
	}
}
