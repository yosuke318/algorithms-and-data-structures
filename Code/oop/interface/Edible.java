// Audibleという名前のインターフェースを定義します。
// 音を出すオブジェクトが持つべきメソッドを定義します。
interface Audible{
    public abstract void makeNoise();
    public abstract double soundFrequency();
    public abstract double soundLevel();
}

// Edibleという名前のインターフェースを定義します。
// 食べられるオブジェクトが持つべきメソッドを定義します。
interface Edible{
    public abstract String howToPrepare();
    public abstract double calories();
}

// PersonクラスはAudibleインターフェースを実装します。
// そのため、makeNoise(), soundFrequency(), soundLevel()というメソッドを持つ必要があります。
class Person implements Audible{
    private String firstName;
    private String lastName;
    private double heightM;
    private double weightKg;
    private int age;

    public Person(String firstName, String lastName, double heightM, double weightKg, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.age = age;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public String toString(){
        return this.getFullName() + " who is " + this.heightM + "m tall and weights " + this.weightKg + "kg.";
    }

    // Personが音を出すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public void makeNoise(){
        System.out.println("Hello World!");
    }

    // Personの音の周波数を返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundFrequency(){
        return this.age > 16 ? 110 : 130;
    }

    // Personの音のレベルを返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundLevel(){
        return this.age > 16 ? 60 : 65;
    }
}

// HorseクラスもAudibleインターフェースを実装します。
// Personクラスと同様に、makeNoise(), soundFrequency(), soundLevel()というメソッドを持つ必要があります。
class Horse implements Audible{
    private double weightKg;
    private double soundFrequency = 120;
    private double soundDecibels = 75;

    public Horse(double weightKg){
        this.weightKg = weightKg;
    } 

    public String toString(){
        return "This is a horse that weights: " + this.weightKg + "kg";
    }

    // Horseが音を出すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public void makeNoise(){
        System.out.println("Neeighh!!");
    }

    // Horseの音の周波数を返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundFrequency(){
        return this.soundFrequency;
    }

    // Horseの音のレベルを返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundLevel(){
        return this.soundDecibels;
    }
}

// CowクラスはAudibleとEdibleの両方のインターフェースを実装します。
// そのため、makeNoise(), soundFrequency(), soundLevel()というメソッドと、howToPrepare(), calories()というメソッドを持つ必要があります。
class Cow implements Audible, Edible{
    private double weightKg;
    private double soundFrequency = 90;
    private double soundDecibels = 70;

    public Cow(double weightKg){
        this.weightKg = weightKg;
    } 

    public String toString(){
        return "This is a cow that weights: " + this.weightKg + "kg";
    }

    // Cowが音を出すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public void makeNoise(){
        System.out.println("Moooo!!");
    }

    // Cowの音の周波数を返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundFrequency(){
        return this.soundFrequency;
    }

    // Cowの音のレベルを返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundLevel(){
        return this.soundDecibels;
    }

    // Cowの調理方法を返すメソッドを定義します。
    // このメソッドはEdibleインターフェースによって要求されています。
    public String howToPrepare(){
        return "Cut the cow with a butchering knife into even pieces, and grill each piece at 220C";
    }

    // Cowのカロリーを返すメソッドを定義します。
    // このメソッドはEdibleインターフェースによって要求されています。
    public double calories(){
        return this.weightKg * 1850;
    }
}

// ChickenクラスはAudibleとEdibleの両方のインターフェースを実装します。
// そのため、makeNoise(), soundFrequency(), soundLevel()というメソッドと、howToPrepare(), calories()というメソッドを持つ必要があります。
class Chicken implements Audible, Edible{
    private double weightKg;
    private double soundFrequency = 140;
    private double soundDecibels = 160;

    public Chicken(double weightKg){
        this.weightKg = weightKg;
    } 

    public String toString(){
        return "This is a chicken that weights: " + this.weightKg + "kg";
    }

