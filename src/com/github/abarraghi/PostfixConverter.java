package com.github.abarraghi;

import java.util.*;

public class PostfixConverter {
	
	//Declare fields
	private HashMap<Character, Integer> operPrecedence;
	private HashMap<Character, String> operAssociativity;
	private Stack<Character> operStack;
	private String input[];
	private String output = "";
	
	//Constructors
	PostfixConverter(){}
	
	PostfixConverter(String[] input) {
		//Instantiate Data Structures
		operPrecedence = new HashMap<Character, Integer>();
		operAssociativity = new HashMap<Character, String>();
		operStack = new Stack<Character>();
		this.input = input;
	
		//Assign each operator its precedence
		operPrecedence.put('!',5);
		operPrecedence.put('^',4);
		operPrecedence.put('~',3);
		operPrecedence.put('%',3);
		operPrecedence.put('/',3);
		operPrecedence.put('*',3);
		operPrecedence.put('+',2);
		operPrecedence.put('-',2);
		operPrecedence.put('(',0);
		
		//Assign each operator its associativity
		//RL - Right to Left, LR - Left to Right
		operAssociativity.put('!', "RL");
		operAssociativity.put('^', "RL");
		operAssociativity.put('~', "RL");
		operAssociativity.put('%', "LR");
		operAssociativity.put('/', "LR");
		operAssociativity.put('*', "LR");
		operAssociativity.put('+', "LR");
		operAssociativity.put('-', "LR");
		operAssociativity.put('(', "LR");
		
	}
	
	/**
	 * Converts a numeric Infix (typed) formula to its Postfix (operators last) representation
	 * (current working operators: +,-,*,/)
	 * assumes all typed numbers are integers, single digit, and positive
	 * @return input string as postfix exp
	 */
	public String infixToPostfix() {
		
		String currElem = " ";
		char topChar = ' ';
		int currPrec = 0, topPrec = 0;
		
		for(int i = 0; i < input.length; i++) {
			
			currElem = input[i];
			
			//Always append operand to output
			try{
				Double.parseDouble(currElem);
				output+= currElem + ",";
			}
			catch(NumberFormatException e) {
				if(currElem.equals("(")) operStack.push(currElem.charAt(0));
				else if( currElem.equals(")")) {
					
					topChar = operStack.peek();
					
					while(topChar != '(' && !operStack.isEmpty()) {
						output += topChar + ",";
						operStack.pop();
						topChar = operStack.peek();
					}
					
					//Remove the remaining opening parenthesis
					operStack.pop();
					
				}
				
				else { 
					
					if( !operStack.isEmpty() && operAssociativity.get(operStack.peek()).equals("LR")) {
						
						currPrec = operPrecedence.get(currElem.charAt(0));
						topChar = operStack.peek();
						topPrec = operPrecedence.get(topChar);
						
						while( (currPrec <= topPrec ) && !operStack.isEmpty()) {
							output += topChar + ",";
							operStack.pop();
							topChar = operStack.peek();
							topPrec = operPrecedence.get(topChar);
						}
						
					}
					
					operStack.push(currElem.charAt(0));
					
				}
			}
			
		}
		
		//Pop remaining operators to output
		while( !operStack.isEmpty() ) {
			output += operStack.pop() + ",";
		}
		
		
		return output.substring(0,output.length()-1);
	}
	

}
