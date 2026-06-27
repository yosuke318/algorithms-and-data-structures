import java.text.SimpleDateFormat;
import java.util.Date;

// abstractキーワードを用いて抽象クラスを宣言します。
abstract class Shape2D{
    protected double scale = 1;
    protected String borderColor = "black";
    protected String backgroundColor = "white";
    protected Date createdTime;

    // コンストラクタ：オブジェクト生成時に現在時刻を記録します。
    public Shape2D(){
        this.createdTime = new java.util.Date();;
    }

    // ゲッターとセッター：オブジェクトのプロパティ（状態）を取得、変更します。
    public double getScale(){
        return this.scale;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    public String getBorderColor(){
        return this.borderColor;
    }

    public void setBorderColor(String color){
        this.borderColor = color;
    }

    public String getBackgroundColor(){
        return this.backgroundColor;
    }

    public void setBackgroundColor(String color){
        this.backgroundColor = color;
    }

    // オブジェクトの作成日時を取得します。
    public String getDateCreated(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.createdTime);
    }

    // 抽象メソッド：サブクラスで実装を行う必要があります。
    public abstract String getDescription();
    public abstract double getArea();
    public abstract double getPerimeter();

    // toStringメソッド：オブジェクトの説明と作成日時を文字列として返します。
    public String toString(){
        return this.getDescription() + " created at " + this.getDateCreated();
    }
}

// サブクラス：Square、Rectangle、Circle。抽象クラスShape2Dから継承します。
class Square extends Shape2D{
    protected double l;

    public Square(double l){
        super();
        this.l = l;
    }

    // Shape2Dから継承した抽象メソッドを具体的に実装します。
    public String getDescription(){
        return "This is a square! It contains the length of one side, and all sides are equal.";
    }

    public double getArea(){
        return this.l*this.l;
    }

    public double getPerimeter(){
        return this.l*4;
    }
}

class Rectangle extends Shape2D{
    protected double l;
    protected double h;

    public Rectangle(double l, double h){
        super();
        this.l = l;
        this.h = h;
    }

    // Shape2Dから継承した抽象メソッドを具体的に実装します。
    public String getDescription(){
        return "This is a rectangle! It contains the length and height of a rectangle.";
    }

    public double getArea(){
        return this.l*this.h;
    }

    public double getPerimeter(){
        return 2 * (this.h + this.l);
    }
}

class Circle extends Shape2D{
    protected double r;

    public Circle(double r){
        super();
        this.r = r;
    }

    // Shape2Dから継承した抽象メソッドを具体的に実装します。
    public String getDescription(){
        return "This is a circle! It contains the radius length of the circle.";
    }

    public double getArea(){
        return Math.PI * (this.r*this.r);
    }

    public double getPerimeter(){
        return this.getCircumference();
    }

    // 円周の長さを計算します。
    public double getCircumference(){
        return 2 * Math.PI * this.r;
    }
}

// Pentagonクラスの実装はまだありません。
class Pentagon{};

class Main{
    // shapeの情報を出力するメソッド
    public static void shapePrinter(Shape2D obj){
        System.out.println(obj);
        System.out.println("More data: area- " + obj.getArea() + ", perimeter- " + obj.getPerimeter());
        System.out.println();
    }

    public static void main(String[] args){
        // Shape2Dのオブジェクトを作成します。しかし、Shape2Dは抽象クラスであるため、直接インスタンス化することはできません。
        // 代わりに、Shape2Dを継承した具体的なクラスのオブジェクトを作成し、それらをShape2D型の変数に代入します。
        // これにより、ポリモーフィズム（一つの型に対して複数の形を持たせる特性）が適用されます。
        Shape2D obj1 = new Square(4);
        Shape2D obj2 = new Rectangle(3,5);
        Shape2D obj3 = new Circle(9);

        // 各シェイプの情報を出力します。
        shapePrinter(obj1);
        shapePrinter(obj2);
        shapePrinter(obj3);
    }
}