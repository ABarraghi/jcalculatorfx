package com.github.abarraghi;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CalculatorGUI extends Application {
	
	Button[][] mainButtons;
	String[][] mainButtonText = {
			{"0","1","2","3"},
			{"4","5","6","7"},
			{"8","9","(",")"},
			{"+","-","*","/"},
			{"C",".","="}
			
	}; 
	String formula = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("jCalculatorFX");
		Rectangle base = new Rectangle();
		TextField formulaBar = new TextField();
		mainButtons = new Button[5][4];
		
		base.setX(0);
		base.setY(0);
		base.setWidth(384);
		base.setHeight(720);
		base.setArcWidth(30.0); 
	    base.setArcHeight(20.0); 
	    base.setFill(Color.TRANSPARENT);
	    
	    formulaBar.setLayoutX(30.72);
	    formulaBar.setLayoutY(44.16);
	    formulaBar.setMinWidth(314.88);
	    formulaBar.setMinHeight(80.64);
	    formulaBar.setEditable(false);
	    
	    GridPane buttonPane = new GridPane();
	    buttonPane.setLayoutX(30.72);
	    buttonPane.setLayoutY(195.84);
	    buttonPane.setMinSize(324.48, 405.12);
	    buttonPane.setGridLinesVisible(true);
	    
	    for(int i = 0; i < 5; i++) {
	    	for(int j = 0; j < 4; j++) {
	    		mainButtons[i][j] = new Button();
	    		final String BUTTON_TEXT = mainButtonText[i][j];
	    		System.out.println(BUTTON_TEXT);
	    		
	    		if(BUTTON_TEXT.equals("=")) {
	    			GridPane.setColumnSpan(mainButtons[i][j], 2);
	    			buttonPane.add(mainButtons[i][j], j, i);
	    			
	    			mainButtons[i][j].setText(BUTTON_TEXT);
	    		    
		    		mainButtons[i][j].setOnAction(e -> {
		    			formula += BUTTON_TEXT;
		    			formulaBar.setText(formula);
		    		});
	    			break;
	    		}
	    		
	    		else {
	    			buttonPane.add(mainButtons[i][j], j, i);
	    			
	    			mainButtons[i][j].setText(BUTTON_TEXT);
	    		    
		    		mainButtons[i][j].setOnAction(e -> {
		    			formula += BUTTON_TEXT;
		    			formulaBar.setText(formula);
		    		});
	    		}
	    			
	    	}
	    }
		
		Group layout = new Group();
		layout.getChildren().addAll(base,formulaBar,buttonPane);
		
		Scene scene = new Scene(layout, 384, 720);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


}
