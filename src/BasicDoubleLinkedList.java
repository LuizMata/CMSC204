import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class BasicDoubleLinkedList <T> implements Iterable<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public BasicDoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    public void addToEnd(T data){
        Node<T> newNode = new Node<T>(data);
        newNode.setPrev(tail);
        tail = newNode;
        if(size == 0){
            head = tail;
        }
        size++;
    }

    public void addToFront(T data){
        Node<T> newNode = new Node<T>(data);
        newNode.setNext(head);
        head = newNode;
        if(size == 0){
            tail = head;
        }
        size++;
    }

    public T getFirst(){
        return head.getData();
    }

    public T getLast(){
        return tail.getData();
    }
    public int getSize(){
        return size;
    }

    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    public Node remove(T dataTarget, Comparator<T> comparator){
        if(size == 0){
            return null;
        }
        Node currentNode = head;

        if(size == 1){
            if(comparator.compare(dataTarget, (T) currentNode.getData()) == 0){
                head = null;
                tail = null;
                size --;
                return currentNode;
            }
        }

        while(currentNode != null){
            if(comparator.compare(dataTarget, (T) currentNode.getData()) == 0){
                Node temp = currentNode;
                if(currentNode == head){
                    head = currentNode.getNext();
                    currentNode.getNext().setPrev(null);
                }
                else if(currentNode == tail){
                    tail = currentNode.getPrev();
                    currentNode.getPrev().setNext(null);
                }
                else {
                    currentNode.getPrev().setNext(currentNode.getNext());
                    currentNode.getNext().setPrev(currentNode.getPrev());
                }
                size--;
                return temp;
            }
            currentNode= currentNode.getNext();
        }

        return null;
    }

    public T retrieveFirstElement(){
        T data = head.getData();
        head = head.getNext();
        head.setPrev(null);
        return data;
    }

    public T retrieveLastElement(){
        T data = tail.getData();
        tail = tail.getPrev();
        tail.setNext(null);
        return data;
    }

    public ArrayList<T> toArrayList(){
        return null;
    }




    protected class Node <T>{
        private T data;
        private Node<T> prev;
        private  Node<T> next;

        public Node(T data){
            this.data = data;
        }

        public Node<T> getNext(){
            return next;
        }
        public void setNext(Node next){
            this.next = next;
        }

        public Node<T> getPrev(){
            return prev;
        }

        public void setPrev(Node prev){
            this.prev = prev;
        }

        public T getData(){
            return data;
        }


    }
    protected class DoubleLinkedListIterator  implements ListIterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }
        @Override
        public T next() {
            return null;
        }
        @Override
        public boolean hasPrevious() {
            return false;
        }
        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public int previousIndex() {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                throw new RuntimeException(e);
            }

        }
        @Override
        public void remove() {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void set(T t) {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void add(T t) {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
