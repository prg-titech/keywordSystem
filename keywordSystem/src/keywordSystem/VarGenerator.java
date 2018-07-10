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


	private Vector<Expression> getAllVarExpression() {
		Vector<Expression> allVarExpression = new Vector<Expression>();
		allVarExpression.add(new Var("a",new Type("String")));
		allVarExpression.add(new Var("b",new Type("String")));
		allVarExpression.add(new Var("src",new Type("BufferReader")));
		allVarExpression.add(new Var("i",new Type("Integer")));
		return allVarExpression;
	}

	@Override
	int arity() {
		return 0;
	}


	public VarGenerator getVarGenerator(Type t) {
		Vector<Expression> allVarExpression = this.getAllVarExpression();
		int size_allVarExp = allVarExpression.size();
		for(int i=0 ; i<size_allVarExp ; i++) {
			Expression varExp_I = allVarExpression.get(i);
			if(varExp_I.getType().equals(t)) {
				this.allVarExpressionWithTypeT.add(varExp_I);
			}
		}
		return this;
		
	}


	@Override
	Type[] types() {
		return new Type[] {};
	}

}
