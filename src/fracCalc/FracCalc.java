package fracCalc;
import java.util.*;

public class FracCalc {

	public static void main(String[] args) 
    {   // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner (System.in);
    	System.out.println("Enter in a fraction string");
    	String inputString = userInput.nextLine();
    	
       	while (!inputString.equals("quit")){
       		//runs until user enters "quit"
	    	System.out.println(produceAnswer(inputString));
	    	System.out.println("Enter in a fraction string");
	        inputString = userInput.nextLine();        	
        }
    }	    
	
    public static String produceAnswer(String input)
    {	// TODO: Implement this function to produce the solution to the input 	
    	String operand2 = "";
    	String operand = "";
    	String operator = "";
    		
    	//separates the input string into the three components (identifies the operator)
    	if (input.indexOf(" +") > 0){
	    	operand =  input.substring(0, input.indexOf(" +"));
	    	operator = ("+");
	    	operand2 = input.substring(input.indexOf("+ ")+2);
    		}
    	
    	else if (input.indexOf(" *") > 0){
        	operand =  input.substring(0, input.indexOf(" *"));
        	System.out.println(operand);
        	operator = ("*");
        	operand2 = input.substring(input.indexOf("* ")+2);
        	System.out.println(operand2);
        	}
    	else if (input.indexOf(" - ") > 0){
        	operand =  input.substring(0, input.indexOf(" - "));
        	operator = ("-");
        	operand2 = input.substring(input.indexOf(" - ")+3);
        	//System.out.println(operand + " " + operand2);
        	}
    	else {
        	operand =  input.substring(0, input.indexOf(" /"));
        	operator = ("/");
        	operand2 = input.substring(input.indexOf("/ ")+2);
        	}
    	
    	//calls the parse function to separate operand1
    	int [] parsedOne = FracCalc.parse(operand);
    	int numeratorOne =  parsedOne [0];
    	int denominatorOne = parsedOne [1];
    	int wholeNumberOne = parsedOne [2];
    	//System.out.println(numeratorOne);
    	//System.out.println(denominatorOne);
    	//System.out.println(wholeNumberOne);
    	
    	//calls the parse function to separate operand2
    	int [] parsedTwo = FracCalc.parse(operand2);
    	int numeratorTwo =  parsedTwo [0];
    	int denominatorTwo = parsedTwo [1];
    	int wholeNumberTwo = parsedTwo [2];
    	//System.out.println(numeratorTwo);
    	//System.out.println(denominatorTwo);
    	//System.out.println(wholeNumberTwo);
    	
    	//Call calculate function on the two operands
    	String calculateAnswer = FracCalc.Calculate(numeratorOne, denominatorOne, wholeNumberOne, numeratorTwo, denominatorTwo, wholeNumberTwo, operator);
    	
    	//returns parsed components of operand2
        return calculateAnswer;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static int[] parse(String operand){
    //this method separates each operand into the whole number, numerator, and denominator       
    	int numerator;
    	int denominator;
    	int wholeNumber;
    	
    	if (!(operand.indexOf("/")>0)){
    		//if operand is an integer
    			wholeNumber = Integer.parseInt(operand);
    			numerator = 0;
    			denominator = 1;
    		}
    		else if (!(operand.indexOf("_")>0) && (operand.indexOf("/")>0)){
    		//if operand is a fraction
    			numerator = Integer.parseInt(operand.substring(0, operand.indexOf("/")));
    			denominator = Integer.parseInt(operand.substring(operand.indexOf("/")+1));
    			wholeNumber = 0;
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
    	//takes in inputs and performs calculations
    	String calculateAnswer = " ";
    	
    	//Changes operands 1 and 2 to improper fractions
    	int impropNumeratorOne;
    	if (wholeNumberOne < 0){
    		wholeNumberOne *= -1;
    		impropNumeratorOne = -1 *(wholeNumberOne*denominatorOne+numeratorOne);
    	}
    	else {
    		impropNumeratorOne = wholeNumberOne*denominatorOne+numeratorOne;
    	}
    	//System.out.println(impropNumeratorOne);
    	
    	int impropDenominatorOne = denominatorOne;
    	//System.out.println(impropDenominatorOne);
    	
    	int impropNumeratorTwo;
    	if (wholeNumberTwo < 0){
    		wholeNumberTwo *= -1;
    		impropNumeratorTwo = -1 *(wholeNumberTwo*denominatorTwo+numeratorTwo);
    	}
    	else{
    		impropNumeratorTwo = wholeNumberTwo*denominatorTwo+numeratorTwo;
    	}
    	//System.out.println(impropNumeratorTwo);
    	int impropDenominatorTwo = denominatorTwo;
    	//System.out.println(impropDenominatorTwo);
    	
    	//calls method depending on operator
    	if (operator.equals("+")){
    		calculateAnswer = FracCalc.add(impropNumeratorOne,impropDenominatorOne, impropNumeratorTwo,impropDenominatorTwo);
    	}
    	else if (operator.equals("-")){
    		calculateAnswer = FracCalc.subtract(impropNumeratorOne,impropDenominatorOne, impropNumeratorTwo,impropDenominatorTwo);
    	}
    	else if (operator.equals("*")){
    		calculateAnswer = FracCalc.multiply(impropNumeratorOne,impropDenominatorOne, impropNumeratorTwo,impropDenominatorTwo);
    	}
    	else {
    		calculateAnswer = FracCalc.divide(impropNumeratorOne,impropDenominatorOne, impropNumeratorTwo,impropDenominatorTwo);
    	}    	
    	
		return calculateAnswer;
    	//returns string answer
    }
    
    public static String add(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){
    	int commonDenominator = impropDenominatorOne * impropDenominatorTwo;
    	int sumNumerator = (impropNumeratorOne*impropDenominatorTwo)+ (impropNumeratorTwo*impropDenominatorOne);
    	return sumNumerator + "/" + commonDenominator;
    }
    
    public static String subtract(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){
    	int commonDenominator = impropDenominatorOne * impropDenominatorTwo;
    	int sumNumerator = (impropNumeratorOne*impropDenominatorTwo)- (impropNumeratorTwo*impropDenominatorOne);
    	return sumNumerator + "/" + commonDenominator;
    }
    
    public static String multiply(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){
    	int productDenominator = impropDenominatorOne * impropDenominatorTwo;
    	int productNumerator = impropNumeratorOne * impropNumeratorTwo;
    	return productNumerator + "/" + productDenominator;
    }
    
    public static String divide(int impropNumeratorOne,int impropDenominatorOne, int impropNumeratorTwo, int impropDenominatorTwo){
    	int quotientDenominator = impropDenominatorOne * impropNumeratorTwo;
    	int quotientNumerator = impropNumeratorOne * impropDenominatorTwo;
    	return quotientNumerator + "/" + quotientDenominator;
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
