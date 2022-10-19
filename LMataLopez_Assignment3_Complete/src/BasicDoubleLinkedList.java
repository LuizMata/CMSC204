import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * A double linked list data structure that allows users to add from both ends
 * @author Luiz
 * @param <T> Linked List Data type
 */
public class BasicDoubleLinkedList <T> implements Iterable<T> {
    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    /**
     * Basic BasicDoubleLinkedList constructor which instantiates a linked list of size 0
     */
    public BasicDoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Add function which allows for linked list node addition to the end/tail side
     * @param data Data input which will be stored in a linked list node
     */
    public void addToEnd(T data){
    	if (tail == null) {
			tail = new Node<T>(data, null, null);
			head = tail;

		} else {

			Node<T> nextTail = new Node<T>(data, tail, null);
			tail.setNext(nextTail);
			tail = nextTail;
		}

		size++;
    }
    
    /**
     * Add function which allows for linked list node addition to the front/head side
     * @param data Data input which will be stored in a linked list node
     */
    public void addToFront(T data){
    	if (head == null) {
			head = new Node<T>(data, null, null);
			tail = head;
		} else {
			Node<T> nextHead = new Node<T>(data, null, head);
			head.setPrev(nextHead);
			head = nextHead;
		}
		size++;
    }

    /**
     * Gets the first item in the linked list
     * @return Data contained within the first linked list node
     */
    public T getFirst(){
    	if(head == null) {
    		return null;
    	}
        return head.getData();
    }

    /**
     * Gets the last item in the linked list
     * @return Data contained within the last linked list node
     */
    public T getLast(){
    	if(tail == null) {
    		return null;
    	}
        return tail.getData();
    }
    public int getSize(){
        return size;
    }
    
