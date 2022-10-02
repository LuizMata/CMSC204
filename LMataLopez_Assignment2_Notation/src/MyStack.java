import java.util.ArrayList;

/**
 * A generic stack ADT that will hold data and perform basic operations on that data.
 * @author Luiz
 *
 * @param <T> Generic for the provided data type
 */
public class MyStack<T> implements StackInterface<T>{

    private Node topNode;
    private int nodeCount;
    private int size;

    public <T> MyStack(int size){
        this.size = size;
    }
    public <T> MyStack(){
        size = 50;
    }
    
   
    /**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    /**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
    @Override
    public boolean isFull() {
        if (nodeCount >= size) {
            return true;
        }
        return false;
    }

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T pop() throws StackUnderflowException {
        T top = top();
        topNode = topNode.getNext();
        nodeCount--;
        return top;
    }

    /**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
           throw new StackUnderflowException();
        }
        return (T) topNode.getData();
    }

    /**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
    @Override
    public int size() {
        return nodeCount;
    }

    /**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
    @Override
    public boolean push(T e) throws StackOverflowException {
            if (isFull()) {
                throw new StackOverflowException();
            }
            Node newNode = new Node(e, topNode);
            nodeCount++;
            topNode = newNode;
        
        return true;
    }

    /**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
    @Override
    public String toString() {
        Node<T> temp = topNode;
        StringBuilder sb = new StringBuilder();
        if(isEmpty()){
            return "";
        }
        while(temp!=null){
            sb.append(temp.getData());
            temp = temp.getNext();
        }
        sb.reverse();
        return sb.toString();
    }
    
    /**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
    @Override
    public String toString(String delimiter) {
        Node<T> temp = topNode;
        StringBuilder sb = new StringBuilder("");
        if(isEmpty()){
            return "";
        }
        while(temp!=null){
            if(temp.getNext()==null){
                sb.append(temp.getData());
                temp = temp.getNext();
            }
            else {
                sb.append(temp.getData() + delimiter);
                temp = temp.getNext();
            }
        }
        sb.reverse();
        return sb.toString();
    }

    /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
    @Override
    public void fill(ArrayList list) {
    	 ArrayList<T> a = new ArrayList<>(list.size());
         for(int index = 0; index < list.size(); index++ ) {
         	a.add((T) list.get(index));
         }
      
        clear();
        for(int i = 0; i < a.size(); i++){
            try {
				push(a.get(i));
			} catch (StackOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public void clear(){
        topNode = null;
    }
    
    /**
     * Node object used to represent nodes in a linked list
     * @author Luiz
     *
     * @param <T> Generic for the provided data type
     */
    private class Node<T> {
        private T data;
        private Node next;

        /**
         * Constructor providing data and pointer to next node in linked list
         * @param dataPortion
         * @param nextNode
         */
        private Node (T dataPortion, Node nextNode ){

            data =dataPortion;
            next = nextNode;

        }

        /**
         * Constructor used to define the first node, the pointer will be null
         * @param dataPortion
         */
        private  Node (T dataPortion){
            this(dataPortion, null);
        }

        /**
         * Function to get the next node in the linked list from pointer
         * @return The next node
         */
        private Node getNext() {
            return next;
        }

        /**
         * Function to set the next node in the linked list
         * @param nextNode A reference node used to set the next node
         */
        private void setNext(Node nextNode){
            next = nextNode;
        }

        /**
         * Function to get the actual data value of a linked list node
         * @return The data portion of the node
         */
        private T getData() {
            return data;
        }

        /**
         * Function used to set or change the data in a linked list node
         * @param newData New data provided to change preexisting node data
         */
        private void setData(T newData){
            this.data = newData;
        }

    }

   
}