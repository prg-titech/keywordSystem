package keywordSystem;

import java.util.Vector;

public class VarGenerator extends Generator {

	// add Var Expression function could be added later

	/*
	 * given Type and keyword , return all expressions with type belong Type 
	 * and has the bigest score according to keywords
	 */
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result,Type type,String keyword) {
		for(Expression varExpression : this.allVarExpression()) {
			if(new Type().matchSubtype(type, varExpression.getType())) {
				result.add(varExpression);
			}
		}
		if(result.size() >= 1) {
			selectMaxVarExpressions(result,keyword);
		}
	}


	private Vector<Expression> allVarExpression() {
		Vector<Expression> allVarExpression = new Vector<Expression>();
		allVarExpression.add(new Var("a",new Type("String")));
		allVarExpression.add(new Var("b",new Type("String")));
		allVarExpression.add(new Var("src",new Type("BufferReader")));
		allVarExpression.add(new Var("i",new Type("Integer")));
		return allVarExpression;
	}

	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
