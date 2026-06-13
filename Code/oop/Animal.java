import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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

class Cattle extends Mammal{
    private boolean hasHorns;              // 角の有無
    private double milkProductionLiters;   // 1日あたりの乳量（リットル）

    public Cattle(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC, boolean hasHorns, double milkProductionLiters){
        super("Cattle", heightM, weightKg, lifeSpanDays, biologicalSex, furLengthCm, furType, avgBodyTemperatureC);
        this.hasHorns = hasHorns;
        this.milkProductionLiters = milkProductionLiters;
    }

    // 草を食べる
    public void graze(){
        if(!this.isAlive()) return;
        System.out.println("Cattle is grazing...");
        this.eat();
    }

    // 角で突く
    public void charge(){
        if(!this.isAlive()) return;
        if(this.hasHorns){
            System.out.println("Cattle charges with its horns!");
        } else {
            System.out.println("Cattle has no horns to charge with.");
        }
    }

    public boolean hasHorns(){
        return this.hasHorns;
    }

    public double getMilkProductionLiters(){
        return this.milkProductionLiters;
    }

    @Override
    public String toString(){
        return super.toString() + " hasHorns:" + this.hasHorns + "/milkProduction:" + this.milkProductionLiters + "L/day";
    }
}

class Horse extends Mammal{
    private String coatColor;       // 毛色（例："bay", "black", "white"）
    private boolean isWild;         // 野生かどうか
    private double runningSpeedKmh;  // 走行速度（km/h）

    public Horse(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC, String coatColor, boolean isWild, double runningSpeedKmh){
        super("Horse", heightM, weightKg, lifeSpanDays, biologicalSex, furLengthCm, furType, avgBodyTemperatureC);
        this.coatColor = coatColor;
        this.isWild = isWild;
        this.runningSpeedKmh = runningSpeedKmh;
    }

    public double getRunningSpeedKmh(){
        return this.runningSpeedKmh;
    }

    // 逃げるメソッド
    public void runAway(){
        if(!this.isAlive()) return;
        if(this.isWild){
            System.out.println("The wild horse runs away at full speed!");
        } else {
            System.out.println("The horse resists and tries to run away!");
        }
        this.move();
    }

    public String getCoatColor(){
        return this.coatColor;
    }

    public boolean isWild(){
        return this.isWild;
    }

    @Override
    public String toString(){
        return super.toString() + " coatColor:" + this.coatColor + "/isWild:" + this.isWild;
    }
}

// ...existing code...

class Bird extends Animal{
    private double wingSpanCm;      // 翼開長（センチメートル単位）
    private boolean canFly;         // 飛べるかどうか（ペンギンなどはfalse）
    private String featherType;     // 羽毛の種類（例："down", "contour"）
    private String beakType;        // くちばしの種類（例："hooked", "flat", "pointed"）
    private boolean isMigratory;    // 渡り鳥かどうか

    public Bird(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double wingSpanCm, boolean canFly, String featherType, String beakType, boolean isMigratory){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);
        this.wingSpanCm = wingSpanCm;
        this.canFly = canFly;
        this.featherType = featherType;
        this.beakType = beakType;
        this.isMigratory = isMigratory;
    }

    // 飛ぶ
    public void fly(){
        if(!this.isAlive()) return;
        if(this.canFly){
            System.out.println(this.species + " is flying with its " + this.wingSpanCm + "cm wingspan!");
        } else {
            System.out.println(this.species + " cannot fly.");
        }
        System.out.println();
    }

    // 鳴く
    public void chirp(){
        if(!this.isAlive()) return;
        System.out.println(this.species + " is chirping!");
        System.out.println();
    }

    // 渡りをする
    public void migrate(){
        if(!this.isAlive()) return;
        if(this.isMigratory){
            System.out.println(this.species + " is migrating to a warmer region.");
        } else {
            System.out.println(this.species + " does not migrate.");
        }
        System.out.println();
    }

    public double getWingSpanCm(){
        return this.wingSpanCm;
    }

    public boolean canFly(){
        return this.canFly;
    }

    public boolean isMigratory(){
        return this.isMigratory;
    }

    @Override
    public void move(){
        if(!this.isAlive()) return;
        System.out.println(this.species + (this.canFly ? " is flying through the sky!" : " is walking on the ground."));
        System.out.println();
    }

    @Override
    public String toString(){
        return super.toString() + " wingSpan:" + this.wingSpanCm + "cm/canFly:" + this.canFly + "/feather:" + this.featherType + "/beak:" + this.beakType + "/migratory:" + this.isMigratory;
    }
}

class Chicken extends Bird{
    private boolean isFreeRange;        // 放し飼いかどうか
    private double eggProductionPerDay; // 1日あたりの産卵数

