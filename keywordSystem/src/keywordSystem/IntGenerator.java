package keywordSystem;

import java.util.Vector;

public class IntGenerator extends Generator {
 Vector<Expression> allIntExpressionWithTypeT = new Vector<Expression>();
	@Override
	void addGenerator(Type t, Vector<Generator> allGeneratorWithTypeT) {
		if(t.equals(new Type("Integer"))) {
			this.allIntExpressionWithTypeT.addAll(this.getAllIntExpression());
		}
		allGeneratorWithTypeT.add(this);
	}
//public Vector<Generator> getGenerator(Type t) {
//		if(t.toString().equals("Integer")) {
//			Vector<Generator>
//			for(Expression e:this.getAllIntExpression()) {
//				
//			}
//		}
//		return this;
//	}


	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {

		result.addAll(allIntExpressionWithTypeT);

	}


	private Vector<Expression> getAllIntExpression() {
		Vector<Expression> allIntExpression = new Vector<Expression>();
		allIntExpression.add(new Int(1));
		allIntExpression.add(new Int(2));
		return allIntExpression;
	}



	@Override
	Type[] types() {
		// TODO Auto-generated method stub
		return new Type[] {};
	}


}
