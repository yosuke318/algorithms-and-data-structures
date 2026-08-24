import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class AbstractListInteger{
    protected int[] initialList;

    public AbstractListInteger(){
        this.initialList = new int[0];
    }

    public AbstractListInteger(int[] arr){
        this.initialList = arr;
    }

    public int[] getOriginalList(){
        return initialList;
    }
    
    public abstract int get(int position);
    public abstract void add(int element); 
    public abstract void add(int[] elements);
    public abstract int pop();
    public abstract void addAt(int position, int element);
    public abstract void addAt(int position, int[] elements);
    public abstract int removeAt(int position);
    public abstract void removeAllAt(int start);
    public abstract void removeAllAt(int start, int end);
    public abstract AbstractListInteger subList(int start); 
    public abstract AbstractListInteger subList(int start, int end);
}

// 以下のクラスを実装します。IntegerArrayListは動的配列、IntegerLinkedListは連結リストです。
// 異なる入力タイプを可能にするために、これらのクラスに必要なだけ状態や挙動、
// オーバーロードされた関数を追加することができます。

// TODO: 残りの抽象メソッド（get / pop / addAt / removeAt / removeAllAt / subList）を実装したら
//       abstract を外して具象クラスにする。
abstract class IntegerArrayList extends AbstractListInteger{

    public IntegerArrayList(){
        super();
    }

    public IntegerArrayList(int[] arr){
        super(arr);
    }

    @Override
    public void add(int element){
        int[] newList = new int[initialList.length + 1];
        System.arraycopy(initialList, 0, newList, 0, initialList.length);

        newList[initialList.length] = element;
        this.initialList = newList;
    }

    @Override
    public void add(int[] elements){
        int[] newList = new int[initialList.length + elements.length];
        System.arraycopy(initialList, 0, newList, 0, initialList.length);
        System.arraycopy(elements, 0, newList, initialList.length, elements.length);
        this.initialList = newList;
    }
}
// class IntegerLinkedList extends AbstractListInteger{}

class Main{
    public static void main(String[] args){

    }
}