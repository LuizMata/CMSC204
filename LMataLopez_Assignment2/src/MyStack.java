import java.util.ArrayList;

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
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public boolean isFull() {
        if (nodeCount >= size) {
            return true;
        }
        return false;
    }

    @Override
    public T pop() throws StackUnderflowException {
        T top = top();
        topNode = topNode.getNext();
        nodeCount--;
        return top;
    }

    @Override
    public T top() throws StackUnderflowException {
        try {
            if (isEmpty()) {
                throw new StackUnderflowException();
            }
        }
        catch (StackUnderflowException exception) {
            exception.printStackTrace();
        }
        return (T) topNode.getData();
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        try {
            if (isFull()) {
                throw new StackOverflowException();
            }
            Node newNode = new Node(e, topNode);
            nodeCount++;
            topNode = newNode;
        }
        catch(StackOverflowException exception){
            exception.printStackTrace();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return true;
    }

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

    @Override
    public void fill(ArrayList list) throws StackOverflowException {
        ArrayList<T> a = new ArrayList<T>(list);
        clear();
        for(int i = 0; i < a.size(); i++){
            push(a.get(i));
        }
    }
    public void clear(){
        topNode = null;
    }
    private class Node<T> {
        private T data;
        private Node next;

        private Node (T dataPortion, Node nextNode ){

            data =dataPortion;
            next = nextNode;

        }

        private  Node (T dataPortion){
            this(dataPortion, null);
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node nextNode){
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData){
            this.data = newData;
        }

    }

}
