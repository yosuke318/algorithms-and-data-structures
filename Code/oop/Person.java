class Wallet{
    private int bill1;
    private int bill5;
    private int bill10;
    private int bill20;
    private int bill50;
    private int bill100;

    public Wallet(){}

    public int getTotalMoney(){
        return (1*bill1) + (5*bill5) + (10*bill10) + (20*bill20) + (50*bill50) + (100*bill100);
    }

    public int insertBill(int bill, int amount){
        switch(bill){
            case(1):
                bill1 += amount;
                break;
            case(5):
                bill5 += amount;
                break;
            case(10):
                bill10 += amount;
                break;
            case(20):
                bill20 += amount;
                break;
            case(50):
                bill50 += amount;
                break;
            case(100):
                bill100 += amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }
}

class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}

class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    // BMIの値を計算して返すメソッド
    public double getValue(){
        return this.weightKg/(this.heightM*this.heightM);
    }

    public String toString(){
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}

// PersonはNameとBMIから構成されます。
class Person{
    // Nameオブジェクトを参照。コンポジションの一部
    private Name name;
    private int age;
    // BMIオブジェクトを参照。コンポジションの一部。
    private BMI bmi;
    private Wallet wallet;
    private Address address;

    // Personクラスのコンストラクタ。ここでNameとBMIの新しいインスタンスが作られ、コンポジションが形成されます。
    public Person(String firstName, String lastName, int age, double heightM, double weightKg, Address address){
        this.name = new Name(firstName, lastName);
        this.age = age;
        this.bmi = new BMI(heightM, weightKg);
        this.wallet = new Wallet();
        this.address = address;
    }

    public int getCash(){
        if(this.wallet == null) return 0;
        return this.wallet.getTotalMoney();
    }

    public int receiveBill(int bill, int amount){
        return this.wallet.insertBill(bill, amount);
    }

    public Wallet dropWallet(){
        Wallet w = this.wallet;
        this.wallet = null;
        return w;
    }

    public void addWallet(Wallet wallet){
        if(this.wallet == null) this.wallet = wallet;
    }

    public void printState(){
        System.out.println("Name - " + this.name);
        System.out.println("age - " + this.age);
        System.out.println("height and weight - " + this.bmi);
        System.out.println("Current Money - " + this.getCash());
        System.out.println("Address - " + this.address);
        System.out.println();
    }
}

class Address{
    private String address;
    private String city;
    private String country;

    public Address(String address, String city, String country){
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public String toString(){
        return this.address + " ," + this.city + " " + this.country;
    }
}

class Main{
    public static void main(String[] args){
        Address house = new Address("Baker street 9 120", "Seatle", "United States");
        Person ryu = new Person("Ryu","Poolhopper", 40, 1.8, 90, house);
        Person tom = new Person("Tom","Poolhopper", 55, 1.75, 85, house);
        Person martha = new Person("Martha","Poolhopper", 55, 1.7, 105, house);

        ryu.printState();
        tom.printState();
        martha.printState();

        // これらのPersonオブジェクトが削除されると、BMIやNameオブジェクトも一緒に削除されることになります。
        tom = null;
        martha = null;

        // marthaやtomのBMIや名前にアクセスする方法はもうありません。tomとmarthaはコンポジションオブジェクトと一緒にガーベジコレクタされました。
    }
}