/**
 * Node data element used for the MorseCodeTree.
 * @author Luiz
 *
 * @param <T> The node's type
 */
public class TreeNode<T> {
	private T data;
	protected TreeNode<T> left;
	protected TreeNode<T> right;

	/**
	 * Default constructor used to instantiate a node with no children and a data entry.
	 * @param data Data of type T that is stored in the node.
	 */
	public TreeNode(T data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	/**
	 * Copy constructor used to make a deep copy of a node, copies data and pointers.
	 * @param copy The node that is getting copied.
	 */
	public TreeNode (TreeNode<T> copy) {
		this(copy.getData(), copy.getLeft(), copy.getRight());
	}
	
	/**
	 * Constructor used by the copy constructor, makes deep copies of everything.
	 * @param data The data that is getting copied.
	 * @param left Left child pointer of the original.
	 * @param right Right child pointer of the original.
	 */
	public TreeNode (T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = new TreeNode<T>(left);
		this.right = new TreeNode<T>(right);
	}
	
	/**
	 * Accessor method used to access the data of a node.
	 * @return Data contained in the node.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Accessor method used to access the left child node.
	 * @return Pointer to the left child of the given node.
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Mutator method used to set the left pointer of a node.
	 * @param left The node which is getting set as the original's left child pointer
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	/**
	 * Accessor method used to access the right child node.
	 * @return Pointer to the right child of the given node.
	 */
	public TreeNode<T> getRight() {
		return right;
	}

	/**
	 * Mutator method used to set the right pointer of a node.
	 * @param right The node which is getting set as the original's right child pointer
	 */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
	
	
}
