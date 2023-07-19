package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	@FXML
	public void onMenuItemSDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml"); //carrega a tela instanciada
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	//synchronized: evita que a tela pare de carregar
	//absoluteName pois vai ser passado o nome completo
	private synchronized void loadView (String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); //referencia do vbox que esta na janela principal
			
			//carrega os filhos estaciados no About.fxml e limpa qualquer coisa que possa estar em tela
			//"substitui os filhos da janela principal, mas mantem o menu"
			Node mainMenu = mainVBox.getChildren().get(0);//posição zero por ser o primeiro filho
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e){
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	private synchronized void loadView2 (String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			//ingeta dependencia e instancia na tela
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		}
		catch (IOException e){
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
