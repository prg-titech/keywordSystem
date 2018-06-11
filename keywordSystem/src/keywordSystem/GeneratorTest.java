package keywordSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeneratorTest {

	@Test
	void testGenerate_exact() {
		Generator.generate_exact(3).stream().forEach(System.out::println);
//		assertEquals(Generator.generate_exact(1).size(),3);
	}

}
