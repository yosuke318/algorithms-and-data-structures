import java.lang.StringBuilder;

// Nodeクラスは任意のオブジェクトをデータとして保持します
class Node{
    Object data;
    Node next;

    // オブジェクトをデータとして持つノードを作成します
    public Node(Object data){
        this.data = data;
    }
}

// 汎用なスタッククラス
class Stack{
    Node head;

    // スタックに新しい要素を追加します
    public void push(Object data){
        Node temp = this.head;
        this.head = new Node(data);
        this.head.next = temp;
    }

    // スタックの先頭の要素を取り出し、その要素をスタックから削除します
    public Object pop(){
        if(this.head == null) return null;

        Node temp = this.head;
        this.head = this.head.next;
        return temp.data;
    }

    // スタックの先頭の要素を取り出しますが、削除はしません
    public Object peek(){
        if(this.head == null) return null;
        return this.head.data;
    }

    // スタックの内容を文字列として出力します
    public String toString(){
        if(this.head == null) return "null";
        StringBuilder str = new StringBuilder("|" + this.head.data + "|");

        Node iterator = this.head.next;
        while(iterator != null && iterator.next != null){
            str.append(iterator.data + ",");
            iterator = iterator.next;
        }
        
        str.append(iterator.data);
        return  str.toString() + "]";
    }
}

class Main{
    public static void main(String[] args){
        // IntegerStackとして使用
        Stack stackInt = new Stack();
        System.out.println(stackInt);
        stackInt.push(3);
        stackInt.push(23);
        stackInt.push(425);
        stackInt.push(94);
        stackInt.push(2);
        stackInt.push(14);
        System.out.println(stackInt);
        stackInt.pop();
        stackInt.pop();
        stackInt.push(45);
        System.out.println(stackInt);

        // DoubleStackとして使用
        Stack stackDouble = new Stack();
        System.out.println(stackDouble);
        stackDouble.push(3.123);
        stackDouble.push(23.5984);
        stackDouble.push(42.515);
        stackDouble.push(9.5154);
        stackDouble.push(2.9941356);
        stackDouble.push(0.00414);
        System.out.println(stackDouble);
        stackDouble.pop();
        stackDouble.pop();
        stackDouble.push(45.0);
        System.out.println(stackDouble);
    }
}