package keywordSystem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class MethodName {
	String name;
	Type[] types;
	// default score is -0.05
	public static final float DEFSCORE = -0.05f;
	// add 1.00 when words in keywords query
	public static final float WIK = 1.00f;
	// subtract 0.01 when words not in keywords query
	public static final float WNIK = 0.01f;

	public MethodName(String name, Type[] types) {
		this.name = name;
		this.types = types;
	}

	@Override
	public String toString() {
		return name;
	}

	public Type returnType() {
		return types[0];
	}

	public Label label() {
		return new Label().convertToLabel(name);
	}

	public boolean matchType(Type type, int pos) {
		return type.equals(types[pos]);
	}

	public boolean matchReceiverType(Type type) {
		return this.matchType(type, 0);
	}

	public static Vector<MethodName> allMethodName() {

		Vector<MethodName> allMethodName = new Vector<MethodName>();
		allMethodName.add(new MethodName("concat", new Type[] { new Type("String"), new Type("String") }));
		allMethodName.add(new MethodName("add", new Type[] { new Type("String") }));
		return allMethodName;
	}

	public float getScore(List<String> keywords) {
		float score = DEFSCORE;
		List<String> name_label = new Label().convertToLabel(name).label;
		int words_size = name_label.size();
		for(int i = 0; i < words_size; i++) {
			if (keywords.contains(name_label.get(i))) {
				score = addPrecise(score, WIK);

			} else {
				score = addPrecise(score, -WNIK);
			}	
		}
		return score;
	}
	
	public float addPrecise(float num1, float num2) {
		return new BigDecimal(Float.toString(num1)).add(new BigDecimal(Float.toString(num2))).floatValue();
	}
	
	

}
