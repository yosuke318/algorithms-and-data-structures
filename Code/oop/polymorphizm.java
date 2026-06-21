import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class Coordinate{
    public int x;
    public int y;
    public int z;

    public Coordinate(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString(){
        return "{x:"+this.x+",y:"+this.y+",z:"+this.z+"}";
    }
}

class Field{
    private static final int MAX_X = 100000;
    private static final int MAX_Y = 40000;
    private static final int MAX_Z = 1000;

    private ArrayList<Animal> creatures;
    private ArrayList<Coordinate> creatureCoordinates;

    public Field(){
        this.creatures = new ArrayList<Animal>();
        this.creatureCoordinates = new ArrayList<Coordinate>();
    }

    public void randomlyAddWithDependency(Animal creature){
        Coordinate c = new Coordinate(this.internalRanAlgorithm(1, Field.MAX_X), this.internalRanAlgorithm(1, Field.MAX_Y),this.internalRanAlgorithm(1, Field.MAX_Z));

        this.creatures.add(creature);
        this.creatureCoordinates.add(c);
    }

    private int internalRanAlgorithm(int min, int max){
        return (int) (Math.random() * (max - min) + min);
    }

    public String toString(){
        StringBuffer s = new StringBuffer("");
        for(int i = 0; i < this.creatures.size(); i++){
            s.append(this.creatures.get(i) + " with coordinates: " + this.creatureCoordinates.get(i) + "");
        }
        return s.toString();
    }
}

class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getWeightKg(){
        return this.weightKg;
    }

    public double getValue(){
        return this.weightKg/(this.heightM*this.heightM);
    }

    public String toString(){
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}

class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected Date spawnTime;
    protected Date deathTime;
    protected int hungerPercent = 100;
    protected int sleepPercent = 100;

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg);
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new java.util.Date();
    }

    public void eat(){
        if(!this.isAlive()) return;
        this.hungerPercent = 0;
    }

    public void setAsHungry(){
        if(!this.isAlive()) return;
        this.hungerPercent = 100;
    }

    public boolean isHungry(){
        return this.hungerPercent >= 70;
    }

    public void sleep(){
        if(!this.isAlive()) return;
        this.sleepPercent = 0;
    }

    public void setAsSleepy(){
        if(!this.isAlive()) return;
        this.sleepPercent = 100;
    }

    public boolean isSleepy(){
        return this.sleepPercent >= 70;
    }

    public void die(){
        this.sleepPercent = 0;
        this.hungerPercent = 0;
        this.deathTime = new java.util.Date();
    }

    public boolean isAlive(){
        return this.deathTime == null;
    }

    public String toString(){
        return this.species + this.bmi + " lives " + this.lifeSpanDays + " days/" + "gender:" + this.biologicalSex + "." + this.status();
    }

    public String status(){
        return this.species + " status:" + " Hunger - " + this.hungerPercent + "%, " + "sleepiness:"+this.sleepPercent + "%" + ", Alive - " + this.isAlive() + ". First created at " + this.dateCreated();
    }

    public String dateCreated(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.spawnTime);
    }
}

class Mammal extends Animal{
    private double furLengthCm;
    private String furType;
    private int toothCounter;
    private double bodyTemperatureC;
    private double avgBodyTemperatureC;
    private boolean mammaryGland = false;

