import java.util.ArrayList;

class Player{
    private String username;
    private int health;
    private int attack;
    private int defense;

    private double height = 1.8;
    private int gold;

    public Player(String username, int health, int attack, int defense, int gold){
        this.username = username;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.gold = gold;

    }
    // getHeightメソッドはプレイヤーの高さを返す。
    public double getHeight(){
        return this.height;
    }

    // attackメソッドはMonsterクラスに依存しています。このメソッドはMonsterオブジェクトを引数に取り、そのMonsterオブジェクトに対する攻撃をシミュレートします。
    public void attack(Monster monster){

        System.out.println("Player is attacking to monster...");
        // Monsterの高さがPlayerの高さの3倍以上 or Monsterの防御力がPlayerの攻撃力以上の場合、攻撃は無効
        if(monster.getHeight() > this.height * 3 || monster.getDefense() >= this.attack){
            return;
        }

        // それ以外の場合、Playerの攻撃の値からMonsterの防御の値を引いた数値がHPから引かれる
        monster.attacked(this.attack - monster.getDefense());

    }
}

// 座標クラス、生物の座標を管理します
class Coordinates{
    public int x;
    public int y;
    public int z;

    public Coordinates(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


// Monsterクラスは依存関係を持ってない
class Monster{
    private String monster;
    private int health;
    private int attack;
    private int defense;

    private double height = 3;

    // コンストラクタ
    public Monster(String monster, int health, int attack, int defense){
        this.monster = monster;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    // getter
    public String getMonsterName(){
        return this.monster;
    }

    public int getHealth(){
        return this.health;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public double getHeight(){
        return this.height;
    }

    public void attacked(int damage){
        this.health -= damage;
        if(this.health < 0) this.health = 0;
    }


}


class Field{
    // フィールドクラス、モンスターの位置情報などを管理します
    private static final int MAX_X = 100000;
    private static final int MAX_Y = 40000;
    private static final int MAX_Z = 1000;

    // creatureの位置
    private ArrayList<Coordinates> coordinates;
    // Monster一覧
    private ArrayList<Monster> creatures;

    // コンストラクタ
    public Field(){
        this.coordinates = new ArrayList<Coordinates>();
        this.creatures = new ArrayList<Monster>();
    }

    // 既存のモンスターをランダムな位置に追加(呼び出し側が、Monsterを生成し注入しているためこれはDI)
    public void randomlyAddWithDependency(Monster monster){
        Coordinates c = new Coordinates(
            this.internalRanAlgorithm(1, this.MAX_X),
            this.internalRanAlgorithm(1, this.MAX_Y),
            this.internalRanAlgorithm(1, this.MAX_Z)
        );

        this.coordinates.add(c);
        this.creatures.add(monster);
    }

    // 新しく作ったモンスターをランダムな位置に追加
    public void randomlyAdd(String monster, int health, int attack, int defense){
        Monster creature = new Monster(monster, health, attack, health, defense);

        Coordinates c = new Coordinates(
            this.internalRanAlgorithm(1,this.MAX_X),
            this.internalRanAlgorithm(1,this.MAX_Y),
            this.internalRanAlgorithm(1, this.MAX_Z)
        );

        this.creatures.add(creature);
        this.coordinates.add(c);
    }



    private int internalRanAlgorithm(int min, int max){
        return (int) (Math.random() * (max - min) + min);
    }

    public String toString(){
        // String bufferは、文字列のための可変のデータ構造です。これを使って時間計算量を補うことにします。
        StringBuffer s = new StringBuffer(""); 
        for(int i = 0; i < this.creatures.size(); i++){
            s.append(this.creatures.get(i) + " with coordinates: " + this.coordinates.get(i) + "\n");      
        }
        return s.toString();
    }
}


class Main{
    public static void main(String[] args){
        Player p1 = new Player("Batrunner", 2000, 200, 60, 1000);
        Monster gorilla = new Monster("Gorilla", 4000, 40, 100);
        Monster vampire = new Monster("Vampire", 6000, 160, 20);

        Field world = new Field();

        // このメソッドではモンスターの名前とパラメータを引数として渡すことでモンスターを追加します。
        // この場合、内部でどのようにモンスターが作成されているか、このメソッドがどのクラスに依存しているかはわかりません。
        world.randomlyAdd("Dragon", 30000, 400, 400);

        // このメソッドではモンスターオブジェクトを直接引数として渡すことでモンスターを追加します。
        // この場合、このメソッドがMonsterクラスに依存していることが明示的にわかります。
        // また、モンスターオブジェクトが既に作成されているため、このメソッド内部でどのようにモンスターが作成されるかを
        // 考慮する必要がありません。これが依存性注入の一例です。
        world.randomlyAddWithDependency(gorilla);
        world.randomlyAddWithDependency(vampire);

        System.out.println(world);
    }
}