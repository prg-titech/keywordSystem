package keywordSystem;

import java.util.List;

public class Int extends Expression {
	public int i;
	Type type_integer = new Type("Integer");

	public Int(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return i + "";
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return type_integer;
	}

	@Override
	public float getScore(List<String> keywords) {
		float score = addPrecise(DEFSCORE,LMVAR);
		if (keywords.contains(i+"")) {
			score = addPrecise(score, WIK);

		} else {
			score = addPrecise(score, -WNIK);
		}

		return score;
	}

	public Label label() {
		return new Label().convertToLabel(i + "");
	}


}
