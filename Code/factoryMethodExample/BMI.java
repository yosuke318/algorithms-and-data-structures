class BMI {
    private final double heightM;
    private final double weightKg;

    public BMI(double heightM, double weightKg) {
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getWeightKg() {
        return this.weightKg;
    }

    public double getValue() {
        return this.weightKg / (this.heightM * this.heightM);
    }

    @Override
    public String toString() {
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}
