package keywordSystem;

public class Var extends Expression {
	private String name;
	private Type type;
	
	public Var(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public float getScore(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

}
