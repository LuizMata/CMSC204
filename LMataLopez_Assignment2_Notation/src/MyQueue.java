import javax.lang.model.type.ArrayType;
import java.io.ObjectStreamException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A generic queue ADT that will hold data and perform basic operations on that data.
 * @author Luiz
 *
 * @param <T> Generic for the provided data type
 */
public class MyQueue<T> implements QueueInterface<T>{
    private int size;
    private int frontIndex;
    private int backIndex;
    private boolean integrityOK;
    private T[] queue;
    private int counter;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;


    public MyQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor with a provided size
     * @param size custom size of the queue
     */
    public MyQueue(int size){
        integrityOK = false;
        counter =0;
        T[] tempQueue = (T[])new Object[size+1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = size;
        integrityOK = true;
    }
    
    /**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return frontIndex == ((backIndex+1)%queue.length);
    }

    /**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
    @Override
    public boolean isFull() {
        checkIntegrity();
        return frontIndex== ((backIndex+2)%queue.length);
    }

    /**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
    @Override
    public T dequeue() throws QueueUnderflowException {
        checkIntegrity();
        if(isEmpty()){
            throw new QueueUnderflowException();
        }
        else{
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex+1)% queue.length;
            counter--;
            return front;
        }
    }

    /**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
    @Override
    public int size() {
        checkIntegrity();
        return counter;
    }

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        checkIntegrity();
        if(isFull()){
            throw new QueueOverflowException();
        }
        backIndex = (backIndex+1)% queue.length;
        queue[backIndex] = e;
        counter++;
        return true;
    }

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for(T item:queue){
            if(item != null) {
                sb.append(item);
            }
        }
        return sb.toString();
    }
    
    /**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder("");
        for(T item:queue){
            if(item != null) {
                sb.append(item+delimiter);
            }
        }
        return sb.substring(0,sb.length()-1);
    }

    /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	  */
    @Override
    public void fill(ArrayList list) {
        ArrayList<T> temp = new ArrayList<>(list.size());
        for(int index = 0; index < list.size(); index++ ) {
        	temp.add((T) list.get(index));
        }
     
        for(T item:temp){
            try {
				enqueue(item);
			} catch (QueueOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    /**
     * 
     * Makes sure that the queue does not overflow, in the case that it fills then the 
     * function will expand its size x2
     */
    private void ensureCapacity(){
        if(frontIndex == ((backIndex+2)% queue.length)){
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize-1);
            integrityOK = false;
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for(int i = 0; i < oldSize-1; i++){
                queue[i] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1)%oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize - 2;
            integrityOK = true;
        }
    }
    /**
     * This will check to make sure the selected size of the queue is not too big
     * @param num the capacity/size of a queue
     */
    private void checkCapacity(int num){
        if(num > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a queue whose " +
                    "capacity exeeds allowed " +
                    "maximum of " + MAX_CAPACITY);
        }
    }

    /**
     * Throws an exception if the queue is not initialized.
     */
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("MyQueue object is corrupt.");
    }


}