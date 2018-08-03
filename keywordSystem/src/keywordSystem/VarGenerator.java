package keywordSystem;

import java.util.Vector;

public class VarGenerator extends Generator {

	// add Var Expression function could be added later
	
	Vector<Expression> allVarExpressionWithTypeT = new Vector<Expression>();

	/*
	 * given Type and keyword , return all expressions with type belong Type 
	 * and has the bigest score according to keywords
	 */
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.addAll(allVarExpressionWithTypeT);
	}

	@Override
	void addGenerator(Type t, Vector<Generator> allGeneratorWithTypeT) {
		Vector<Expression> allVarExpression = this.getAllVarExpression();
		for(Expression varExpression:allVarExpression) {
			if(varExpression.getType().equals(t)) {
				this.allVarExpressionWithTypeT.add(varExpression);
			}
		}
		allGeneratorWithTypeT.add(this);
	}

	private Vector<Expression> getAllVarExpression() {
		Vector<Expression> allVarExpression = new Vector<Expression>();
		allVarExpression.add(new Var("a",new Type("String")));
		allVarExpression.add(new Var("b",new Type("String")));
		allVarExpression.add(new Var("src",new Type("BufferReader")));
		allVarExpression.add(new Var("i",new Type("Integer")));
		allVarExpression.add(new Var("array",new Type("List<String>")));
		return allVarExpression;
	}


	@Override
	Type[] types() {
		return new Type[] {};
	}


}
