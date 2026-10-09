class Mammal extends Animal {
    private double bodyTemperatureC;
    private final double avgBodyTemperatureC;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double avgBodyTemperatureC) {
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);
        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("this " + this.species + " is eating with its single lower jaw");
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.mammalInformation();
    }

    public void increaseBodyHeat(double celcius) {
        this.bodyTemperatureC += celcius;
    }

    public void decreaseBodyHeat(double celcius) {
        this.bodyTemperatureC -= celcius;
    }

    public void adjustBodyHeat() {
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public String mammalInformation() {
        return "This is a mammal with a temperature of: " + this.bodyTemperatureC;
    }
}
