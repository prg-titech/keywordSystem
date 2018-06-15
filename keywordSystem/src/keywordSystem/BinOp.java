package keywordSystem;

public class BinOp extends Expression {
	private String operator;
	private Expression operand1;
	private Expression operand2;
	public BinOp(String operator, Expression operand1, Expression operand2) {
		super();
		this.setOperator(operator);
		this.setOperand1(operand1);
		this.setOperand2(operand2);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + operand1.toString() + ") " + operator + " (" + operand2 + ")";
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		String operand1_type = operand1.getType().toString();
		String operand2_type = operand2.getType().toString();
		return (operand1_type == operand2_type)?
				operand1.getType() :
					// modify later to represent error message;
					null
				;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Expression getOperand1() {
		return operand1;
	}
	public void setOperand1(Expression operand1) {
		this.operand1 = operand1;
	}
	public Expression getOperand2() {
		return operand2;
	}
	public void setOperand2(Expression operand2) {
		this.operand2 = operand2;
	}
	@Override
	public float getScore(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

}
