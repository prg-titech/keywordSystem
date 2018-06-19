package keywordSystem;

import java.util.Vector;

public class MethodInvocationGenerator extends Generator implements IMethodName{
	MethodName name;
	
	int arity() {
		return name.types.length;
	}
	
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {

		result.add(new MethodInvocation(name,subExps));

	}

	@Override
	public MethodName getName() {
		// TODO Auto-generated method stub
		return name;
	}


	@Override
	public void setName(MethodName name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	

}
