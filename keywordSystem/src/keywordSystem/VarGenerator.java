package keywordSystem;

import java.util.Vector;

public class VarGenerator extends Generator {

	// add Var Expression function could be added later

	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.add(new Var("concat",new Type("String")));
		result.add(new Var("add",new Type("String")));
		}

	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
