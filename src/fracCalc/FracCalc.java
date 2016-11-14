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
        // TODO: Implement this function to produce the solution to the input    	
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
    	
        return operand2;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
