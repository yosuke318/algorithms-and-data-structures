// Fly(): オブジェクトは飛びます
// FlightHeight(): このオブジェクトが何メートル上空まで飛ぶことができるかを返します
// FlySpeed(): このオブジェクトが秒速何メートルで飛べるかを返します
// 特定のインターフェースを実装するオブジェクトは、インターフェースで指定されたメソッドを実装しなければならない
// という契約を結ぶこと以外、何も共通点を持つ必要がないことを覚えておいてください。
// Fly の場合、鳥、飛行機、ヘリコプター、ドローン、紙飛行機などのようなオブジェクトが考えられます。

interface Fly{

    public abstract void fly();

    public abstract double flySpeed();

    public abstract double flightHeight();
}


class Bird implements Fly{
    private double flySpeed = 100;
    private double flightHeight = 1000;

    @Override
    public void fly(){
        System.out.println("this bird is flying.");
    }

    @Override
    public double flySpeed(){
        return this.flySpeed;
    }

    @Override
    public double flightHeight(){
        return this.flightHeight;
    }
}

class AirPlane implements Fly {

    private double flySpeed = 250.0; // m/s (roughly cruising)
    private double flightHeight = 10000; // m
    @Override
    public void fly() {
        System.out.println("the airplane is flying with engine thrust.");
    }

    @Override
    public double flySpeed() {
        return this.flySpeed;
    }

    @Override
    public double flightHeight() {
        return this.flightHeight;
    }
}


class PaperAirPlane implements Fly {
    private double flySpeed = 6.5;
    private double FlightHeight = 3.0;

    @Override
    public void fly() {
        System.out.println("the paper airplane glides through the air.");
    }

    @Override
    public double flySpeed() {
        return this.flySpeed;
    }

    @Override
    public double flightHeight() {
        return this.flightHeight;
    }
}