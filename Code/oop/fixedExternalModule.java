import java.util.Calendar;

// Personというクラスを定義します。このクラスには、人の名前、身長、体重、生年などの情報を格納します。
class Person{
    // 各変数はprivateと宣言されているため、Personクラスの内部からのみアクセス可能です。
    private String firstName;
    private String lastName;
    private double heightM;
    private double weightKg;
    private int birthYear;

    // Personオブジェクトを生成するためのコンストラクタ。この中でprivate変数の初期化を行います。
    public Person(String firstName, String lastName, double heightM, double weightKg, int birthYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.birthYear = birthYear;
    }

    // publicと宣言されたメソッド。toStringはオブジェクトを文字列に変換します。
    // このメソッドを通じて内部のprivate変数の値を取得することが可能です。
    public String toString(){
        return this.getFullName() + ", heightM: " + this.heightM + ", weightKg: " + this.weightKg + ", age: " + this.getAge();
    }

    // 名前を変更するためのpublicメソッド。
    // このメソッドを通じてのみfirstNameとlastNameを変更できます。
    public void changeName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // privateメソッド。
    // このメソッドはクラスの外部からは呼び出せません。クラス内部からのみ使用できます。
    private String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    // 年齢を計算して返すpublicメソッド。
    // このメソッドを通じてbirthYearを利用し年齢を計算します。
    public int getAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.birthYear;
    }
}

class Main{
    public static void main(String[] args){
        // Personオブジェクトを作成し、その参照をcarly変数に格納します。
        Person carly = new Person("Carly", "Angelo", 1.72, 85.5, 1996);

        // PersonオブジェクトのtoStringメソッドを呼び出します。これはpublicメソッドなので、オブジェクトの外部から呼び出すことが可能です。
        System.out.println(carly);
        
        // getAgeもpublicメソッドなので、オブジェクトの外部から呼び出すことが可能です。
        System.out.println(carly.getAge());

        // 以下のコードはエラーを引き起こします。getFullNameメソッドとlastName変数はともにprivateなので、Personクラスの外部からアクセスすることはできません。
        // System.out.println(carly.getFullName());
        // carly.lastName = "Bardernson";

        // 名前を変更するpublicメソッドを呼び出します。これはオブジェクトの外部から呼び出すことが可能です。
        carly.changeName("Carly","Bardernson");
        System.out.println(carly);

        System.out.println("getFullNameを呼ぶ" + carly.toString());
        
        // birthYearはprivate変数で、Personクラスの定義では、birthYearを更新するpublicメソッドが提供されていないため、この行はエラーとなります。
        // carly.birthYear = 1976;
    }
}