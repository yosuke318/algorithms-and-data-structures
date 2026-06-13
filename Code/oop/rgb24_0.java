// RGB24クラスはRGB色空間を表現するクラスです。
class RGB24{
    // 赤、緑、青の色成分を保持します。値の範囲は0から255までです。
    private int red;
    private int green;
    private int blue;

    // RGB色成分を直接指定するコンストラクタです。
    public RGB24(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // 16進数や2進数の文字列からRGB色成分を設定するコンストラクタです。
    public RGB24(String inputString){
        int l = inputString.length();

        // 文字列の長さに応じて、16進数か2進数か判断し、それぞれのメソッドを呼び出します。
        // 16進数の場合
        if(l == 6) this.setColorsByHex(inputString);
        // 2進数の場合
        else if(l == 24) this.setColorsByBin(inputString);
        // それ以外は黒に設定します。
        else this.setAsBlack();
    }

    // 16進数の文字列からRGB色成分を設定します。
    public void setColorsByHex(String hex){
        // 文字列の長さが6でなければ、黒に設定します。
        if(hex.length() != 6) this.setAsBlack();
        else{
            // 文字列を2文字ずつに分割し、それぞれを16進数として解釈して、RGB色成分に設定します。
            this.red = Integer.parseInt(hex.substring(0,2), 16);
            this.green = Integer.parseInt(hex.substring(2,4), 16);
            this.blue = Integer.parseInt(hex.substring(4,6), 16);
        }
    }

    // 2進数の文字列からRGB色成分を設定します。
    public void setColorsByBin(String bin){
        // 文字列の長さが24でなければ、黒に設定します。
        if(bin.length() != 24) this.setAsBlack();
        else{
            // 文字列を8文字ずつに分割し、それぞれを2進数として解釈して、RGB色成分に設定します。
            this.red = Integer.parseInt(bin.substring(0,8), 2);
            this.green = Integer.parseInt(bin.substring(8,16), 2);
            this.blue = Integer.parseInt(bin.substring(16), 2);
        }
    }

    // RGB色成分をすべて0に設定し、黒にします。
    public void setAsBlack(){
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    // RGB色成分を16進数の文字列で返します。
    public String getHex(){
        String hex = Integer.toHexString(this.red);
        hex+=Integer.toHexString(this.green);
        hex+=Integer.toHexString(this.blue);

        return hex;
    }

    // RGB色成分を2進数の文字列で返します。
    public String getBits(){
        return Integer.toBinaryString(Integer.parseInt(this.getHex(), 16));
    }

    // RGB色成分が最も大きい色を判定し、その色名を返します。
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

    // RGB24クラスのインスタンスの情報を文字列で返します。
    public String toString(){
        return "The color is rgb(" + this.red + "," + this.green + "," + this.blue + "). Hex: " + this.getHex() + ", binary: " + this.getBits();
    }
}

// メインクラスです。RGB24クラスの使用例を示します。
class Main{
    public static void main(String[] args){
        // RGB値で色を作成します。
        RGB24 color1 = new RGB24(0, 153, 255);
        // 16進数で色を作成します。rgb(255, 153, 204)
        RGB24 color2 = new RGB24("ff99cc");
        // 2進数で色を作成します。rgb(153, 255, 51)
        RGB24 color3 = new RGB24("100110011111111100110011");
        // 16進数で色を作成します。rgb(123, 123, 123)
        RGB24 grey = new RGB24("7b7b7b");
    }
}