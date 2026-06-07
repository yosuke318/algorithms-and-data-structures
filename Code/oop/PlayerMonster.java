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
