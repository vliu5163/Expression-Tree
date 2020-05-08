/**
 * Tester class for Expression Tree operations.
 * @author Vivian Liu
 */
import java.util.Scanner;

public class Problem2 {
	
	private static final Scanner CONSOLE = new Scanner(System.in);
	
	public static void main (String[] args) {
		System.out.println("Please input an expression in postfix notation, "
				+ "separating each token with spaces.");
		String userInput = CONSOLE.nextLine();
		String[] expression = userInput.split(" ");
		ExpressionTree finalExpression = new ExpressionTree(null, null, null).generateExpressionTree(expression);
		// displays all of the operations.
		if (finalExpression!= null)
		{
			System.out.println("Evaluated expression:");
			System.out.println(finalExpression.eval());
			System.out.println("User input in postfix order:");
			System.out.println(finalExpression.postfix());
			System.out.println("User input in prefix order");
			System.out.println(finalExpression.prefix());
			System.out.println("User input in infix order");
			System.out.println(finalExpression.infix());
		}
		else
		{
			System.out.println("Invalid expression.");
		}
		
	}

}
