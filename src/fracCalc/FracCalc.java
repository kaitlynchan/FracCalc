package fracCalc;
import java.util.*;

public class FracCalc {

	public static void main(String[] args) 
    {   // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner (System.in);
    	System.out.println("Enter in a fraction string");
    	String inputString = userInput.nextLine();
    	
    	//runs until user enters "quit"
       	while (!inputString.equals("quit")){
       		//error handling: does not allow a zero in the denominator
       		if (inputString.indexOf("/0") >0){
       			System.out.println("ERROR: Dividing by zero violates a math law!");
       		}
       		//error handling: does not all invalid format input of ++,--,**, or //
       		else if (inputString.indexOf("++") >0 || inputString.indexOf("--") >0 ||inputString.indexOf("**") >0 || inputString.indexOf("//") >0){
       			System.out.println("ERROR: Input is an invalid format!");
       		}
       		else{
       			System.out.println(produceAnswer(inputString));
       		}  
       		System.out.println("Enter in another fraction string");
	        inputString = userInput.nextLine();        	
        }
    }	    
	
	public static String produceAnswer(String input)
    {	// This method takes in any length of fraction input and calculates the answer two operands at a time from left to right	
    	
		//takes away spaces and places the input string into an array
    	String [] components = input.split(" ");
    	String operand = "";
    	String operator = "";
    	String operand2 = "";
    	int i = 0;
    	String finalAnswer = components[0];
    	//the loop separates the string to calculate two numbers at a time, and replaces the first operand each time with the previous answer
    	while (i <= components.length-3){
        	operand = finalAnswer;
        	operator = components[i+1];
        	operand2 = components[i+2];
        	finalAnswer = calculateTwo(operand2, operand, operator);
        	i += 2;
    	}
    	//returns the final answer to main method
    	return finalAnswer;
    	  
    }
    
    public static String calculateTwo (String operand2, String operand, String operator){
    	//this method calculates the answer to two operands
    	
    	//call the parse function to separate operand1
    	int [] parsedOne = FracCalc.parse(operand);
    	int numeratorOne =  parsedOne [0];
    	int denominatorOne = parsedOne [1];
    	int wholeNumberOne = parsedOne [2];
    	
    	//call the parse function to separate operand2
    	int [] parsedTwo = FracCalc.parse(operand2);
    	int numeratorTwo =  parsedTwo [0];
    	int denominatorTwo = parsedTwo [1];
    	int wholeNumberTwo = parsedTwo [2];
    	
    	//Call calculate function on the two operands' parsed components
    	String calculateAnswer = FracCalc.Calculate(numeratorOne, denominatorOne, wholeNumberOne, numeratorTwo, denominatorTwo, wholeNumberTwo, operator);
    	
    	//returns the solution
        return calculateAnswer;
    }
    
    public static int[] parse(String operand){
    //this method separates each operand into the whole number, numerator, and denominator       
    	int numerator = 0;
    	int denominator = 1;
    	int wholeNumber = 0;
    	
    	if (!(operand.indexOf("/")>0)){
    		//if operand is an integer
    		wholeNumber = Integer.parseInt(operand);
    		}
    	else if (!(operand.indexOf("_")>0) && (operand.indexOf("/")>0)){
    		//if operand is a fraction
    		numerator = Integer.parseInt(operand.substring(0, operand.indexOf("/")));
    		denominator = Integer.parseInt(operand.substring(operand.indexOf("/")+1));
    		}
    	else {
    		//if operand is a mixed number	
    		numerator = Integer.parseInt(operand.substring(operand.indexOf("_")+1, operand.indexOf("/")));
    		denominator = Integer.parseInt(operand.substring(operand.indexOf("/")+1));
    		wholeNumber = Integer.parseInt(operand.substring(0,operand.indexOf("_")));
    		}  
    	
    	//method returns the components as elements of an array
    	int[] components = new int[3];
    	components [0] = numerator;
    	components [1] = denominator;
    	components [2] = wholeNumber;
    	return components;
    }
    
