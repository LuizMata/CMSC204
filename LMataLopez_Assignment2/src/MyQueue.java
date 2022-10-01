import javax.lang.model.type.ArrayType;
import java.io.ObjectStreamException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public MyQueue(int size){
        integrityOK = false;
        counter =0;
        T[] tempQueue = (T[])new Object[size+1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = size;
        integrityOK = true;
    }
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return frontIndex == ((backIndex+1)%queue.length);
    }

    @Override
    public boolean isFull() {
        checkIntegrity();
        return frontIndex== ((backIndex+2)%queue.length);
    }

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

    @Override
    public int size() {
        checkIntegrity();
        return counter;
    }

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

    @Override
    public void fill(ArrayList list) throws QueueOverflowException {
        ArrayList<T> temp = list;
        for(T item:temp){
            enqueue(item);
        }
    }

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
    private void checkCapacity(int num){
        if(num > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a queue whose " +
                    "capacity exeeds allowed " +
                    "maximum of " + MAX_CAPACITY);
        }
    }

    // Throws an exception if this object is not initialized.
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("MyQueue object is corrupt.");
    }


}
