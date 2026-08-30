// ============================================================================
// 【1】インターフェース層 ―「何ができるか」だけを宣言する
// ============================================================================
// interface には状態(フィールド)を持たせない。「契約」だけを書く。
// 重要: Stack と Queue のメソッド名は絶対に衝突させないこと。
// もし両方に E pop() を書くと、Java はシグネチャが同一なので「同じメソッド」
// として黙って1本に統合してしまう。コンパイルエラーが出ないぶん危険。
// (頭から消すのか末尾から消すのか区別できなくなる)

/** 頭(先頭)側だけを触る操作の契約。LIFO。 */
interface Stack<E> {
    E peekFirst();        // 先頭の要素を見るだけ。削除しない。
    E popFirst();         // 先頭を削除し、削除した要素を返す。
    void pushFirst(E element); // 先頭に追加する。
}

/** 尻尾(末尾)側だけを触る操作の契約。pushLast + popFirst で FIFO になる。 */
interface Queue<E> {
    E peekLast();         // 末尾の要素を見るだけ。削除しない。
    E popLast();          // 末尾を削除し、削除した要素を返す。
    void pushLast(E element);  // 末尾に追加する。
}

/**
 * Deque = Double Ended Queue。両端を触れる。
 * interface の extends は「複数継承」できる。クラスと違って状態を持たないので、
 * ダイヤモンド継承で状態が二重に生まれる問題が起きないため。
 */
interface Deque<E> extends Stack<E>, Queue<E> {
    int size();
    boolean isEmpty();
}

// ============================================================================
// 【2】抽象クラス層 ―「サブクラスの部品だけで書ける共通処理」を1回だけ書く
// ============================================================================
/**
 * abstract class にする理由:
 *   - interface だけだと isEmpty() や toString() を LinkedList と ArrayList で
 *     まったく同じ内容で2回書くハメになる。その重複をここに集約する。
 *   - new AbstractList<>() されても中身が無いので意味がない → abstract を付けて
 *     インスタンス化を禁止する。
 *
 * 【最重要】ここに Node(prev/next) を置いてはいけない。
 *   Node は LinkedList だけの実装詳細。配列ベースの ArrayList は Node を使わない。
 *   親に置くと ArrayList が使わないフィールドを継承させられて無駄になる。
 *   親に置いていいのは「実装方法に依存しない処理」だけ。
 */
abstract class AbstractList<E> implements Deque<E> {

    /**
     * サブクラスに実装を義務付ける「部品」。
     * size() (Deque 由来) と この get() の2つさえ揃えば、
     * 下の isEmpty / contains / toString は実装方法を知らなくても書ける。
     * これが抽象クラスの本質: 「共通処理を、未実装の部品の上に組み立てる」。
     */
    protected abstract E get(int index);

    /** size() がどう実装されていようが、空判定は必ずこう書ける。 */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /** get() と size() だけで書ける。LinkedList/ArrayList のどちらでもそのまま動く。 */
    public boolean contains(E target) {
        for (int i = 0; i < size(); i++) {
            E element = get(i);
            if (element == null ? target == null : element.equals(target)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i > 0) str.append(", ");
            str.append(get(i));
        }
        return str.append("]").toString();
    }
}

// ============================================================================
// 【3】具象クラス層 ― 実際のデータの持ち方を決める
// ============================================================================
/**
 * 連結リストによる実装。双方向(prev/next)なので両端 O(1)。
 * 代わりに get(index) は先頭から辿るので O(n)。
 */
class LinkedList<E> extends AbstractList<E> {