    public static String Calculate(int numeratorOne, int denominatorOne, int wholeNumberOne, int numeratorTwo, int denominatorTwo, int wholeNumberTwo, String operator){
    	//this method takes in parsed components and performs calculations
    	String calculateAnswer = " ";
    	int impropNumeratorOne;
    	int impropNumeratorTwo;
    	
    	//Changes operands 1 and 2 to improper fractions	
    	if (wholeNumberOne < 0){
    		wholeNumberOne *= -1;
    		impropNumeratorOne = -1 *(wholeNumberOne*denominatorOne+numeratorOne);
    	}
    	else {
    		impropNumeratorOne = wholeNumberOne*denominatorOne+numeratorOne;
    	}
    	if (wholeNumberTwo < 0){
    		wholeNumberTwo *= -1;
    		impropNumeratorTwo = -1 *(wholeNumberTwo*denominatorTwo+numeratorTwo);
    	}
    	else{
    		impropNumeratorTwo = wholeNumberTwo*denominatorTwo+numeratorTwo;
    	}
    	//preparing variables to store results of operations to send into the reduce method
    	int answerNumerator;
    	int commonDenominator = denominatorOne * denominatorTwo;
    	int quotientDenominator = denominatorOne * impropNumeratorTwo;
    	
    	//calls method depending on operator
    	if (operator.equals("+")){  		
    		answerNumerator = add(impropNumeratorOne,denominatorOne, impropNumeratorTwo,denominatorTwo);
    		calculateAnswer = reduce(answerNumerator,commonDenominator);
    	}
    	else if (operator.equals("-")){
    		answerNumerator = subtract(impropNumeratorOne,denominatorOne, impropNumeratorTwo,denominatorTwo);
    		calculateAnswer = reduce(answerNumerator,commonDenominator);
    	}
    	else if (operator.equals("*")){
    		answerNumerator = multiply(impropNumeratorOne,denominatorOne, impropNumeratorTwo,denominatorTwo);
    		calculateAnswer = reduce(answerNumerator,commonDenominator);
    	}
    	else {
    		answerNumerator = divide(impropNumeratorOne,denominatorOne, impropNumeratorTwo,denominatorTwo);
    		calculateAnswer = reduce(answerNumerator,quotientDenominator);
    	}   

		return calculateAnswer;
    }
    
    public static String reduce (int answerNumerator, int answerDenominator){
    	//reduces the fraction into proper form by calculating the greatest common factor (ignores the signs)
    	int a = abs(answerNumerator);
    	int b = abs(answerDenominator);
    	int gcf = 1;
    	while (b > 0){
    		gcf = b;
    		b = a % b;
    		a = gcf;
    	}
    	//reduces by the greatest common factor
    	answerNumerator /= gcf;
    	answerDenominator /= gcf;

    	if (answerNumerator % answerDenominator == 0){
           	//formats return answer for integer
    		return answerNumerator/answerDenominator + "";
    	}
    	else if (answerNumerator / answerDenominator != 0 ){
    		//formats return answer for mixed number
    		return answerNumerator/answerDenominator + "_" + abs(answerNumerator) % abs(answerDenominator) + "/" + abs(answerDenominator);
    	}
    	else {
			//formats answer for simple fraction
    		if (answerDenominator<0) {
    			answerNumerator *= -1;
    		}
    		return answerNumerator + "/" + abs(answerDenominator);
    	}
    }
    
	public static int abs (int value){
		//a simple absolute value method
		int answer = value;
		if (value < 0){
			answer = -value;
		}
		return answer;
	}
	//self-explanatory operation methods
    public static int add(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){   	
    	int sumNumerator = (impropNumeratorOne*impropDenominatorTwo)+ (impropNumeratorTwo*impropDenominatorOne);
    	return sumNumerator;
    }
    
    public static int subtract(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){   	
    	int sumNumerator = (impropNumeratorOne*impropDenominatorTwo)- (impropNumeratorTwo*impropDenominatorOne);
    	return sumNumerator;
    }
    
    public static int multiply(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){
    	int productNumerator = impropNumeratorOne * impropNumeratorTwo;
    	return productNumerator;
    }
    
    public static int divide(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){ 	
    	int quotientNumerator = impropNumeratorOne * impropDenominatorTwo;
    	return quotientNumerator;
    }
}