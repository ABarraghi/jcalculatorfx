package com.github.abarraghi;

import java.util.*;

public class Calculator {
	
	//Each calculation is in the form:
	//operandOne operator operandTwo = result
	private float operandOne;
	private float operandTwo;
	private char operator;
	private float result = 0;
	private String[] input;
	private Stack<Float> calcStack;
	private HashMap<Character, String> operatorType;
	
	//Constructors
	public Calculator() {}
	
	public Calculator(String[] input){
		this.input = input;
		calcStack = new Stack<Float>();
		operatorType = new HashMap<Character,String>();
		
		operatorType.put('!', "unary");
		operatorType.put('~', "unary");
		operatorType.put('^', "binary");
		operatorType.put('%', "binary");
		operatorType.put('*', "binary");
		operatorType.put('/', "binary");
		operatorType.put('+', "binary");
		operatorType.put('-', "binary");
	}
	
	
	/**
	 * Calculates the result of a given Postfix expression.
	 * Supported operators: +,-,*,/. Supported operands: any 1 digit.
	 * @return Postfix formula result
	 */
	public float calculate() {
		
		for(int i = 0; i < input.length; i++ ) {
			
			String currElem = input[i];
			
			try {
				calcStack.push((float) Double.parseDouble(currElem));
			}
			catch(NumberFormatException e) {
				
				try {
					
					operator = currElem.charAt(0);
					if(operatorType.get(operator).equals("binary"))
						operandTwo = calcStack.pop();
					
					operandOne = calcStack.pop();
					
					calcStack.push(performOperation());
					
				} catch(Exception ex) { ex.printStackTrace(); }
			}	
			
		}
		
		return calcStack.pop();
		
	}
	
	/**
	 * Calculate result of formula, according to operator used
	 * @return the value computed by performing the operation on the operand(s)
	 * @throws Exception where a non-integer/negative is factorial input, or invalid operator is operator input  
	 */
	public float performOperation() throws Exception {
		switch(operator) {
			case '+':
				result  = operandOne + operandTwo;
				break;
			case '-':
				result = operandOne - operandTwo;
				break;
			case '*':
				result = operandOne * operandTwo;
				break;
			case '/':
				result = operandOne / operandTwo;
				break;
			case '^':
				result = (float) Math.pow(operandOne, operandTwo);
				break;
			case '%':
				result = operandOne % operandTwo;
				break;
			case '!':
				if(operandOne < 0) 
					throw new Exception("Must be a non-negative number!");
				else {
					if((operandOne%2!=1)&&(operandOne%2!=0))
						throw new Exception("Must be an integer!");
					else {
						result = 1;
						while(operandOne>1) {
							result *= operandOne--;
						}
					}
				}
				break;
			case '~':
				result = -1 * operandOne;
				break;
			default:
				throw new Exception("Invalid operator!");
		}
		return result;
	}

}
