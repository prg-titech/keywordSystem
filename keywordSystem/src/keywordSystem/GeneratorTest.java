package keywordSystem;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class GeneratorTest {

	@Test
	void testGenerate_exact() {
//		 Generator.generate_exact(2).stream().forEach(System.out::println);
		assertEquals(Generator.generate_exact(1).size(), 4);
		assertEquals(Generator.generate_exact(2).size(), 20);
		assertEquals(Generator.generate_exact(3).size(), 580);
	}
	
	

	@Test
	void testConvertToLabel() {
		Label label_test = new Label().convertToLabel("generateKeyword");
		assertEquals(label_test.label.size(), 2);
		assertEquals(label_test.label.get(0), "generate");
		assertEquals(label_test.label.get(1), "keyword");

		// test 2
		Label label_test2 = new Label().convertToLabel("generateKeywordList");
		assertEquals(label_test2.label.size(), 3);
		assertEquals(label_test2.label.get(0), "generate");
		assertEquals(label_test2.label.get(1), "keyword");

		// test 3
		Label label_test3 = new Label().convertToLabel("generate");
		assertEquals(label_test3.label.size(), 1);
		assertEquals(label_test3.label.get(0), "generate");
	}
	
	@Test
	void testGetScore() {
		String keyword = "add line";
		Var var1 = new Var("src", new Type("BufferReader"));
		// src ==> -0.059
		assertEquals(-0.059f, var1.getScore(keyword), 0);
		// 1 ==> -0.06
		assertEquals(-0.06f,new Int(1).getScore(keyword),0);
		// readLine() : BufferReader ==> String
		MethodName mname1 = new MethodName("readLine",
				new Type[] {new Type("String"),new Type("BufferReader")});
		Expression[] args1 = new Expression[] {var1};
		// src.readLine() ==> 0.881
		Expression exp1 = new MethodInvocation(mname1,args1);
		assertEquals(0.881f,exp1.getScore(keyword),0);
		Var var2 = new Var("array", new Type("List<String>"));
		// array ==> -0.059
		assertEquals(-0.059f, var2.getScore(keyword), 0);
		MethodName mname2 = new MethodName("add",
				new Type[] {new Type("boolean"),new Type("List<String>")});
		Expression[] args2 = new Expression[]{var2,exp1};
		Expression exp2 = new MethodInvocation(mname2,args2);
		// array.add(src.readLine()) ==> 1.772
		assertEquals(1.772f,exp2.getScore(keyword),0);
		
	}
}
