/**
 * ExpressionTree class. The ExpressionTree points to a root ExpressionNode, which in turn
 * points to the entire tree. Implements Stack algorithms to evaluate the ExpressionTree, and 
 * to express the tree in terms of post order, in order, and pre order. 
 * @author Vivian Liu
 */
import java.util.Arrays;
import java.util.Stack;

public class ExpressionTree {
	
	private static ExpressionNode root;
	private static String[] possibleOperators = new String[] {"+", "-", "*", "/"};
	private static Stack<ExpressionNode> theNodes = new Stack<>();

	private Object value;
	private ExpressionNode left;
	private ExpressionNode right;
	
	public ExpressionTree(Object valueIn, ExpressionNode leftIn, ExpressionNode rightIn) {
		value = valueIn;
		left = leftIn;
		right = rightIn;
	}
	/**
	 * This method generates an ExpressionTree based on the post order expression given.
	 * @param expression
	 * @return
	 */
	public static ExpressionTree generateExpressionTree(String[] expression) {
		for (int i = 0; i < expression.length; i++) {
			String token = expression[i];
			if (!Arrays.asList(possibleOperators).contains(token)) {
				int value = Integer.parseInt(token);
				ExpressionNode operandNode = new ExpressionNode(value, null, null);
				theNodes.push(operandNode);
			}
			else {
				ExpressionNode right = (ExpressionNode)theNodes.pop();
				ExpressionNode left = (ExpressionNode)theNodes.pop();
				ExpressionNode newNode = new ExpressionNode(token, left, right);
				theNodes.push(newNode);
			}
		}
		root = theNodes.peek();
		return new ExpressionTree(theNodes.peek().getValue(), theNodes.peek().getLeft(), 
				theNodes.peek().getRight());
	}
	
	/*
	 * Wrapper class for evaluate method
	 */
	public static double eval() {
		return auxEval(root);
	}
	/**
	 * Given an expression tree, evaluates the expression using a stack algorithm
	 * @param t
	 * @return
	 */
	private static double auxEval(ExpressionNode t) {
		String token = t.getValue().toString();
		if (!Arrays.asList(possibleOperators).contains(token)) {
			double value = Integer.parseInt(token);
			return value;
		}
		else {
			if (token.equals("+")) {
				return auxEval(t.getLeft()) + auxEval(t.getRight());
			}
			if (token.equals("-")) {
				return auxEval(t.getLeft()) - auxEval(t.getRight());
			}
			else if (token.equals("*")) {
				return auxEval(t.getLeft()) * auxEval(t.getRight());
			}
			else {
				return auxEval(t.getLeft()) / auxEval(t.getRight());
			}
		}
		
	}
	
	/**
	 * Wrapper class for postFix order.
	 */
	public static String postfix() {
		return auxPostfix(root);
	}
	/**
	 * Returns the String representation of the expression in post fix order.
	 * @param t
	 * @return
	 */
	private static String auxPostfix(ExpressionNode t) 
	{
		if ( t == null )
		{
			return "";
		}
		else
		{
			return auxPostfix( t.getLeft() ) +
					auxPostfix(t.getRight()) +
					t.getValue().toString() + " ";
		}
	}
	
	/**
	 * Wrapper class for inFix order
	 */
	public static String infix() {
		return auxInfix(root);
	}
	/**
	 * Returns the String representation of the expression in infix order.
	 * @param t
	 * @return
	 */
	private static String auxInfix(ExpressionNode t) {
		if ( t == null )
		{
			return "";
		}
		else
		{
			return "(" + auxInfix( t.getLeft() ) +
					t.getValue().toString() + 
					auxInfix(t.getRight()) + ")";	
		}
	}
	
	/**
	 * Wrapper class for preFix order
	 * @return
	 */
	public static String prefix() {
		return auxPrefix(root);
	}
	/**
	 * Returns the String representation of the expression in order.
	 * @param t
	 * @return
	 */
	private static String auxPrefix(ExpressionNode t) {
		if ( t == null )
		{
			// System.out.println("On jah?");
			return "";
		}
		else
		{
			return t.getValue().toString() + " " +
					auxPrefix( t.getLeft() ) +
					auxPrefix(t.getRight());		
		}
	}
	
	/**
	 * Nested inner class for ExpressionTree. Each ExpressionNode represents a node on the tree.
	 * Each ExpressionNode has a value, a pointer to a left ExpressionNode, and a pointer to a 
	 * right ExpressionNode
	 * @author vivianliu
	 *
	 */
	private static class ExpressionNode {
		private Object value;
		private ExpressionNode left;
		private ExpressionNode right;
		
		public ExpressionNode(Object valueIn, ExpressionNode leftIn, ExpressionNode rightIn) {
			value = valueIn;
			left = leftIn;
			right = rightIn;
		}
		/**
		 * Returns the value stored in the Node
		 * @return
		 */
		public Object getValue() {
			return value;
		}
		/**
		 * Returns the left ExpressionNode
		 * @return
		 */
		public ExpressionNode getLeft() {
			return left;
		}
		/**
		 * Returns the right ExpressionNode
		 * @return
		 */
		public ExpressionNode getRight() {
			return right;
		}
	}
}
