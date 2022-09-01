import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	
	private GradeBook book1;
	private GradeBook book2;

	@BeforeEach
	void setUp() throws Exception {
		
		book1 = new GradeBook(5);
		book2 = new GradeBook(5);
		book1.addScore(85);
		book1.addScore(91);
		book1.addScore(72);
		book2.addScore(87);
		book2.addScore(65);
		book2.addScore(76);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		book1 = null;
		book2 = null;
		
	}
	
	@Test
	public void testAddScore() {
		assertTrue(book1.toString().equals("85.0 91.0 72.0 0.0 0.0 "));
		assertTrue(book2.toString().equals("87.0 65.0 76.0 0.0 0.0 "));
		assertEquals(book1.getScoreSize(), 3);
		assertEquals(book2.getScoreSize(), 3);

	}
	
	@Test
	public void testSum() {
		assertEquals(book1.sum(), 248);
		assertEquals(book2.sum(), 228);
		
	}
	
	@Test
	public void testMinimum() {
		assertEquals(book1.minimum(), 72);
		assertEquals(book2.minimum(), 65);
		
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(book1.finalScore(), 176, 0);
		assertEquals(book2.finalScore(), 163,0);
		
	}
	
}
