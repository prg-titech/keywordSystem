package keywordSystem;

import java.util.Vector;

public class MethodInvocationGenerator extends Generator implements IMethodName{
	int arity;
	String name;
	
	int arity() {
		return arity;
	}
	
	@Override
	void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {		
		result.add(new MethodInvocation(name,arity,subExps));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getArity() {
		// TODO Auto-generated method stub
		return arity;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setArity(int arity) {
		// TODO Auto-generated method stub
		this.arity = arity;
	}

}
