package keywordSystem;

import java.util.Vector;

public class IntGenerator extends Generator {
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.add(new Int(1));
		result.add(new Int(2));

	}
	
	@Override
	int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
