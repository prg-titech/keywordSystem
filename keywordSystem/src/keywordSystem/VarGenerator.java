package keywordSystem;

import java.util.Vector;

public class VarGenerator extends Generator {

	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.add(new Var("a",new Type("String")));
		result.add(new Var("b",new Type("String")));

	}

	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