    public Chicken(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double wingSpanCm, String featherType, boolean isFreeRange, double eggProductionPerDay){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex, wingSpanCm, false, featherType, "pointed", false);
        // ニワトリは飛べない(canFly=false)、渡り鳥でない(isMigratory=false)、くちばしは"pointed"で固定
        this.isFreeRange = isFreeRange;
        this.eggProductionPerDay = eggProductionPerDay;
    }

    // 卵を産む
    public void layEgg(){
        if(!this.isAlive()) return;
        if(this.biologicalSex.equals("female")){
            System.out.println(this.species + " laid an egg! (daily production: " + this.eggProductionPerDay + ")");
        } else {
            System.out.println("Male chicken cannot lay eggs.");
        }
        System.out.println();
    }

    // 鳴く（コケコッコー）
    @Override
    public void chirp(){
        if(!this.isAlive()) return;
        System.out.println(this.species + " says: Cock-a-doodle-doo!");
        System.out.println();
    }

    public boolean isFreeRange(){
        return this.isFreeRange;
    }

    public double getEggProductionPerDay(){
        return this.eggProductionPerDay;
    }

    @Override
    public String toString(){
        return super.toString() + " isFreeRange:" + this.isFreeRange + "/eggProduction:" + this.eggProductionPerDay + "/day";
    }
}


class Person extends Mammal{
    private Map<String, List<Animal>> listOfAnimalMap = new HashMap<>(Map.of(
        "Cattle", new ArrayList<>(),
        "Chicken", new ArrayList<>(),
        "Horse", new ArrayList<>()
    ));
    private Integer earning;
    
    public Person(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, Integer earning){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex, 0, "none", 36.6);
        this.earning = earning;
    }

    public void haveAnimal(String species, Integer count){
        listOfAnimalMap.putIfAbsent(species, new ArrayList<>());

        for(int i = 0; i < count; i++){
            Animal animal;
            switch(species){
                case "Cattle":
                    animal = new Cattle("Cattle", 1.8, 454.5, 3650, "female", 1.4, "Cowhide", 38.5, false, 25.0);
                    break;
                case "Chicken":
                    animal = new Chicken("Chicken", 0.3, 2.5, 1825, "female", 20.0, "contour", true, 0.8);
                    break;
                case "Horse":
                    animal = new Horse("Horse", 1.6, 500.0, 10950, "female", 2.0, "Horsehair", 37.5, "bay", false);
                    break;
                default:
                    animal = new Animal(species, 1.0, 10.0, 365, "female");
                    break;
            }

            listOfAnimalMap.get(species).add(animal);
        }
    }

    public void sellAnimal(String species, Integer count){
        List<Animal> animals = listOfAnimalMap.get(species);

        if(animals == null || animals.size() == 0){
            return;
        }

        if(count > animals.size()){
            return ;
        }

        int totalEarning = 0;

        for(int i = 0; i < count; i++){
            Animal animal = animals.get(i);
            switch(species){
                case "Cattle":
                    totalEarning += (int)(animal.bmi.getWeightKg() * 500);
                    break;
                case "Chicken":
                    totalEarning += (int)(animal.bmi.getWeightKg() * 300);
                    break;
                case "Horse":
                    if(animal instanceof Horse){
                        totalEarning += (int)(((Horse) animal).getRunningSpeedKmh() * 10000);
                    }
                    break;
            }
        }

        animals.subList(0, count).clear();
        setEarning(totalEarning);
        System.out.println("Sold " + count + " " + species + " for " + totalEarning + " yen.");
        System.out.println("Remaining " + species + ": " + animals.size() + " / Total earning: " + this.earning);
        System.out.println();
    }

    public void sellAnimal(String species){
        // 全頭売る
        List<Animal> animals = listOfAnimalMap.get(species);
        if(animals == null || animals.size() == 0){
            System.out.println("No " + species + " to sell.");
            return;
        }
        sellAnimal(species, animals.size());
    }

    public void setEarning(Integer money){
        this.earning += money;
    }

    public void buyMilk(){
        if(listOfAnimalMap.get("Cattle").size() != 0){
            setEarning(1);
        }
    }

    public void buyEgg(){
        if(listOfAnimalMap.get("Chicken").size() == 0) return;

        setEarning(1);
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
        shihTzu.ownPet();
        System.out.println(shihTzu.isPet());

        Person farmer = new Person("Human", 1.7, 65.0, 29200, "male", 500000);

        farmer.haveAnimal("Cattle", 3);   // 牛を3頭追加
        farmer.haveAnimal("Chicken", 5);  // ニワトリを5羽追加
        farmer.haveAnimal("Horse", 2);    // 馬を2頭追加
        farmer.buyMilk();
        farmer.buyEgg();

        farmer.haveAnimal("Cattle", 3);
        farmer.sellAnimal("Cattle", 5);  // → Not enough Cattle to sell. (have: 3, requested: 5)
        farmer.sellAnimal("Cattle", 2);  // → Sold 2 Cattle for *** yen. Remaining Cattle: 1
        farmer.sellAnimal("Cattle");     // → Sold 1 Cattle for *** yen. Remaining Cattle: 0
    }
}