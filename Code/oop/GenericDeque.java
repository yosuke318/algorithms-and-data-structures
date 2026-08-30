interface Queue<E> {  
    public abstract E peekLast();//リストの最後の要素を返します。
    public abstract E pop();//リストの最後の要素を削除し、削除した要素を返します。
    public abstract void push(E element);//リストの最後に要素を追加します。
}

interface Stack<E> {}
interface Deque<E> extends Stack<E>, Queue<E> {}