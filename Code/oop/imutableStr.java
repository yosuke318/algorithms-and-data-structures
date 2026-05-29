class Main{
    // 文字配列を表示するヘルパーメソッド
    public static void printChars(char[] charArr){
        System.out.print("Printing character array: ");
        for(int i = 0; i < charArr.length; i++) System.out.print(charArr[i]);
        System.out.println();
    }

    public static void main(String[] args){
        // 文字配列の初期化
        char[] str1Chars = new char[]{'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', '!'};
        // 文字配列の表示
        printChars(str1Chars);
        
        // 文字配列の内容を変更します（可変性）
        str1Chars[6] = 'E';  
        str1Chars[7] = 'a';
        str1Chars[8] = 'r';
        str1Chars[9] = 't';
        str1Chars[10] = 'h';
        // 再度、文字配列を表示
        printChars(str1Chars);

        // 文字配列から文字列を生成します。
        String s1 = new String(str1Chars);

        // Stringオブジェクトの状態を変更することはできません（不変性）
        // 以下のコードはエラーを引き起こします
        // s1[3] = 'e';
        
        // 文字列の比較
        System.out.println();
        System.out.println("Comparing strings....");
        String s2 = new String(str1Chars);
        String s3 = "Hello World!";
        String s4 = "Hello World!";
        String s5 = new String("Hello World!");

        // Stringオブジェクトのハッシュコードを表示します。同じ文字列でも生成方法により異なることがあります。
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        System.out.println(System.identityHashCode((s1+s2))); // 新しい文字列が生成されるためハッシュコードが変わる
        System.out.println(System.identityHashCode(s3));

        // '=='は参照比較を行うのに対し、equalsメソッドは内容の比較を行う
        System.out.println(s1 == s2); // false
        System.out.println(s1.equals(s2)); // true
        System.out.println(s3 == s4); // true
        System.out.println(s4 == s5); // false
        System.out.println(s5 == s5); // true

        System.out.println();
        System.out.println("Doing operations....");

        // 文字列の反転 O(n^2)
        // 文字列は不変なので、+= 操作は毎回新しいStringオブジェクトを生成します
        String reverseS = "";
        for(int i = s5.length()-1; i >= 0; i--){
            reverseS += s5.charAt(i);  // O(n)の操作
        }
        System.out.println(s5);
        System.out.println(reverseS);

        // 文字配列を使うことで効率的に処理を行うことができます
        // 文字列の反転 O(n)
        char[] reverseChar = new char[s5.length()];
        int l = s5.length()-1;
        for(int i = l; i >= 0; i--){
            reverseChar[l-i] = s5.charAt(i);  // O(1)の操作
        }

        // 最後に文字列に変換
        String reverseS2 = new String(reverseChar);
        System.out.println(reverseS2);
        System.out.println(reverseS2.equals(reverseS)); // true
    }
}