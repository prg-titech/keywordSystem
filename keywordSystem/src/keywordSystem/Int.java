package keywordSystem;

public class Int extends Expression {
	public int i;
	Type type_integer = new Type("String");
	public Int(int i) {
		this.i = i;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return i+"";
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return type_integer;
	}

	@Override
	public float getScore(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

}
