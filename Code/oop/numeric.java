abstract class Numeric{
    public byte getByte(){
        return (byte) this.getInteger();
    }

    public short getShort(){
        return (short) this.getInteger();
    }

    public long getLong(){
        return (long) this.getInteger();
    }

    public char getChar(){
        return (char) this.getInteger();
    }

    public abstract int getInteger();
    public abstract double getDouble();

    public String toString(){
        return this.getClass().getSimpleName() + " of int value: " + this.getInteger();
    }
}

class IntegerNumeric extends Numeric{
    private int value;

    public IntegerNumeric(int value){
        this.value = value;
    }

    public int getInteger(){
        return this.value;
    }

    public double getDouble(){
        return this.value + 0.0;
    }
}

class CharNumeric extends Numeric{
    private char c;

    public CharNumeric(char c){
        this.c = c;
    }

    public CharNumeric(int c){
        this.c = (char) c;
    }

    public int getInteger(){
        return this.c;
    }

    public double getDouble(){
        return this.getInteger() + 0.0;
    }
}

// ここから開発しましょう。

class Hexadecimal extends Numeric{
    private String hexInt;

    public Hexadecimal(String hexInt){
        this.hexInt = hexInt;
    }

    public void setHexInt(String hexInt){
        this.hexInt = hexInt;
    }

    @Override
    public int getInteger(){
        return Integer.parseInt(this.hexInt, 16);
    }

    @Override
    public double getDouble(){
        return Integer.parseInt(this.hexInt, 16) + 0.0;
    }
}

class Main{
    public static void numericPrinter(Numeric num){
        System.out.println(num);
        System.out.println("Byte: " + num.getByte());
        System.out.println("Short: " + num.getShort());
        System.out.println("Long: " + num.getLong());
        System.out.println("Char: " + num.getChar());
        System.out.println("Double: " + num.getDouble());
        System.out.println();
        
    }

    public static void main(String[] args){
        Numeric num1 = new IntegerNumeric(73);
        Numeric num2 = new IntegerNumeric(23555461);
        Numeric num3 = new CharNumeric(61);
        Numeric num4 = new Hexadecimal("1A3F");

        numericPrinter(num1);
        numericPrinter(num2);
        numericPrinter(num3);
        numericPrinter(num4);
    }
}