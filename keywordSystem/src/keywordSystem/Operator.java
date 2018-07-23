package keywordSystem;

import java.util.Vector;

public class Operator {
	String operator;
	Type[] types;
	public Operator(String operator,Type[] types) {
		this.operator = operator;
		this.types = types;
	}
	
	public static Vector<Operator> allOperator() {
		Vector<Operator> allOperator = new Vector<Operator>();
		allOperator.add(new Operator("+",new Type[] {new Type("Integer"),new Type("Integer")}));
		allOperator.add(new Operator("+",new Type[] {new Type("String"),new Type("String")}));
		return allOperator;
	}
	
	public String toString() {
		return operator;
	}

}
