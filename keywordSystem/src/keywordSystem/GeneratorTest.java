package keywordSystem;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GeneratorTest {

	@Test
	void testGenerate_exact() {
//		Generator.generate_exact(3).stream().forEach(System.out::println);
		assertEquals(Generator.generate_exact(1).size(),3);
		assertEquals(Generator.generate_exact(2).size(),12);
	}
	@Test
	void testConvertToLabel() {
		Label label_test = new Label().convertToLabel("generateKeyword");
		assertEquals(label_test.label.size(),2);
		assertEquals(label_test.label.get(0),"generate");
		assertEquals(label_test.label.get(1),"keyword");
		
		//test 2
		Label label_test2 = new Label().convertToLabel("generateKeywordList");
		assertEquals(label_test2.label.size(),3);
		assertEquals(label_test2.label.get(0),"generate");
		assertEquals(label_test2.label.get(1),"keyword");
		
	}
}
