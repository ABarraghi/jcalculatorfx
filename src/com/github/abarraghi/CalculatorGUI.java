package com.github.abarraghi;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class CalculatorGUI extends Application {
	
	Button button;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("jCalculatorFX");
		button = new Button("Click Me!");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 384, 720);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


}
