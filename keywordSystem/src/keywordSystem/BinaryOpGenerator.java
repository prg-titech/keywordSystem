package keywordSystem;

import java.util.Vector;

public class BinaryOpGenerator extends Generator {
	Operator operator;
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result , String keywords) {
		result.add(new BinOp(operator,subExps[0],subExps[1]));

	}

	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 2;
	}

	public void setOperator(Operator operator) {
		this.operator=operator;
		
	}

	@Override
	Type[] types() {
		// TODO Auto-generated method stub
		return operator.types;
	}

	

}
