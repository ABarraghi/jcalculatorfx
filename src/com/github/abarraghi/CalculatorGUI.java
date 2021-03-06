package com.github.abarraghi;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CalculatorGUI extends Application {
	
	Button[][] mainButtons;
	String[][] mainButtonText = {
			{"0","1","(",")"},
			{"2","3","+","-"},
			{"4","5","*","/"},
			{"6","7","!","%"},
			{"8","9","^","~"},
			{"<","C",".","="}
			
	}; 
	String formula = "";
	PostfixConverter pf;
	Calculator calc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("jCalculatorFX");
		Rectangle base = new Rectangle();
		TextField formulaBar = new TextField();
		mainButtons = new Button[6][4];
		
		base.setX(0);
		base.setY(0);
		base.setWidth(384);
		base.setHeight(720);
	    base.setFill(Color.MAROON);
	    
	    formulaBar.setLayoutX(30.72);
	    formulaBar.setLayoutY(44.16);
	    formulaBar.setMinWidth(324.48);
	    formulaBar.setMinHeight(80.64);
	    formulaBar.setEditable(false);
	    
	    GridPane buttonPane = new GridPane();
	    buttonPane.setLayoutX(30.72);
	    buttonPane.setLayoutY(195.84);
	    buttonPane.setMinSize(324.48, 405.12);
	    
	    for(int i = 0; i < 6; i++) {
	    	for(int j = 0; j < 4; j++) {
	    		mainButtons[i][j] = new Button();
	    		final String BUTTON_TEXT = mainButtonText[i][j];
	    		System.out.println(BUTTON_TEXT);
	    		
	    		buttonPane.add(mainButtons[i][j], j, i);
	    		buttonPane.setGridLinesVisible(false);
    			mainButtons[i][j].setText(BUTTON_TEXT);
    			mainButtons[i][j].setMinWidth(80.64);
    			mainButtons[i][j].setMinHeight(80.64);
    			
	    		
	    		switch(BUTTON_TEXT) {
	    		
	    			case "=":
	    				mainButtons[i][j].setOnAction(e -> {
			    			pf = new PostfixConverter(tokenizeInput(formula).split(","));
			    			String postfixFormula = pf.infixToPostfix();
			    			calc = new Calculator(postfixFormula.split(","));
			    			formula = Float.toString(calc.calculate());
			    			formulaBar.setText(formula);
			    		});
	    				break;
	    			case "<":
	    				mainButtons[i][j].setOnAction(e -> {
	    					if(formula.length()>0)
	    						formula = formula.substring(0,formula.length()-1);
	    					formulaBar.setText(formula);
	    				});
	    				break;
	    			case "C":
	    				mainButtons[i][j].setOnAction(e -> {
		    				formula = "";
				    		formulaBar.setText("");
				    	});
	    				break;
	    			default:
	    				mainButtons[i][j].setOnAction(e -> {
			    			formula += BUTTON_TEXT;
			    			formulaBar.setText(formula);
			    		});
	    				break;
	    			
	    		}
	    			
	    	}
	    }
		
		Group layout = new Group();
		layout.getChildren().addAll(base,formulaBar,buttonPane);
		
		Scene scene = new Scene(layout, 384, 720);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	/**
	 * Represent a given formula in tokens, separating those tokens by a comma
	 * @param input the formula to be separated by commas
	 * @return tokenized representation of the formula
	 */
	public static String tokenizeInput(String input) {
		char currChar = ' ';
		String tokenized = "";
		boolean digitPrec = false;
		for(int i = 0;i<input.length();i++) {
			currChar = input.charAt(i);
			if(Character.isDigit(currChar)||currChar=='.') {
				tokenized += currChar;
				digitPrec = true;
				continue;
			}
			else if (currChar == ' ') continue;
			else {
				if(digitPrec) { 
					digitPrec = false;
					if(i==(input.length()-1)) tokenized += "," + currChar;
					else
					tokenized += "," + currChar + ",";
				}
				else
					if(i==(input.length()-1)) tokenized += currChar;
					else
					tokenized += currChar + ",";		
			}
			
		}
		
		return tokenized;
	}
	
}
