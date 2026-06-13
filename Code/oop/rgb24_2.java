class RGB24Immutable{
    // これらのメンバ変数はprivateなため、クラスの外部から直接変更することはできません。
    private int red;
    private int green;
    private int blue;

    // コンストラクタでは値の初期化を行いますが、その後は値の変更は許されません。
    public RGB24Immutable(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // このコンストラクタも、一度設定された値は変更できません。
    public RGB24Immutable(String inputString){
        int l = inputString.length();

        if(l == 6) this.setColorsByHex(inputString);
        else if(l == 24) this.setColorsByBin(inputString);
        else this.setAsBlack();
    }

    public RGB24Immutable(){
        this.setAsBlack();
    }

    // 反面教師的に、以下のようなpublicメソッドが存在すると、クラスの状態を変更できてしまうため、イミュータブルではなくなります。
    // public void changeBlack(){
    //     this.setAsBlack();
    // }
    
    // これらのprivateメソッドは、クラスの内部で色の設定を行うためのものです。
    // しかし、これらはコンストラクタ内からのみ呼び出され、他のメソッドからは呼び出せないため、
    // 一度設定された色は外部から変更することができません。
    private void setColorsByHex(String hex){
        if(hex.length() != 6) this.setAsBlack();
        else{
            this.red = Integer.parseInt(hex.substring(0,2), 16);
            this.green = Integer.parseInt(hex.substring(2,4), 16);
            this.blue = Integer.parseInt(hex.substring(4,6), 16);
        }
    }

    private void setColorsByBin(String bin){
        if(bin.length() != 24) this.setAsBlack();
        else{
            this.red = Integer.parseInt(bin.substring(0,8), 2);
            this.green = Integer.parseInt(bin.substring(8,16), 2);
            this.blue = Integer.parseInt(bin.substring(16), 2);
        }
    }

    private void setAsBlack(){
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    // publicなゲッターメソッドはあるため、外部から値を取得することはできます。
    // ただし、セッターメソッドは存在しないため、値を変更することはできません。
    public String getHex(){
        String hex = Integer.toHexString(this.red);
        hex+=Integer.toHexString(this.green);
        hex+=Integer.toHexString(this.blue);

        return hex;
    } 

    public String getBits(){
        return Integer.toBinaryString(Integer.parseInt(this.getHex(), 16));
    }

    public String getColorShade(){
        if(this.red == this.green && this.green == this.blue) return "greyscale";
        String[] stringTable = new String[]{"red","green","blue"};
        int[] values = {this.red, this.green, this.blue};

        int max = values[0];
        int maxIndex = 0;
        for(int i = 1; i < values.length; i++){
            if(max <= values[i]){
                max = values[i];
                maxIndex = i;
            }
        }

        return stringTable[maxIndex];
    }

    public String toString(){
        return "The color is rgb(" + this.red + "," + this.green + "," + this.blue + "). Hex: " + this.getHex() + ", binary: " + this.getBits();
    }
}

class Main{
    public static void main(String[] args){
        RGB24Immutable color1 = new RGB24Immutable(0, 153, 255);
        //rgb(255, 153, 204)
        RGB24Immutable color2 = new RGB24Immutable("ff99cc");
        //rgb(153, 255, 51)
        RGB24Immutable color3 = new RGB24Immutable("100110011111111100110011");
        //rgb(123, 123, 123)
        RGB24Immutable grey = new RGB24Immutable("7b7b7b");

        System.out.println(grey);
        System.out.println();
        System.out.println(color1);
        System.out.println();

        // コンストラクタ後、RGB24Immutableの状態を変更することはできません。
        // 以下の行はすべてエラーになります。
        //color1.red = 200;//ERROR: 直接アクセスはprivate属性のため不可能
        //color1.setAsBlack();//ERROR: privateメソッドなので外部から呼び出せない
        //color1.setColorsByHex("ff99cc");//ERROR: privateメソッドなので外部から呼び出せない
        System.out.println(color1);
    }
}