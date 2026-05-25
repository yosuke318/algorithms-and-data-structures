class Battery7v{
    public String manufacturer;
    public String model;
    public static final double VOLTAGE = 7.2;
    public static final String TYPE = "Lithium-Ion";
    public static int manufacturedCount;
    public double ampHours;
    public double weightKg;
    public double[] dimensionMm;

    public Battery7v(String manufacturer, String model, double ampHours, double weightKg, double wMm, double hMm, double dMm){
        this.manufacturer = manufacturer;
        this.model = model;
        this.ampHours = ampHours;
        this.weightKg = weightKg;
        this.dimensionMm = new double[]{wMm, hMm, dMm};
        this.manufacturedCount+=1;
    }

    public String toString(){
        return this.manufacturer + " " + this.model + " " + Battery7v.TYPE + " Battery: " + this.getPowerCapacity() + "Wh (" + Battery7v.VOLTAGE + "V/" + this.ampHours + "Ah) - " + this.dimensionMm[0] + "(W)x" + this.dimensionMm[1] + "(H)x" + this.dimensionMm[2] + "(D) " + this.getVolume() + " volume " + this.weightKg + "kg";
    }

    public double getPowerCapacity(){
        return Battery7v.VOLTAGE * this.ampHours;
    }

    public double getVolume(){
        return this.dimensionMm[0] * this.dimensionMm[1] * this.dimensionMm[2];
    }
}

class ExternalModule{
    // このメソッドは意図しない副作用を引き起こします。
    // Battery7vクラスの manufacturedCountを直接変更してしまうため、Battery7vの内部状態が予期せぬ形で変化します。
    public static void dangerousMethod(String customerId, Battery7v battery){
        System.out.println("Processing data....internals");
        System.out.println("Client " + customerId + " purchased a " + battery.toString());
        
        // 不適切に状態を変更します。これにより製造されたバッテリーの数が意図しない数値になってしまいます。
        battery.manufacturedCount += 4234;
    }

    // これも意図しない副作用を引き起こします。
    // 同じくBattery7v クラスの manufacturedCount を直接変更するため、プログラムの状態が不正確になる可能性があります。
    public static void otherDangerousMethod(){
        Battery7v.manufacturedCount += 10000;
    }
}

class Main {
    public static void main(String[] args) {
        // 新たなBattery7vオブジェクトを作成します。それぞれのオブジェクトはバッテリーを表しています。
        Battery7v zlD72 = new Battery7v("MT-Dell Tech", "ZL-D72", 9.9, 1.18, 38, 80, 70);
        Battery7v zlD50 = new Battery7v("MT-Dell Tech", "ZL-D50", 6.6, 0.9, 28, 50, 65);
        Battery7v zlD40 = new Battery7v("MT-Dell Tech", "ZL-D40", 5.3, 1.18, 38, 80, 70);

        // ここでは、現在までに製造されたバッテリーの合計数を表示しています。
        System.out.println("Total batteries manufactured: " + Battery7v.manufacturedCount);

        System.out.println();
        // ここでExternalModuleのメソッドを呼び出しますが、これらのメソッドはBattery7vのmanufacturedCountを不適切に変更します。
        ExternalModule.dangerousMethod("AD515221", zlD40);
        ExternalModule.otherDangerousMethod();

        System.out.println();
        // これらのメソッドが実行された後、製造されたバッテリーの数が突如として増加してしまいます。
        // このように、クラスの内部状態を直接変更できると、プログラムの予期せぬ挙動を引き起こす可能性があります。
        System.out.println("Total batteries manufactured: " + Battery7v.manufacturedCount);
    }
}