    /**
     * static ネストクラスは外側の型変数 E を参照できない
     * (「staticでない型変数Eをstaticコンテキストから参照することはできません」)。
     * → 自前の型変数 T を持たせて解決する。
     *   static のままなので外側インスタンスへの隠れ参照を持たず、その分だけ軽い。
     *   (static を外して private class Node にすれば E をそのまま使えるが、
     *    ノード1個ごとに外側への参照を抱えることになる)
     */
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void pushFirst(E element) {
        Node<E> node = new Node<>(element);

        if (head == null) {
            // 空リスト: 唯一のノードが head でも tail でもある。
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void pushLast(E element) {
        Node<E> node = new Node<>(element);

        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public E popFirst() {
        if (head == null) return null;

        Node<E> removed = head;
        head = head.next;

        if (head == null) {
            tail = null;   // 最後の1個を取り除いた → tail も消す(参照を残すとリークする)
        } else {
            head.prev = null;
        }
        size--;
        return removed.data;
    }

    @Override
    public E popLast() {
        if (tail == null) return null;

        Node<E> removed = tail;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return removed.data;
    }

    @Override
    public E peekFirst() {
        return head == null ? null : head.data;
    }

    @Override
    public E peekLast() {
        return tail == null ? null : tail.data;
    }

    @Override
    public int size() {
        return size;
    }

    /** 連結リストなので添字アクセスは先頭から辿るしかない → O(n)。 */
    @Override
    protected E get(int index) {
        Node<E> iterator = head;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }
        return iterator.data;
    }
}

/**
 * 可変長配列による実装。Node は一切使わない。
 * これが「Node を親に置いてはいけない」ことの証明になっている。
 */
class ArrayList<E> extends AbstractList<E> {

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    ArrayList() {
        // ジェネリック配列は new E[10] と書けない(型消去のため)。
        // Object[] を作ってキャストするのが定石。
        this.elements = (E[]) new Object[10];
    }

    /** 満杯になったら容量を2倍にして詰め替える。 */
    @SuppressWarnings("unchecked")
    private void grow() {
        if (size < elements.length) return;

        E[] larger = (E[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, larger, 0, size);
        elements = larger;
    }

    /** 末尾追加は詰め替え不要なので O(1)。配列の得意技。 */
    @Override
    public void pushLast(E element) {
        grow();
        elements[size++] = element;
    }

    /** 先頭追加は全要素を1個ずつ後ろにずらす必要があり O(n)。配列の弱点。 */
    @Override
    public void pushFirst(E element) {
        grow();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[0] = element;
        size++;
    }

    @Override
    public E popLast() {
        if (size == 0) return null;

        E removed = elements[--size];
        elements[size] = null;  // 参照を切らないと GC されない
        return removed;
    }

    @Override
    public E popFirst() {
        if (size == 0) return null;

        E removed = elements[0];
        System.arraycopy(elements, 1, elements, 0, size - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public E peekFirst() {
        return size == 0 ? null : elements[0];
    }

    @Override
    public E peekLast() {
        return size == 0 ? null : elements[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    /** 配列なので添字アクセスは一発 → O(1)。LinkedList との決定的な差。 */
    @Override
    protected E get(int index) {
        return elements[index];
    }
}

// ============================================================================
// 【4】動作確認
// ============================================================================
class Main {
    public static void main(String[] args) {
        // 引数の型を Deque<String> にしておくと、実装が LinkedList でも ArrayList でも
        // 同じコードが動く。これがインターフェースを切る最大の見返り。
        System.out.println("--- LinkedList ---");
        exercise(new LinkedList<String>());

        System.out.println("--- ArrayList ---");
        exercise(new ArrayList<String>());

        // 抽象クラスに1回書いただけの contains/toString が両方で使える。
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.pushLast(10);
        numbers.pushLast(20);
        numbers.pushFirst(5);
        System.out.println("numbers   = " + numbers);
        System.out.println("contains 20 -> " + numbers.contains(20));
        System.out.println("contains 99 -> " + numbers.contains(99));

        // AbstractList list = new AbstractList(); // ← abstract なのでコンパイルエラー
    }

    private static void exercise(Deque<String> deque) {
        System.out.println("初期状態 isEmpty : " + deque.isEmpty());

        deque.pushLast("B");
        deque.pushLast("C");
        deque.pushFirst("A");
        System.out.println("A,B,C 投入後     : " + deque + " size=" + deque.size());

        System.out.println("peekFirst        : " + deque.peekFirst()); // A
        System.out.println("peekLast         : " + deque.peekLast());  // C
        System.out.println("popFirst         : " + deque.popFirst());  // A
        System.out.println("popLast          : " + deque.popLast());   // C
        System.out.println("残り             : " + deque + " size=" + deque.size());

        System.out.println("popFirst         : " + deque.popFirst());  // B
        System.out.println("空に対する popFirst: " + deque.popFirst()); // null
        System.out.println("最終 isEmpty     : " + deque.isEmpty());
        System.out.println();
    }
}
