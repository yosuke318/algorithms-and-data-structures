
interface AbstractListInteger {
    void push(Integer item);
}

interface StackInt extends AbstractListInteger {
    Integer pop();
    Integer peekLast();
}

interface QueueInt extends AbstractListInteger {
    Integer poll();
    Integer peekFirst();
}

interface DequeInt extends QueueInt, StackInt {
    int size();
    boolean isEmpty();
}

class IntegerLinkedList implements DequeInt {
    private static class Node {
        Integer data;
        Node prev;
        Node next;

        Node(Integer data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public IntegerLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void push(Integer data){
        Node newNode = new Node(data);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public Integer pop(){
        if(this.tail == null) return null;

        Integer value = this.tail.data;
        this.tail = this.tail.prev;
        if(this.tail == null){
            this.head = null;
        } else {
            this.tail.next = null;
        }
        this.size--;
        return value;
    }

    @Override
    public Integer peekLast(){
        if(this.tail == null) return null;
        return this.tail.data;
    }

    @Override
    public Integer poll(){
        if(this.head == null) return null;

        Integer value = this.head.data;
        this.head = this.head.next;
        if(this.head == null){
            this.tail = null;
        } else {
            this.head.prev = null;
        }
        this.size--;
        return value;
    }

    @Override
    public Integer peekFirst(){
        if(this.head == null) return null;
        return this.head.data;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean isEmpty(){
        return this.size == 0;
    }

}