    private boolean sweatGland = true;
    private boolean isPregnant = false;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC){

        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.furLengthCm = furLengthCm;
        this.furType = furType;

        this.mammaryGland = (biologicalSex == "female");

        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public void sweat(){
        if(!this.isAlive()) return;
        if(this.sweatGland) System.out.print("Sweating....");
        this.bodyTemperatureC-=0.3;
        System.out.print("Body temperature is now " + this.bodyTemperatureC + "C");
        System.out.println();
    }

    public void produceMilk(){
        if(!this.isAlive()) return;
        if(this.isPregnant() && this.mammaryGland) System.out.println("Producing milk...");
        else System.out.println("Cannot produce milk");
        System.out.println();
    }

    public void mate(Mammal mammal){
        if(!this.isAlive()) return;
        if(this.species != mammal.species) return;
        if(this.biologicalSex == "female" && mammal.biologicalSex == "male") this.fertalize();
        else if(this.biologicalSex == "male" && mammal.biologicalSex == "female") mammal.fertalize();
    }

    public void fertalize(){
        if(!this.isAlive()) return;
        this.isPregnant = true;
    }

    public boolean isPregnant(){
        if(!this.isAlive()) return false;
        return this.isPregnant;
    }

    public void bite(){
        if(!this.isAlive()) return;
        System.out.println(this.species + " bites with their single lower jaws which has" +  (this.toothCounter == 0 ? " not" : "")  + " replaced its teeth: " + (this.toothCounter > 0));
        System.out.println();
    }

    public void replaceTeeth(){
        if(!this.isAlive()) return;
        if(this.toothCounter == 0) this.toothCounter++;
    }

    public void increaseBodyHeat(double celcius){
        this.bodyTemperatureC+=celcius;
    }

    public void decreaseBodyHeat(double celcius){
        this.bodyTemperatureC-=celcius;
    }

    public void adjustBodyHeat(){
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public void move(){
        if(!this.isAlive()) return;
        System.out.println("This mammal is moving.....");
        System.out.println();
    }

    public String toString(){
        return super.toString() + this.mammalInformation();
    }

    public String mammalInformation(){
        return "This is a mammal with the following - "+"fur:"+this.furType+"/teethReplaced:"+(this.toothCounter>0)+"/Pregnant:"+this.isPregnant()+"/Body Temperature:"+this.bodyTemperatureC;
    }

    public void eat(){
        super.eat();
        this.bite();
        System.out.println("this" + this.species + " is eating with its single lower jaw");
    }
}

class Fox extends Mammal{
    public Fox(double heightM, double weightKg, String biologicalSex){
        super("Fox", heightM, weightKg, 1460, biologicalSex, 35, "Fox", 38.7);
    }

    public void howl(){
        System.out.println("Oooooooooooooooowhoo");
    }

    public void eat(){
        super.eat();
        System.out.println("This fox is a carnivore");
    }

    public void sleep(){
        super.sleep();
        System.out.println("This fox is sleeping");
    }
}

// より多くのクラスを作成してください。これらは単に親を継承します。
class Bird extends Animal{
    public Bird(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);
    }

    public void eat(){
        super.eat();
        System.out.println("This bird is a Omnivore");
    }

    public void sleep(){
        super.sleep();
        System.out.println("This bird is sleeping");
    }
}

class Rabbit extends Mammal{
    public Rabbit(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex, furLengthCm, furType, avgBodyTemperatureC);
    }

    public void eat(){
        super.eat();
        System.out.println("This rabbit is a Herbivore");
    }

    public void sleep(){
        super.sleep();
        System.out.println("This rabbit is Sleeping");
    }
}

class Main{
    // この関数では、Animalクラスのオブジェクトが引数として渡されます。
    // メソッド呼び出し(animal.eat())では、ランタイム時にオブジェクトの実際のタイプがどのサブクラスであるかを確認します。
    // これが動的バインディングと呼ばれるものです。
    // Javaは動的にeat()メソッドをバインドし、そのメソッドが実装されているクラスを確認します。
    // これにより、適切なメソッドが呼び出され、多態性が実現されます。
    public static void animalPolymorphism(Animal animal){
        System.out.println(animal);
        animal.eat();

        // ここでも動的バインディングが行われますが、さらに「instanceof」演算子が使われています。
        // これは、animalオブジェクトがFoxクラスのインスタンスであるかどうかを確認します。Foxクラスのインスタンスであれば、howlメソッドを呼び出します。
        if(animal instanceof Fox) ((Fox) animal).howl();
        System.out.println();
    }

    public static void main(String[] args){
        // 各クラスのインスタンスを作成
        Mammal cow = new Mammal("Cattle", 1.8,454.5,730, "female", 1.4, "Cowhide", 32.4);
        Animal bullAnimal = new Animal("Cattle", 1.8,454.5,730, "male");
        Fox f1 = new Fox(0.7, 14, "female");
        Bird bird1 = new Bird("Bird", 0.1,2.5,30, "female");
        Rabbit bunny1 = new Rabbit("Bunny", 0.1,5.5,100, "female", 0.9, "Rabbit Fur", 32.4);

        System.out.println("-----polymorphism test START-----");

        // 動的バインディングの例： animalPolymorphismにそれぞれの動物を渡すと、それぞれの動物タイプに応じて適切なeatメソッドが実行されます。
        animalPolymorphism(cow);// Mammalのeat()が呼び出されました。
        animalPolymorphism(bullAnimal);// Animalのeat()が呼び出されました。
        animalPolymorphism(f1); // Foxのeat()が呼び出されました。
        animalPolymorphism(bird1);// Animalのeat()が呼び出されました。
        animalPolymorphism(bunny1);// Mammalのeat()が呼び出されました。
        System.out.println("-----polymorphism test END-----");
        System.out.println();


        Field world = new Field();
        world.randomlyAddWithDependency(cow);
        world.randomlyAddWithDependency(bullAnimal);
        world.randomlyAddWithDependency(f1);
        world.randomlyAddWithDependency(bird1);
        world.randomlyAddWithDependency(bunny1);

        System.out.println(world);

        // sleep()関数を異なるクラスに上書きして動的バインディングを調べ、その関数をanimalPolymorphism内で呼び出してください。
        f1.sleep();
        bird1.sleep();
        bunny1.sleep();
    }
}