package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClumpTest {

	@Test
	public void test() {
		ClumpT c = new ClumpT();
		boolean b = c.isPresent(17761);
		assertEquals(false, b);
		
	}

}
