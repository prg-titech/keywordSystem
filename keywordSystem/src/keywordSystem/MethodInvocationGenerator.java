package keywordSystem;

import java.util.Vector;

public class MethodInvocationGenerator extends Generator {
	MethodName name;
	
	int arity() {
		//部分式の型のlist
		return name.types.length;
	}
	
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result, String keywords) {

		result.add(new MethodInvocation(name,subExps));

	}

	public void setName(MethodName name) {
		this.name = name;
	}

	@Override
	Type[] types() {
		// TODO Auto-generated method stub
		return name.types;
	}


	

}
