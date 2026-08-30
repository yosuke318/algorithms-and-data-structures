import java.lang.StringBuilder;

// ジェネリック型をEとして定義します。
class Node<E>{
    E data;
    Node<E> next;

    public Node(E data){
        // このノードが持つデータを設定します。データ型はEです。
        this.data = data;
    }
}

// Stackクラスもジェネリック型Eを使用します。
// これにより、任意の型のオブジェクトをスタックに格納できます。
// この場合、プレースホルダはEであり、このクラスをインスタンス化する際にEが何を意味するかを宣言することができます。
// コンパイル時には、Eはインスタンス化時に宣言された指定されたクラスに置き換えられます。
class StackGeneric<E>{
    Node<E> head;

    // pushメソッドでは、ジェネリック型Eのデータを引数に取ります。
    // Eは、インスタンスが行われると特定のタイプに置き換えられます。
    // これにより、任意の型のデータをスタックにプッシュできます。
    public void push(E data){
        // Nodeにもジェネリック型を使用します。クラス名で宣言したEプレースホルダを使用します。
        Node<E> temp = this.head;
        this.head = new Node<E>(data);
        this.head.next = temp;
    }

    public E pop(){
        if(this.head == null) return null;

        Node<E> temp = this.head;
        this.head = this.head.next;
        // ポップしたノードのデータを返します。このデータの型はEです。
        return temp.data;
    }

    public E peek(){
        if(this.head == null) return null;
        // スタックのトップのデータを返します。このデータの型はEです。
        return this.head.data;
    }

    public String toString(){
        if(this.head == null) return "null";
        StringBuilder str = new StringBuilder("|" + this.head.data + "|");

        Node<E> iterator = this.head.next;
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
        // Integerのスタックを作成します。
        // ここで、ジェネリック型EはIntegerになります。
        StackGeneric<Integer> stack = new StackGeneric<Integer>();
        System.out.println(stack);
        stack.push(3);
        stack.push(23);
        stack.push(425);
        stack.push(2456);
        stack.push(14);
        stack.push(455);
        System.out.println(stack);

        // 2つの整数をポップし、それらを掛け合わせます。結果はIntegerです。
        System.out.println(stack.pop() * stack.pop());
        System.out.println(stack);

        // Doubleのスタックを作成します。
        // ここで、ジェネリック型EはDoubleになります。
        StackGeneric<Double> stackDouble = new StackGeneric<Double>();
        System.out.println(stackDouble);
        stackDouble.push(3.123);
        stackDouble.push(23.5984);
        stackDouble.push(42.515);
        stackDouble.push(9.5154);
        stackDouble.push(2.9941356);
        stackDouble.push(1.00414);
        System.out.println(stackDouble);
        
        // 2つのDoubleをポップし、それらを掛け合わせます。結果はDoubleです。
        System.out.println(stackDouble.pop() * stackDouble.pop());
        System.out.println(stackDouble);

        // 次に、文字型のスタック、Catのスタックを作成してください。
        StackGeneric<String> cat = new StackGeneric<String>();
        cat.push("mantican");
        cat.push("american short hair");
        cat.push("rossian blue");

        System.out.println(cat.pop());
        System.out.println(cat.pop());
        System.out.println(cat.pop());
    }
}