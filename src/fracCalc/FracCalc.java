package fracCalc;
import java.util.*;

public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner (System.in);
    	System.out.println("Enter in a fraction string");
    	String inputString = userInput.nextLine();
    	
    	
    	while (!inputString.equals("quit")){
    		System.out.println(produceAnswer(inputString));
    		System.out.println("Enter in a fraction string");
        	inputString = userInput.nextLine();        	
    	}

    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
    	String operand2 = "";
    	String operand = "";
    	String operator = "";
    	int numerator1;
    	int denominator1;
    	int wholeNumber1;
    	int numerator2;
    	int denominator2;
    	int wholeNumber2;
    	
    	
        // TODO: Implement this function to produce the solution to the input 
    	//separates the input string into the three components
    	if (input.indexOf(" +") > 0){
    	operand =  input.substring(0, input.indexOf(" +"));
    	operator = input.substring(input.indexOf("+"),input.indexOf("+")+1);
    	operand2 = input.substring(input.indexOf("+ ")+2);
    		}
    	else if (input.indexOf(" -") > 0){
        	operand =  input.substring(0, input.indexOf(" -"));
        	operator = input.substring(input.indexOf("-"),input.indexOf("-")+1);
        	operand2 = input.substring(input.indexOf("- ")+2);
        	}
    	else if (input.indexOf(" *") > 0){
        	operand =  input.substring(0, input.indexOf(" *"));
        	operator = input.substring(input.indexOf("*"),input.indexOf("*")+1);
        	operand2 = input.substring(input.indexOf("* ")+2);
        	}
    	else {
        	operand =  input.substring(0, input.indexOf(" /"));
        	operator = input.substring(input.indexOf("/"),input.indexOf("/")+1);
        	operand2 = input.substring(input.indexOf("/ ")+2);
        	}
    //parsing operand1
    		
    		if (!(operand.indexOf("/")>0)){
    		//integer
    			wholeNumber1 = Integer.parseInt(operand);
    		}
    		else if (!(operand.indexOf("_")>0) && (operand.indexOf("/")>0)){
    		//fraction
    			numerator1 = Integer.parseInt(operand.substring(0, operand.indexOf("/")));
    			denominator1 = Integer.parseInt(operand.substring(operand.indexOf("/")+1));
    		}
    		else {
    		//mixed number	
    			numerator1 = Integer.parseInt(operand.substring(operand.indexOf("_")+1, operand.indexOf("/")));
    			denominator1 = Integer.parseInt(operand.substring(operand.indexOf("/")+1));
    			wholeNumber1 = Integer.parseInt(operand.substring(0,operand.indexOf("_")));
    		}
    		
//parsing operand2
    		
    		if (!(operand2.indexOf("/")>0)){
    		//integer
    			wholeNumber2 = Integer.parseInt(operand2);
    			numerator2 = 0;
    			denominator2 = 1;
    		}
    		else if (!(operand2.indexOf("_")>0) && (operand2.indexOf("/")>0)){
    		//fraction
    			numerator2 = Integer.parseInt(operand2.substring(0, operand2.indexOf("/")));
    			denominator2 = Integer.parseInt(operand2.substring(operand2.indexOf("/")+1));
    			wholeNumber2 = 0;
    		}
    		else {
    		//mixed number	
    			numerator2 = Integer.parseInt(operand2.substring(operand2.indexOf("_")+1, operand2.indexOf("/")));
    			denominator2 = Integer.parseInt(operand2.substring(operand2.indexOf("/")+1));
    			wholeNumber2 = Integer.parseInt(operand2.substring(0,operand2.indexOf("_")));
    		}
    	
    	//returns parsed components of operand2
        return "whole:" + wholeNumber2 + " numerator:" + numerator2 + " denominator:" + denominator2;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
