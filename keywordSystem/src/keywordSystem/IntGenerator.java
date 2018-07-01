package keywordSystem;

import java.util.Vector;

public class IntGenerator extends Generator {
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result,Type type, String keyword) {
		for(Expression intExpression : this.allIntExpression()) {
			if(new Type().matchSubtype(type,intExpression.getType())) {
				result.add(intExpression);
			}
		}
		if(result.size() >= 1) {
			selectMaxVarExpressions(result,keyword);
		}

	}


	private Vector<Expression> allIntExpression() {
		Vector<Expression> allIntExpression = new Vector<Expression>();
		allIntExpression.add(new Int(1));
		return allIntExpression;
	}
	
	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
