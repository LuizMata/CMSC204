
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Luiz
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> studentPasswords;
	String password1, password2;
	
	@Before
	public void setUp() throws Exception {
		studentPasswords = new ArrayList<String>();
		studentPasswords.add("YYUAN2992!");
		studentPasswords.add("ASDF");
		studentPasswords.add("1234567");
		studentPasswords.add("password");
		studentPasswords.add("Password123");
		studentPasswords.add("CNCMachine123");
	}

	@After
	public void tearDown() throws Exception {
		studentPasswords = null;  
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("AsDf~"));
			assertTrue("Did not throw lengthException",false);
			assertTrue(PasswordCheckerUtility.isValidPassword("AsDssssf~!"));
			assertTrue("Did not throw lengthException", true);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("123456"));
			assertTrue("Did not throw NoUpperAlphaException",false);
			assertTrue(PasswordCheckerUtility.isValidPassword("@1"));
			assertTrue("Did not throw NoUpperAlphaException",true);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("LUIZ2002"));
			assertTrue("Did not throw NoLowerAlphaException",false);
			assertTrue(PasswordCheckerUtility.isValidPassword("AsfL2003!"));
			assertTrue("Did not throw NoLowerAlphaException",true);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			 
			boolean weak = PasswordCheckerUtility.isWeakPassword("213dfdA@");
			assertTrue("Did not throw WeakPassword Exception",false);
			boolean weak2 = PasswordCheckerUtility.isWeakPassword("213dfdA@ASD");
			assertTrue("Did not throw WeakPassword Exception",true);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(false,PasswordCheckerUtility.NoSameCharInSequence("BBB!!@@BBBB"));
		 	assertTrue("Did not throw an InvalidSequenceException",false);
		 	assertEquals(true,PasswordCheckerUtility.NoSameCharInSequence("sdjfhbA"));
		 	assertTrue("Did not throw an InvalidSequenceException",true);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertEquals(false,PasswordCheckerUtility.hasDigit("BaaaBB!!@@BBBB"));
		 	assertTrue("Did not throw an NoDigitException",false);
		 	assertEquals(true,PasswordCheckerUtility.hasDigit("sdjSfh123bA"));
		 	assertTrue("Did not throw an NoDigitException",true);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw an NoDigitException",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoDigitException",false);
		}
	}
	
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("ValidPass45!"));
			assertTrue("Did not throw an exception",true);
		 	assertEquals(false,PasswordCheckerUtility.isValidPassword("sd45jSfhbA"));
		 	assertTrue("Did not throw an exception",false);
		}
		catch(NoSpecialCharacterException e)
		{
			assertTrue("Successfully threw an NoSpecialCharacterException",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoSpecialCharacterException",false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(studentPasswords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "YYUAN2992!");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "ASDF");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("long"));
		
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "1234567");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
				scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "password");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "Password123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "CNCMachine123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
	}
	
}
