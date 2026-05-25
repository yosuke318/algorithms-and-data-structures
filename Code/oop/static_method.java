// 'MathThings'クラスを定義します。このクラスには状態を保持するインスタンスフィールドがなく、
// すべてのメソッドが静的（static）であり、インスタンスフィールドを持たない、もしくは定義されていても変更できないため、ステートレスと言えます。
// インスタンスを生成した場合返されるオブジェクトは状態を持たないため、ステートレスオブジェクトと呼びます。
// またメソッドはstaticであるため、MathThingsの具体的なインスタンスを作成せずとも、そのメソッドをいつでも利用できます。
class MathThings {
    // 'public static final'修飾子を用いて円周率の近似値を定義します。
    // 'final'修飾子はこの値が定数であり、一度定義すると変更できないことを意味します。
    // 'static'修飾子はこの値がクラスレベルで存在し、MathThingsクラスのどこからでもアクセス可能であることを意味します。
    public static final double PIAPPROX = 3.14159265359;

    // 円の表面積を計算する静的メソッドを定義します。
    // このメソッドは引数として半径の値を受け取り、その半径の円の表面積を計算して返します。
    // 'static'修飾子があるため、このメソッドはアプリケーションのライフタイム全体でどこからでもアクセスできます。
    public static double circleSurfaceArea (int x)  {
        return PIAPPROX * x * x;
    }

    // 箱の体積を計算する静的メソッドを定義します。
    // このメソッドは引数として一辺の長さを受け取り、その長さの立方体の体積を計算して返します。
    public static double boxVolume(double x){
        return x*x*x;
    }
}

class Main{
    public static void main(String[] args){
        // MathThingsクラスの静的メンバである'PIAPPROX'を出力します。
        System.out.println(MathThings.PIAPPROX);

        System.out.println(MathThings.boxVolume(2));
        System.out.println(MathThings.circleSurfaceArea(4));
        System.out.println(MathThings.boxVolume(5));


        System.out.println("------------------------------------");
        // 状態を持たないobj作成します。
        MathThings obj = new MathThings();
        System.out.println(obj.PIAPPROX);
        System.out.println(obj.boxVolume(2));
        System.out.println(obj.circleSurfaceArea(4));
        System.out.println(obj.boxVolume(5));
    }
}