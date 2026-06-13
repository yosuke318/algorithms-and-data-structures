public class MutableString {
    // ここから開発しましょう

    private char[] buffer;
    private int size;

    public MutableString() {
        this.buffer = new char[16];
        this.size = 0;
    }

    public MutableString(char[] chars) {
        this.buffer = new char[chars.length * 2];
        for (int i = 0; i < chars.length; i++){
            this.buffer[i] = chars[i];
        }
        this.size = chars.length;
    }

    public MutableString(String s) {
        this(s.toCharArray());
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > this.buffer.length) {
            int newCapacity = Math.max(minCapacity, this.buffer.length * 2);
            char[] newBuffer = new char[newCapacity];
            for (int i = 0; i < this.size; i ++) {
                newBuffer[i] = this.buffer[i];
            }
            this.buffer = newBuffer;
        }
    }

    public void append(char c) {
        ensureCapacity(this.size + 1);
        this.buffer[this.size] = c;
        this.size++;
    }

    public MutableString substring(int start){
        return substring(start, this.size);
    }

    public MutableString string(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > this.size || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid index");
        }
        char[] sub = new char[endIndex - startIndex];
        for (int i = startIndex; i < endIndex; i++){
            sub[i - startIndex] = this.buffer[i];
        }
        return new MutableString(sub);
    }

    // char配列を連結する（ミューテーター）
    public void concat(char[] cArr) {
        ensureCapacity(this.size + cArr.length);
        for (int i = 0; i < cArr.length; i++) {
            this.buffer[this.size + i] = cArr[i];
        }
        this.size += cArr.length;
    }

    // Stringを連結する（ミューテーター）
    public void concat(String stringInput) {
        concat(stringInput.toCharArray());
    }

    // MutableStringを連結する（ミューテーター）
    public void concat(MutableString stringInput) {
        concat(stringInput.toCharArray());
    }

    // 文字列の長さを返す
    public int length() {
        return this.size;
    }

    private char[] toCharArray(){
        char[] result = new char[this.size];
        for (int i = 0; i < this.size; i++){
            result[i] = this.buffer[i];
        }
        return result;
    }
}


class Main {
    public static void main(String[] args) {
        MutableString ms = new MutableString("Hello");
        System.out.println("初期値: " + ms);             // Hello

        ms.append('!');
        System.out.println("append('!'): " + ms);       // Hello!

        MutableString sub = ms.substring(1, 4);
        System.out.println("substring(1,4): " + sub);  // ell

        ms.concat(new char[]{' ', 'W', 'o', 'r', 'l', 'd'});
        System.out.println("concat(char[]): " + ms);   // Hello! World

        ms.concat(" Java");
        System.out.println("concat(String): " + ms);  // Hello! World Java

        MutableString ms2 = new MutableString("!!");
        ms.concat(ms2);
        System.out.println("concat(MutableString): " + ms); // Hello! World Java!!

        System.out.println("length(): " + ms.length());     // 18
    }
}