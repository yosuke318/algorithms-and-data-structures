
import java.util.ArrayList;
import java.util.List;


interface Stack{
    String peekLast();
    String pop();
    void push(String item);
}

interface Queue{
    String peekFirst();
    String poll();
    void push(String item);
}

interface Deque extends Queue, Stack{
    int size();
    boolean isEmpty();
}

// インターフェースのextendsキーワードは、実装の複数継承を可能にします。
// ただし、クラスには状態が含まれているため、これは不可能であり、Javaは状態の単一継承しかサポートしていません。
// スタックとキューのすべての抽象クラスを拡張（継承）します。
class ArrayDequeImpl implements Deque {
    // deque独自のメソッド
    private final List<String> dequeList = new ArrayList<>();

    @Override
    public String peekLast(){
        if(dequeList.isEmpty()) return null;
        return dequeList.get(dequeList.size() - 1);
    }

    @Override
    public String peekFirst(){
        if(dequeList.isEmpty()) return null;
        return dequeList.get(0);
    }

    @Override
    public String pop(){
        if(dequeList.isEmpty()) return null;
        return dequeList.remove(dequeList.size()-1);
    }

    @Override
    public String poll(){
        if(dequeList.isEmpty()) return null;
        return dequeList.remove(0);

    }

    @Override
    public void push(String item){
        dequeList.add(item);
    }

    @Override
    public int size() {
        return dequeList.size();
    }

    @Override
    public boolean isEmpty() {
        return dequeList.isEmpty();
    }
}

class Main {
    public static void main(String[] args) {
        ArrayDequeImpl d = new ArrayDequeImpl();
        d.push("A");
        d.push("B");
        System.out.println(d.peekFirst()); // A
        System.out.println(d.peekLast());  // B
        System.out.println(d.pop());       // B
        System.out.println(d.poll());      // A
    }
}