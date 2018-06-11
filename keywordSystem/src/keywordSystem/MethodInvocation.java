package keywordSystem;

public class MethodInvocation extends Expression {

	String name;
	int arity;
	Expression[] args;
	public MethodInvocation(String name, int arity, Expression[] args) {
		super();
		this.name = name;
		this.arity = arity;
		this.args = args.clone();
	}
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("("+args[0]+")."+name+"(");
		String separator = "";
		for (int i=1;i<arity; i++) {
			result.append(separator+args[i]);
			separator = ",";
		}
		result.append(")");
		return result.toString();
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return args[0].getType();
	}
	@Override
	public float getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}
