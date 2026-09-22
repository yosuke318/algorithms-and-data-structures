interface Audible{
    public abstract void makeNoise();
    public abstract double soundFrequency();
    public abstract double soundLevel();
}

interface Edible{
    public abstract String howToPrepare();
    public abstract double calories();
}

// ここから開発してください
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

    public void makeNoise(){
        System.out.println("Hello World!");
    }

    public double soundFrequency(){
        return this.age > 16 ? 110 : 130;
    }

    public double soundLevel(){
        return this.age > 16 ? 60 : 65;
    }
}

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

    public void makeNoise(){
        System.out.println("Neeighh!!");
    }

    public double soundFrequency(){
        return this.soundFrequency;
    }

    public double soundLevel(){
        return this.soundDecibels;
    }
}

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

    public void makeNoise(){
        System.out.println("Moooo!!");
    }

    public double soundFrequency(){
        return this.soundFrequency;
    }

    public double soundLevel(){
        return this.soundDecibels;
    }

    public String howToPrepare(){
        return "Cut the cow with a butchering knife into even pieces, and grill each piece at 220C";
    }

    public double calories(){
        return this.weightKg * 1850;
    }
}

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

class Main{
    public static void personInteractsWithObject(Person p, Audible noiseObject){
        System.out.println(p + " will interact with " + noiseObject + " and cause it to make a noise");
        noiseObject.makeNoise();
        System.out.println("The noise was made at " + noiseObject.soundFrequency() + "Hz at a level of " + noiseObject.soundLevel() + "dB");
        System.out.println();
    }

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

        personInteractsWithObject(ashley, obj1);
        personInteractsWithObject(ashley, obj2);

        personInteractsWithObject(ashley, obj3);
        personEatsEdible(ashley, obj3);
    }
}