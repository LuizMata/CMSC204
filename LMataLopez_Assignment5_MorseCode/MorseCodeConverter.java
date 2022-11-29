import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for the morse code translator. Takes normal string input and accepts files.
 * @author Luiz
 *
 */
public class MorseCodeConverter {
	
	/**
	 * Default No-Arg constructor
	 */
	public MorseCodeConverter() {
		
	}

	/**
	 * Morse code translator that take a string as input.
	 * @param code Morse code text that is to be translated.
	 * @return The translation of the morse code in english.
	 */
	public static String convertToEnglish(String code) {
		String[] arr = code.split("[/ ]");
		MorseCodeTree tree = new MorseCodeTree();
		StringBuilder word = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == "") {
				word.append(tree.fetch(arr[i])+ " ");
			}
			else {
				word.append(tree.fetch(arr[i]));
			}
		}
		
		return word.toString().trim().replace("  ", " ");
	}
	
	/**
	 * Morse code translator that take a file as input.
	 * @param file Text file that contains morse code text that is to be translated.
	 * @return The translation of the morse code in english.
	 * @throws FileNotFoundException When the input file is not found.
	 */
	public static String convertToEnglish(File file) throws FileNotFoundException{
		Scanner sc = new Scanner(file);
		MorseCodeTree tree = new MorseCodeTree();
		StringBuilder word = new StringBuilder();
		
		while(sc.hasNextLine()) {
			String S = sc.nextLine();
			String[] arr = S.split("[/ ]");
			
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == "") {
					word.append(tree.fetch(arr[i])+ " ");
				}
				else {
					word.append(tree.fetch(arr[i]));
				}
			}
		}
		sc.close();
		
		return word.toString().trim().replace("  ", " ");
	}
	
	/**
	 * Testing method to print out the full translation tree with an In order traversal.
	 * @return A string containing the in order traversal, letter by letter.
	 */
	public static String printTree() {
		
		MorseCodeTree Tree = new MorseCodeTree();
		StringBuilder lnr = new StringBuilder();
		
		ArrayList<String> LNRTree = new ArrayList<>();
		LNRTree = Tree.toArrayList();
		
		for(int i = 0; i < LNRTree.size(); i++) {
				lnr.append(LNRTree.get(i) + " ");
		}
		
		lnr.deleteCharAt(lnr.length()-1);
		return lnr.toString();
	}

}
