
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * 
 * @author Luiz
 * CMSC204 Assignment 1 PasswordChecker
 */
public class PasswordCheckerUtility {

   public PasswordCheckerUtility() {}
    
   /**
    * Compare equality of two passwords
    * @param password Original password
    * @param passwordConfirm Second confirmation password
    * @throws UnmatchedException
    */
   public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{

	   boolean flag = password.equals(passwordConfirm);

		   if(flag == false) {
			   throw new UnmatchedException();
		   }

   }

   /**
    * Compare equality of two passwords with boolean output
    * @param password Original password
    * @param passwordConfirm Second confirmation password
    * @return The status of comparison, same/different
    */
   public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
	   return password.equals(passwordConfirm);
    }

   /**
    * This method will accept an ArrayList of passwords as the parameter and return 
    * an ArrayList with the status of any invalid passwords 
    * (weak passwords are not considered invalid).
    * @param passwords An ArrayList of passwords
    * @return An ArrayList of invalid passwords and their errors
    */
   public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
       ArrayList<String> status = new ArrayList<>();

	       for(int i = 0; i < passwords.size(); i++) {
	    	   try {
	    		   isValidPassword(passwords.get(i));
	    	   }
	    	   catch(Exception exception){
	    		   status.add(i, passwords.get(i)+ " -> " + exception.getMessage());
	    	   }
	       }
        
        return status;
   }
    
   /**
    * Checks the password Digit requirement - Password must contain a numeric character
    * @param password The password input of user
    * @return Whether or not the password contains a number 0-9 (true/false)
    * @throws NoDigitException
    */
   public static boolean hasDigit(String password) throws NoDigitException{
        boolean flag = false;
       
	    for(int i = 0; i < password.length(); i++){
	        if(Character.isDigit(password.charAt(i))){
	            flag = true;
	        }
	    }
	    if(flag == false) {
	    	throw new NoDigitException();
	    }

        return flag;
    }
   
   /**
    * Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
    * @param password The password input of user
    * @return Whether or not the password contains a lowercase letter a-z (true/false)
    * @throws NoLowerAlphaException
    */
   public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
        boolean flag = false;
        
        try {
	        for(int i = 0; i < password.length(); i++) {
	        	if(Character.isLowerCase(password.charAt(i))) {
	        		flag = true;
	        		continue;
	        	}
	        }
	        if(flag == false) {
	        	throw new NoLowerAlphaException();
	        }
        }
        catch(NoLowerAlphaException exception){
 		   exception.printStackTrace();
 		  

        }
        
        return flag; 
    }

   /**
    * Checks the password SpecialCharacter requirement - Password must contain a Special Character
    * @param password The password input of user
    * @return Whether or not the password contains a special character (true/false)
    * @throws NoSpecialCharacterException
    */
   public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
	   boolean result = false;
	   
	   try {
		   Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		   Matcher m = pattern.matcher(password);
		   result = !(m.matches());
		   
		   if(result == false) {
			   throw new NoSpecialCharacterException();
		   }
	   }
	   catch(NoSpecialCharacterException exception){
		   exception.printStackTrace();
		   
	   }
	   
        return result;
    }
   
   /**
    * Checks the password alpha character requirement - Password must contain an uppercase alpha character
    * @param password The password input of user
    * @return Whether or not the password contains an uppercase letter A-Z (true/false)
    * @throws NoUpperAlphaException
    */
   public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
        boolean flag = false;
        
        try {
        	for(int i = 0; i < password.length(); i++) {
        		if(Character.isUpperCase(password.charAt(i))) {
        			flag = true;
        			continue;
        		}
        	}
        	if(flag == false) {
        		throw new NoUpperAlphaException(); 
        	}
        }
        catch(NoUpperAlphaException exception) {
        	exception.printStackTrace();
        	
        }
        
        return flag;
   }
   
   /**
    * Checks the password length requirement - The password must be at least 6 characters long
    * @param password The password input of user
    * @return Whether or not the password is of appropriate length (6+ characters) (true/false)
    * @throws LengthException
    */
   public static boolean isValidLength(String password) throws LengthException{
	   boolean flag = false;
	
		  if(password.length() >= 6) {
			  flag = true;
		  }
		  if(flag == false) {
			  throw new LengthException();
		  }
	   
       return flag;
   }

   /**
    * Return true if valid password (follows all of the rules), returns false if an invalid password 1
    * @param password The password input of user
    * @return Whether or not the password is valid ie. passes all of the function tests (true/false)
    * @throws LengthException
    * @throws NoUpperAlphaException
    * @throws NoLowerAlphaException
    * @throws NoDigitException
    * @throws NoSpecialCharacterException
    * @throws InvalidSequenceException
    */
   public static boolean isValidPassword(String password)throws LengthException, NoUpperAlphaException, NoLowerAlphaException, 
   NoDigitException, NoSpecialCharacterException, InvalidSequenceException
   {
	   boolean flag = true;
	 
		   if(isValidLength(password)==false) {
			   flag = false;
			   throw new LengthException();
		   }
		   if(hasUpperAlpha(password)==false){
			   flag = false;
			   throw new NoUpperAlphaException();
		   }
		   if(hasLowerAlpha(password)==false) {
			   flag = false;
			   throw new NoLowerAlphaException();
		   }
		   if(hasDigit(password)==false) {
			   flag = false;
			   throw new NoDigitException();
		   }
		   if(hasSpecialChar(password)==false) {
			   flag = false;
			   throw new NoSpecialCharacterException();
		   }
		   if(NoSameCharInSequence(password)==false) {
			   flag = false;
			   throw new InvalidSequenceException();
		   }
	
	  return flag;
	   
   }
   
   /**
    * checks if the password contains 6 to 9 characters
    * @param password The password input of user
    * @return Whether or not the password contains 6-9 characters (inclusive) (true/false)
    */
   public static boolean hasBetweenSixAndNineChars(String password){
       return password.length() >= 6 && password.length() <= 9;
   }

   /**
    * Checks if password is VALID and the length is NOT between 6-9 characters
    * @param password The password input of user
    * @return Whether or not the password is weak, determined if password is valid but is not 10+ chars
    * @throws WeakPasswordException
    * @throws LengthException
    * @throws NoUpperAlphaException
    * @throws NoLowerAlphaException
    * @throws NoDigitException
    * @throws NoSpecialCharacterException
    * @throws InvalidSequenceException
    */
   public static boolean isWeakPassword(String password) throws WeakPasswordException, LengthException, NoUpperAlphaException, 
   NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
	   boolean flag = false;
	   
	    	if(isValidPassword(password)&&!(hasBetweenSixAndNineChars(password))) {
	    		flag = false;
	    	}
	    	
	    	if(isValidPassword(password)&&(hasBetweenSixAndNineChars(password))) {
	    		flag = true;
	    		throw new WeakPasswordException();
	    	}
	 	    
        return flag;
    }

   /**
    * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
    * @param password The password input of user
    * @return Whether or not the password contains a valid sequence of character such that no one character is repeated more than 2 times
    * @throws InvalidSequenceException
    */
   public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException{
   	boolean flag = true; 
   	//counter for all characters ascii values
   	int counter[] = new int[256];
 
	    	Arrays.fill(counter, 0);
	    	
	    	for(int i = 0; i < password.length(); i++) {
	    		if(counter[Integer.valueOf(password.charAt(i))] == 2) {
	    			flag = false; 
	    			throw new InvalidSequenceException();
	    		}
	    		else {
	    			counter[Integer.valueOf(password.charAt(i))]++;
	    		}
	    	}
	    	
  
       return flag;
   }

}
