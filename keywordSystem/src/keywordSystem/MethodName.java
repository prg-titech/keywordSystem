package keywordSystem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import keywordSystem.Expression;

public class MethodName {
	String name;
	Type[] types;

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

	public List<String> label() {
		return new Label().convertToLabel(name).label;
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
		float score = Expression.DEFSCORE;
		List<String> name_label = this.label();
		for(String word : name_label) {
			if(keywords.contains(word)) {
				score = Expression.addPrecise(score,Expression.WIK);
				keywords.remove(word);
			}else{
				score = Expression.addPrecise(score,-Expression.WNIK);
			}
		}
		return score;
	}
	
	
	

}
