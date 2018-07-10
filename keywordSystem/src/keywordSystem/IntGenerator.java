package keywordSystem;

import java.util.Vector;

public class IntGenerator extends Generator {
	Vector<Expression> allIntExpressionWithTypeT = new Vector<Expression>();
	public IntGenerator getIntGenerator(Type t) {
		if(t.toString().equals("Integer")) {
			allIntExpressionWithTypeT.addAll(this.getAllIntExpression());
		}
		return this;
	}


	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {

		result.addAll(allIntExpressionWithTypeT);

	}


	private Vector<Expression> getAllIntExpression() {
		Vector<Expression> allIntExpression = new Vector<Expression>();
		allIntExpression.add(new Int(1));
		return allIntExpression;
	}
	
	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	Type[] types() {
		// TODO Auto-generated method stub
		return new Type[] {};
	}

}
