package keywordSystem;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;


class GeneratorTest {
//
//	@Test
//	void testGenerate_exact() {
//		 Generator.generate_exact(2).stream().forEach(System.out::println);
//		assertEquals(Generator.generate_exact(1).size(), 4);
//		assertEquals(Generator.generate_exact(2).size(), 36);
//		assertEquals(Generator.generate_exact(3).size(), 3204);
//	}
//	
//	
//
//	@Test
//	void testConvertToLabel() {
//		Label label_test = new Label().convertToLabel("generateKeyword");
//		assertEquals(label_test.label.size(), 2);
//		assertEquals(label_test.label.get(0), "generate");
//		assertEquals(label_test.label.get(1), "keyword");
//
//		// test 2
//		Label label_test2 = new Label().convertToLabel("generateKeywordList");
//		assertEquals(label_test2.label.size(), 3);
//		assertEquals(label_test2.label.get(0), "generate");
//		assertEquals(label_test2.label.get(1), "keyword");
//
//		// test 3
//		Label label_test3 = new Label().convertToLabel("generate");
//		assertEquals(label_test3.label.size(), 1);
//		assertEquals(label_test3.label.get(0), "generate");
//	}
//	
//	@Test
//	void testGetScore() {
//		String keyword = "add line";
//		Var var1 = new Var("src", new Type("BufferReader"));
//		// src ==> -0.059
//		assertEquals(-0.059f, var1.getScore(keyword), 0);
//		// 1 ==> -0.06
//		assertEquals(-0.06f,new Int(1).getScore(keyword),0);
//		// readLine() : BufferReader ==> String
//		MethodName mname1 = new MethodName("readLine",
//				new Type[] {new Type("String"),new Type("BufferReader")});
//		Expression[] args1 = new Expression[] {var1};
//		// src.readLine() ==> 0.881
//		Expression exp1 = new MethodInvocation(mname1,args1);
//		assertEquals(0.881f,exp1.getScore(keyword),0);
//		Var var2 = new Var("array", new Type("List<String>"));
//		// array ==> -0.059
//		assertEquals(-0.059f, var2.getScore(keyword), 0);
//		MethodName mname2 = new MethodName("add",
//				new Type[] {new Type("boolean"),new Type("List<String>")});
//		Expression[] args2 = new Expression[]{var2,exp1};
//		Expression exp2 = new MethodInvocation(mname2,args2);
//		// array.add(src.readLine()) ==> 1.772
//		assertEquals(1.772f,exp2.getScore(keyword),0);
//		
//	}
	@Test
	void testVarGenerator(){
//		// <a,String> <b,String> <src, BufferReader> <i, Integer> <1, Integer>
//		// <concat, String, String> <add, String>
//		// if keywords is "a" and want a "String" type expression then the result is "a"
//		String keyword = "a";
//		assertEquals(Generator.generate_exact(1, new Type("String"), keyword).get(0).toString(),"a");
//		// if keywords is "b" and want a "String" type expression then the result is "b"
//		keyword = "b";
//		assertEquals(Generator.generate_exact(1, new Type("String"), keyword).get(0).toString(),"b");
//		// if keywords is "s" and want a "String" type expression then the result is "a" & "b"
//		// if keywords is "a" and want a "Integer" type expression then the result is "1" & "i"
		
		String keyword = "s"; 
//		System.out.println(Generator.generate_exact(1, new Type("String"), keyword).size());
//		assertEquals(Generator.generate_exact(1, new Type("String"), keyword).get(0).toString(),"a");
//		assertEquals(Generator.generate_exact(1, new Type("String"), keyword).get(1).toString(),"b");
//		assertEquals(Generator.generate_exact(1, new Type("Integer"), keyword).get(0).toString(),"1");
		assertEquals(Generator.generate_exact(1, new Type("Integer"), keyword).get(1).toString(),"i");
		Generator.allMaxExpression.clear();
		Generator.generate_exact(1, new Type("String"), keyword);
//		Generator.generate_exact(1, new Type("String"), keyword).stream().forEach(System.out::println);
		
		
	}
	@Test
	void testAllMaxExpressions() {
		
	}
	@Test
	void testGenerateWithSubExp() {
		Vector<Expression> result = new Vector<Expression>();
		new IntGenerator().getIntGenerator(new Type("String")).generateWithSubExps(new Expression[0], result, "s");
//		System.out.println(result.size());
	}
	
	
	@Test
	void testAllExpressionGeneratorsWithTypeT() {
		String keyword = "s";
		Vector<Generator> gs = new Vector<Generator>();
		gs.addAll(Generator.allExpressionGeneratorsWithTypeT(new Type("String")));
		assertEquals(gs.size(),4);
	}
	
}