    /**
	 * Iterator which allows simple traversal of the SortedDoubleLinkedList
	 * @return An instance of an iterator which can be used for any BasicDoubleLinkedList
	 * @throws UnsupportedOperationException if an unsupported function is used
	 * @throws NoSuchElementException when there is no elements left for traversal
	 */
    @Override
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        return new DoubleLinkedListIterator();
    }

    /**
	 * Remove function which allows you to remove any element given the data.
	 * @param targetData Data which is to be deleted
	 * @param comparator Comparator to compare each linked list element to the targetData
	 * @return A node object that contains the targetData
	 */
    public Node<T> remove(T targetData, Comparator<T> comparator) {
		Node<T> current = head;
		
		if(size == 0) {
			return null;
		}
		Node<T> result = null;
		if(size ==1) {
			if(comparator.compare(targetData, head.getData())==0) {
				result = head;
				head = null;
				tail = null;
				size--;
			}
			return result;
		}
		
		while(current != null) {
			if(comparator.compare(targetData, current.getData())==0) {
				if(current.equals(head)) {
					head = current.getNext();
					current.getNext().setPrev(null);
				} else if(current.equals(tail)) {
					tail = current.getPrev();
					current.getPrev().setNext(null); 
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());				
				}
				
				size--;
				break;
			}
			current=current.getNext();
		}
		return null;
	}

    /**
     * Retrieval function which returns data from the first node and deletes it
     * @return Data contained within the first linked list node
     */
    public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		T t = head.getData();

		if (size == 1) {
			head = null;
			tail = null;
			return t;
		}

		head = head.getNext();
		head.setPrev(null);
		size--;
		return t;
	}

    /**
     * Retrieval function which returns data from the last node and deletes it
     * @return Data contained within the last linked list node
     */
    public T retrieveLastElement(){
    	if(tail == null) {
    		return null;
    	}
    	T data = tail.getData();
    	if(head == tail) {
    		head = null;
    		tail = null;
    		return data;
    	}
     
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
        return data;
    }

    /**
     * Basic toArrayList function which turns the linked list into an ArrayList
     * @return ArrayList containing the items from the linked list
     */
    public ArrayList<T> toArrayList(){
    	ArrayList<T> arr = new ArrayList<T>();
    	DoubleLinkedListIterator it = (BasicDoubleLinkedList<T>.DoubleLinkedListIterator) iterator();
		
		while(it.hasNext()) {
			arr.add(it.next());	
		}	
		return arr;
    }


    /**
     * Node to represent a link in a linked list
     * @author Luiz
     *
     * @param <T> type of the link in the linked list
     */
    protected class Node<T>{
        private T data;
        private Node<T> prev;
        private  Node<T> next;

        /**
         * Constructor which takes in the data, and previous/next pointers.
         * @param data User data to be contained in the node
         * @param prev previous node pointer
         * @param next next node pointer
         */
        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        /**
         * function to get the next node
         * @return next node from any given node
         */
        public Node<T> getNext(){
            return next;
        }
        /**
         * function to set the next node
         * @param next Node which will become next
         */
        public void setNext(Node<T> next){
            this.next = next;
        }

        /**
         * function to get the previous node
         * @return previous node from any given node
         */
        public Node<T> getPrev(){
            return prev;
        }
        
        /**
         * function to set the previous node
         * @param prev Node which will become previous
         */
        public void setPrev(Node<T> prev){
            this.prev = prev;
        }
       
        /**
         * function to get the data contained in the node object
         * @return Data of generic type
         */
        public T getData(){
            return data;
        }
        
        /**
         * function to determine if two nodes are equal
         * @param node node to compare to
         * @return true/false whether or not they match
         */
        public boolean equals(Node<T> node) {
			if(next == node.getNext() && prev == node.getPrev() && data == node.getData()) {
				return true;
			}
			return false;
		}

    }
    
    /**
     * Iterator for the DoubleLinkedList
     * @author Luiz
     *
     */
    protected class DoubleLinkedListIterator  implements ListIterator<T> {
    	protected Node<T> nextNode;
    	protected Node<T> it;
    	
    	/**
    	 * Default constructor for the DoubleLinkedListIterator
    	 */
    	protected DoubleLinkedListIterator() {
    		nextNode = head;
    	}

    	/**
    	 * Determines if there is a next node
    	 * @return A true or false value depending on if there is a next node
    	 */
        @Override
        public boolean hasNext() {
        	return nextNode != null;
        }
        
        /**
         * Moves the iterator to the next node, precondition is that hasNext() must be true
         * @throws NoSuchElementException in the case that hasNext() is not true
         */
        @Override
        public T next() throws NoSuchElementException{
            if(hasNext()) {
            	it = nextNode;
            	nextNode = nextNode.getNext();
            	return it.getData();
    
            }
            throw new NoSuchElementException("Illegal Call to next");

        }
        
        /**
    	 * Determines if there is a previous node
    	 * @return A true or false value depending on if there is a previous node
    	 */
        @Override
        public boolean hasPrevious() {
            return it != null;
        }
        
        /**
         * Moves the iterator to the previous node, precondition is that hasPrevious() must be true
         * @throws NoSuchElementException in the case that hasPrevious() is not true
         */
        @Override
        public T previous() throws NoSuchElementException {
        	if(hasPrevious()) {
        		nextNode = it;
        		it = it.getPrev(); 
        		return nextNode.getData();
        	
        	}
        	throw new NoSuchElementException("Illegal Call to previous");
        	
        }

        /**
         * Unsupported function
         */
        @Override
        public int nextIndex() throws UnsupportedOperationException {
           throw new UnsupportedOperationException();
        }
        
        /**
         * Unsupported function
         */
        @Override
        public int previousIndex() throws UnsupportedOperationException {
        	throw new UnsupportedOperationException();
        }
        
        /**
         * Unsupported function
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
         
        }
        
        /**
         * Unsupported function
         */
        @Override
        public void set(T t) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
       
        }
        
        /**
         * Unsupported function
         */
        @Override
        public void add(T t) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
          
    }

}