    // Chickenが音を出すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public void makeNoise(){
        System.out.println("kokecookoo!!");
    }

    // Chickenの音の周波数を返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundFrequency(){
        return this.soundFrequency;
    }

    // Chickenの音のレベルを返すメソッドを定義します。
    // このメソッドはAudibleインターフェースによって要求されています。
    public double soundLevel(){
        return this.soundDecibels;
    }

    // Chickenの調理方法を返すメソッドを定義します。
    // このメソッドはEdibleインターフェースによって要求されています。
    public String howToPrepare(){
        return "Cut the chicken with a butchering knife into even pieces, and grill each piece at 220C";
    }

    // Chickenのカロリーを返すメソッドを定義します。
    // このメソッドはEdibleインターフェースによって要求されています。
    public double calories(){
        return this.weightKg * 1050;
    }
}

// 以下もAudibleインターフェースを実装したクラスの例です。
class Truck implements Audible{
    private double weightKg;

    public Truck(double weightKg){
        this.weightKg = weightKg;
    } 

    public String toString(){
        return "This is a truck that weights: " + this.weightKg + "kg";
    }

    public void makeNoise(){
        System.out.println("Beep Beep!!");
    }

    public double soundFrequency(){
        return 165;
    }

    public double soundLevel(){
        return 120;
    }
}

class Violin implements Audible{
    private double soundFrequency = 659.3;
    private final static double SOUND_DECIBELS = 95;

    public String toString(){
        return "This is a violin that plays music: ";
    }

    public void makeNoise(){
        System.out.println("Beep Beep!!");
    }

    public double soundFrequency(){
        return this.soundFrequency;
    }

    public double soundLevel(){
        return Violin.SOUND_DECIBELS;
    }
}

class Pizza implements Edible{

    public String howToPrepare(){
        return "cut by butterknife and take piece of this and eat.";
    }

    public double calories(){
        return 1200.0;
    }
}

// Mainクラスでは、PersonクラスのインスタンスがAudibleやEdibleのインスタンスとどのように相互作用するかを定義します。
class Main{
    // PersonがAudibleオブジェクトとやりとりする例を示します。
    public static void personInteractsWithObject(Person p, Audible noiseObject){
        System.out.println(p + " will interact with " + noiseObject + " and cause it to make a noise");
        noiseObject.makeNoise();
        System.out.println("The noise was made at " + noiseObject.soundFrequency() + "Hz at a level of " + noiseObject.soundLevel() + "dB");
        System.out.println();
    }

    // PersonがEdibleオブジェクトを食べる例を示します。
    public static void personEatsEdible(Person p, Edible rawFood){
        System.out.println(p + " will prepare and eat :" + rawFood + ". They do the following:" + rawFood.howToPrepare());
        System.out.println("The person prepared and ate the meal. " + rawFood.calories() + " calories consumed.");
        System.out.println();
    }

    public static void main(String[] args){
        Person ashley = new Person("Ashley", "William", 1.8, 110, 29);

        Person obj1 = new Person("Toshi", "Takemura", 1.7, 105, 41);
        Horse obj2 = new Horse(450);
        Cow obj3 = new Cow(1300);
        Truck obj4 = new Truck(3230.5);
        Violin obj5 = new Violin();
        Chicken obj6 = new Chicken(2.4);
        Pizza obj7 = new Pizza();

        // Ashleyが他のAudibleオブジェクトとやりとりします。
        personInteractsWithObject(ashley, obj1);
        personInteractsWithObject(ashley, obj2);
        personInteractsWithObject(ashley, obj3);
        // CowはAudibleインターフェースとEdibleインターフェースの両方を実装しています。
        // そのため、AshleyはCowとやりとりし、またCowを食べることも可能です。
        personEatsEdible(ashley, obj3);
        personEatsEdible(ashley, obj6);
        personEatsEdible(ashley, obj7);
    }
}