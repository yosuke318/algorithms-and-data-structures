import java.util.Date;
import java.text.SimpleDateFormat;

class BMI{
    private double heightM;   // 身長（メートル単位）
    private double weightKg;  // 体重（キログラム単位）

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
    protected String species;          // 動物の種類・名称
    protected BMI bmi;                 // 身長・体重・BMIをまとめたオブジェクト
    protected double lifeSpanDays;     // 寿命（日数）
    protected String biologicalSex;    // 生物学的性別（"male" または "female"）
    protected Date spawnTime;          // 誕生した日時
    protected Date deathTime;          // 死亡した日時（生存中はnull）
    protected int hungerPercent = 100; // 空腹度（0:満腹 〜 100:空腹）
    protected int sleepPercent = 100;  // 眠気（0:十分睡眠 〜 100:眠い）

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

    public void move(){
        if(!this.isAlive()) return;
        System.out.println("This animal just moved...");
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
    private double furLengthCm;          // 毛の長さ（センチメートル単位）
    private String furType;              // 毛の種類・名称
    private int toothCounter;            // 歯の生え替わり回数（0:未交換、1以上:交換済み）
    private double bodyTemperatureC;     // 現在の体温（摂氏）
    private double avgBodyTemperatureC;  // 平均（正常）体温（摂氏）
    private boolean mammaryGland = false;// 乳腺の有無（メスの場合true）
    private boolean sweatGland = true;   // 汗腺の有無（哺乳類は基本的にtrue）
    private boolean isPregnant = false;  // 妊娠中かどうか

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

    // 以下のメソッドでは、親クラスAnimalのメソッドをオーバーライド（上書き）しています。
    // オーバーライドとは、継承したメソッドの振る舞いをサブクラスで再定義することです。

    // moveメソッドのオーバーライド
    public void move(){
        if(!this.isAlive()) return;
        System.out.println("This mammal is moving.....");
        System.out.println();
    }

    // toStringメソッドのオーバーライド
    // ここでは、super.toString()を使って親クラスのメソッドを呼び出し、その結果にMammalクラス固有の情報を追加しています。
    public String toString(){
        return super.toString() + this.mammalInformation();
    }

    public String mammalInformation(){
        return "This is a mammal with the following - "+"fur:"+this.furType+"/teethReplaced:"+(this.toothCounter>0)+"/Pregnant:"+this.isPregnant()+"/Body Temperature:"+this.bodyTemperatureC;
    }

    // eatメソッドのオーバーライド
    // ここでも、super.eat()を使って親クラスのメソッドを呼び出し、その後にMammalクラス固有の行動を追加しています。
    public void eat(){
        super.eat();
        this.bite();
        System.out.println("this" + this.species + " is eating with its single lower jaw");
    }
}

class Dog extends Mammal{
    private String breed;   // 犬種（例："Shiba Inu", "Golden Retriever"）
    private boolean isPet;  // ペットとして飼われているかどうか

    public Dog(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC, String breed, boolean isPet){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex, furLengthCm, furType, avgBodyTemperatureC);
        this.breed = breed;
        this.isPet = isPet;
    }

    public boolean isPet(){
        return this.isPet;
    }

    public void ownPet(){
        this.isPet = true;
    }

    public String getBreed(){
        return this.breed;
    }

}

class Main{
    public static void main(String[] args){
        Mammal cow = new Mammal("Cattle", 1.8,454.5,730, "female", 1.4, "Cowhide", 32.4);
        System.out.println(cow);
        System.out.println();

        Mammal bull = new Mammal("Cattle", 1.8,454.5,730, "male", 1.1, "Cowhide", 30.8);
        System.out.println(bull);
        System.out.println();

        Animal bullAnimal = new Animal("Cattle", 1.8,454.5,730, "male");
        System.out.println(bullAnimal);
        System.out.println();

        bull.move();
        bullAnimal.move();

        bull.eat();
        bullAnimal.eat();

        // 他の哺乳類を作成し、Mammalのメソッドを使用してください。
        // 動物クラスを拡張して、昆虫（insect）や爬虫類（reptiles）のクラスを作成してください。
        // Mammalsクラスを拡張して、Lionクラスを作成してください。Mammalからの全ての状態と挙動は、Lionクラスに継承されることに注意してください。これにはMammalsが継承した状態と挙動も含まれます。

        Dog shihTzu = new Dog("Dog", 0.75, 30.5, 730, "male", 1.1, "Cowhide", 30.8, "shihTzu", false);

        System.out.println(shihTzu.getBreed());
        System.out.println(shihTzu.isPet());
        System.out.println(shihTzu.ownPet());
        System.out.println(shihTzu.isPet());
    }
}