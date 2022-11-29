import java.util.ArrayList;

/**
 * Main translation tree used for the morse code translator.
 * @author Luiz
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	
	private TreeNode<String> root;
	
	/**
	 * Default No-Arg constructor used to build the tree itself.
	 */
	public MorseCodeTree() {
		root = null;	
		buildTree();
	}

	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>("");
	}

	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	@Override
	public void insert(String code, String letter) {
		if(this.root == null) {
			this.root = new TreeNode<String>(letter);
		}
		else {
			addNode(this.root, code, letter);
		}
		
	}

	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		//stop condition will be the number of characters in the code = 0.
		if(code.length() > 1) {
			char current = code.charAt(0);
			String newCode = code.substring(1);
			
			if(current == '-') {
				addNode(root.getRight(), newCode, letter);
			}
			else{
				addNode(root.getLeft(), newCode, letter);
			}
		}
		
		else {
			if(code.equals(".")) {
				root.setLeft(new TreeNode<String>(letter));
			}
			else {
				root.setRight(new TreeNode<String>(letter));
			}
			
		}
		
	}

	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(this.root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		String letter = "";
		//stop condition will be the number of characters in the code = 0.
		if(code.length() > 1) {
			char current = code.charAt(0);
			String newCode = code.substring(1);
			
			if(current == '.') {
				root = root.getLeft();
				return fetchNode(root, newCode);
			}
			else if (current == '-') {
				root = root.getRight();
				return fetchNode(root, newCode);
			}
			
		}
		else {
			if(code.equals(".")) {
				letter = root.getLeft().getData();
				return letter;
			}
			else if(code.equals("-")) {
				letter = root.getRight().getData();
				return letter;
			}
			
		}

		return letter;
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * 
	 */
	@Override
	public void buildTree() {
		insert("","");
		//level 1
		insert(".", "e");
		insert("-", "t");
		//level 2
		insert("..","i");
		insert(".-","a");
		insert("-.","n");
		insert("--","m");
		//level 3
		insert("...","s");
		insert("..-","u");
		insert(".-.","r");
		insert(".--","w");
		insert("-..","d");
		insert("-.-","k");
		insert("--.","g");
		insert("---","o");
		//level 4
		insert("....","h");
		insert("...-","v");
		insert("..-.","f");
		insert(".-..","l");
		insert(".--.","p");
		insert(".---","j");
		insert("-...","b");
		insert("-..-","x");
		insert("-.-.","c");
		insert("-.--","y");
		insert("--..","z");
		insert("--.-","q");
	}

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> storage = new ArrayList<>();
		LNRoutputTraversal(this.root, storage);
		return storage;
	}
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if(root!=null) {
			
			LNRoutputTraversal(root.getLeft(), list);
			list.add(root.getData());
			LNRoutputTraversal(root.getRight(), list);
			
		}
	}

}
