package keywordSystem;

import java.util.Vector;

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


	public static Vector<MethodName> allMethodName() {
		
		Vector<MethodName> allMethodName = new Vector<MethodName>();
		allMethodName.add(new MethodName("concat",new Type[]{new Type("String"),new Type("String")}));
		return allMethodName;
	}

}
