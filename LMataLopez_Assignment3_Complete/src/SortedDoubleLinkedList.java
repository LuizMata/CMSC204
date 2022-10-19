import java.util.Comparator;
import java.util.ListIterator;
/**
 * A double linked list data structure that sorts based on each input with a given comparator
 * @author Luiz
 * @param <T> Linked List Data type
 */
public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T> implements Iterable<T>{
	private Comparator<T> comparator;
	
	/**
	 * Basic constructor for SortedDoubleLinkedList
	 * @param comparator Comparator used for sorting inputs
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	
	}
	
	/**
	 * Add function, creates a new node within the linked list and places it in the appropriate position based on comparator.
	 * @param data Input of generic type (same as whole linked list)
	 * @return Instance of a SortedDoubleLinkedList where the new item is added.
	 */
	public void add(T data) {
		if (size == 0) {
			if (head == null) {
				head = new Node<T>(data, null, null);
				tail = head;
			} else {
				Node<T> nextHead = new Node<T>(data, null, head);
				head.setPrev(nextHead);
				head = nextHead;
			}
			size++;
			return;
		}

		if (comparator.compare(head.getData(), data) > 0 || comparator.compare(tail.getData(), data) == 0) {
			Node<T> nextHead = new Node<T>(data, null, head);
			head.setPrev(nextHead);
			head = nextHead;
			
			size++;
			return;
		}

		if (comparator.compare(tail.getData(), data) < 0 || comparator.compare(tail.getData(), data) == 0) {
			Node<T> nextTail = new Node<T>(data, tail, null);
			tail.setNext(nextTail);
			tail = nextTail;
			
			size++;
			return;
		}

		int prevScore = -1;
		int currentScore;

		Node<T> current = head;
		Node<T> newNode;
		currentScore = comparator.compare(current.getData(), data);
		while (!(prevScore < 0 && currentScore > 0 || currentScore == 0)) {
			currentScore = comparator.compare(current.getData(), data);
			if (prevScore < 0 && currentScore > 0 || currentScore == 0) {
				newNode = new Node<>(data, current.getPrev(), current);
				current.getPrev().setNext(newNode);
				current.setPrev(newNode);
				size++;
				break;
			}
			prevScore = currentScore;
			current = current.getNext();
		}
		return;
	}
	
	/**
	 * Iterator which allows simple traversal of the SortedDoubleLinkedList
	 * @return An instance of an iterator which can be used for any SortedDoubleLinkedList
	 */
	@Override
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Remove function which allows you to remove any element given the data.
	 * @param targetData Data which is to be deleted
	 * @param comparator Comparator to compare each linked list element to the targetData
	 * @return A node object that contains the targetData
	 */
	@Override
	public Node<T> remove(T targetData, Comparator<T> comparator) {
		return super.remove(targetData, comparator);
	}

	/**
	 * Unsupported function
	 */
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException{
    	throw new UnsupportedOperationException();
    }

	/**
	 * Unsupported function
	 */
	@Override
    public void addToFront(T data) throws UnsupportedOperationException{
    	throw new UnsupportedOperationException();
    }
	
	
	
	

}