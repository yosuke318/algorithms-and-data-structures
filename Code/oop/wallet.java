class Wallet{
    public int bill1;
    public int bill5;
    public int bill10;
    public int bill20;
    public int bill50;
    public int bill100;

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

class Person{
    public String firstName;
    public String lastName;
    public int age;
    public double heightM;
    public double weightKg;
    public Wallet wallet;
    private String denomination;

    public Person(String firstName, String lastName, int age, double heightM, double weightKg){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.wallet = new Wallet();
        this.denomination = "highestFirst";
    }

    public int getCash(){
        if(this.wallet == null) return 0;
        return this.wallet.getTotalMoney();
    }

    public void printState(){
        System.out.println("firstname - " + this.firstName);
        System.out.println("lastname - " + this.lastName);
        System.out.println("age - " + this.age);
        System.out.println("height - " + this.heightM);
        System.out.println("weight - " + this.weightKg);
        System.out.println("Current Money - " + this.getCash());
        System.out.println();
    }

    public void getFullName(){
        System.out.println(this.firstName + " " + this.lastName);
    }

    public Wallet addWallet(Wallet wallet){
        this.wallet = wallet;
        return this.wallet;
    }

    public Wallet dropWallet(){
        this.wallet = null;
        return this.wallet;
    }

    public void setDenomination(String denomination){
        this.denomination = denomination;
    }

    public int[] spendMoney(int money){
        if(this.wallet == null || money > this.getCash()) return new int[]{};

        // [0]=bill1, [1]=bill5, [2]=bill10, [3]=bill20, [4]=bill50, [5]=bill100
        int[] bills = new int[6];
        int remaining = money;

        if(this.denomination.equals("highestFirst")){
            // 最高額から支払うロジック
            bills[5] = Math.min(this.wallet.bill100, remaining / 100);
            remaining -= bills[5] * 100;
            bills[4] = Math.min(this.wallet.bill50, remaining / 50);
            remaining -= bills[4] * 50;
            bills[3] = Math.min(this.wallet.bill20, remaining / 20);
            remaining -= bills[3] * 20;
            bills[2] = Math.min(this.wallet.bill10, remaining / 10);
            remaining -= bills[2] * 10;
            bills[1] = Math.min(this.wallet.bill5, remaining / 5);
            remaining -= bills[1] * 5;
            bills[0] = Math.min(this.wallet.bill1, remaining);
            remaining -= bills[0];
        } else if(this.denomination.equals("dollers")){
            // 最低額から支払うロジック
            bills[0] = Math.min(this.wallet.bill1, remaining);
            remaining -= bills[0];
            bills[1] = Math.min(this.wallet.bill5, remaining / 5);
            remaining -= bills[1] * 5;
            bills[2] = Math.min(this.wallet.bill10, remaining / 10);
            remaining -= bills[2] * 10;
            bills[3] = Math.min(this.wallet.bill20, remaining / 20);
            remaining -= bills[3] * 20;
            bills[4] = Math.min(this.wallet.bill50, remaining / 50);
            remaining -= bills[4] * 50;
            bills[5] = Math.min(this.wallet.bill100, remaining / 100);
            remaining -= bills[5] * 100;
        } else if(this.denomination.equals("twenties")){
            // 20ドル札から支払うロジック
            bills[3] = Math.min(this.wallet.bill20, remaining / 20);
            remaining -= bills[3] * 20;
            bills[2] = Math.min(this.wallet.bill10, remaining / 10);
            remaining -= bills[2] * 10;
            bills[1] = Math.min(this.wallet.bill5, remaining / 5);
            remaining -= bills[1] * 5;
            bills[0] = Math.min(this.wallet.bill1, remaining);
            remaining -= bills[0];
        }

        // 釣り銭が出せない場合（端数が残る場合）は何もしない
        if(remaining != 0) return new int[]{};

        // 財布から紙幣を削除
        this.wallet.bill1   -= bills[0];
        this.wallet.bill5   -= bills[1];
        this.wallet.bill10  -= bills[2];
        this.wallet.bill20  -= bills[3];
        this.wallet.bill50  -= bills[4];
        this.wallet.bill100 -= bills[5];

        return bills;
    }

    public int[] getPaid(int money){
        if(this.wallet == null) return new int[]{};

        // [0]=bill1, [1]=bill5, [2]=bill10, [3]=bill20, [4]=bill50, [5]=bill100
        int[] bills = new int[6];
        int remaining = money;

        if(this.denomination.equals("highestFirst")){
            // 最高額から受け取るロジック
            bills[5] = remaining / 100;
            remaining -= bills[5] * 100;
            bills[4] = remaining / 50;
            remaining -= bills[4] * 50;
            bills[3] = remaining / 20;
            remaining -= bills[3] * 20;
            bills[2] = remaining / 10;
            remaining -= bills[2] * 10;
            bills[1] = remaining / 5;
            remaining -= bills[1] * 5;
            bills[0] = remaining;
        } else if(this.denomination.equals("dollers")){
            // 最低額から受け取るロジック
            bills[0] = remaining;
            remaining -= bills[0];
            bills[1] = remaining / 5;
            remaining -= bills[1] * 5;
            bills[2] = remaining / 10;
            remaining -= bills[2] * 10;
            bills[3] = remaining / 20;
            remaining -= bills[3] * 20;
            bills[4] = remaining / 50;
            remaining -= bills[4] * 50;
            bills[5] = remaining / 100;
        } else if(this.denomination.equals("twenties")){
            // 20ドル札から受け取るロジック
            bills[3] = remaining / 20;
            remaining -= bills[3] * 20;
            bills[2] = remaining / 10;
            remaining -= bills[2] * 10;
            bills[1] = remaining / 5;
            remaining -= bills[1] * 5;
            bills[0] = remaining;
        }

        // 財布に紙幣を追加
        this.wallet.bill1   += bills[0];
        this.wallet.bill5   += bills[1];
        this.wallet.bill10  += bills[2];
        this.wallet.bill20  += bills[3];
        this.wallet.bill50  += bills[4];
        this.wallet.bill100 += bills[5];

        return bills;
    }

}

class Main{
    public static void main(String[] args){
        Person p = new Person("Ryu","Poolhopper", 40, 1.8, 140);
        p.printState();

        // 財布に紙幣を入れる
        p.wallet.insertBill(1, 3);   // $1 x 3
        p.wallet.insertBill(5, 2);   // $5 x 2
        p.wallet.insertBill(20, 1);  // $20 x 1
        p.wallet.insertBill(100, 1); // $100 x 1
        p.printState(); // 現在の所持金: $133

        // --- getPaid() の例 ---
        // denomination: "highestFirst"（デフォルト）で $55 を受け取る
        // → $50 x 1, $5 x 1 として受け取る
        System.out.println("=== getPaid $55 (highestFirst) ===");
        int[] received = p.getPaid(55);
        System.out.println("受け取った紙幣: $1=" + received[0] + ", $5=" + received[1]
            + ", $10=" + received[2] + ", $20=" + received[3]
            + ", $50=" + received[4] + ", $100=" + received[5]);
        p.printState(); // 所持金: $188

        // denomination: "twenties" で $45 を受け取る
        // → $20 x 2, $5 x 1 として受け取る
        p.setDenomination("twenties");
        System.out.println("=== getPaid $45 (twenties) ===");
        received = p.getPaid(45);
        System.out.println("受け取った紙幣: $1=" + received[0] + ", $5=" + received[1]
            + ", $10=" + received[2] + ", $20=" + received[3]
            + ", $50=" + received[4] + ", $100=" + received[5]);
        p.printState(); // 所持金: $233

        // --- spendMoney() の例 ---
        // denomination: "highestFirst" で $123 を支払う
        // → $100 x 1, $20 x 1, $3 x 1 として支払う
        p.setDenomination("highestFirst");
        System.out.println("=== spendMoney $123 (highestFirst) ===");
        int[] spent = p.spendMoney(123);
        System.out.println("支払った紙幣: $1=" + spent[0] + ", $5=" + spent[1]
            + ", $10=" + spent[2] + ", $20=" + spent[3]
            + ", $50=" + spent[4] + ", $100=" + spent[5]);
        p.printState(); // 所持金: $110

        // denomination: "dollers"（最低額優先）で $11 を支払う
        // → $1 x 3, $3 x 1... 
        p.setDenomination("dollers");
        System.out.println("=== spendMoney $11 (dollers) ===");
        spent = p.spendMoney(11);
        System.out.println("支払った紙幣: $1=" + spent[0] + ", $5=" + spent[1]
            + ", $10=" + spent[2] + ", $20=" + spent[3]
            + ", $50=" + spent[4] + ", $100=" + spent[5]);
        p.printState();

        // --- 財布なしのケース ---
        System.out.println("=== 財布を落とした後 ===");
        p.dropWallet();
        int[] result = p.spendMoney(10);
        System.out.println("spendMoney 結果の長さ（0なら何もしない）: " + result.length);
        result = p.getPaid(10);
        System.out.println("getPaid 結果の長さ（0なら何もしない）: " + result.length);
    }
}