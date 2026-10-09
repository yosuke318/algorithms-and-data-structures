class Rabbit extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Rabbit";
    public static final double LIFE_EXPECTANCY = 5000;
    public static final double BODY_TEMPERATURE = 38.0;

    private static final double PLAYFUL_HOURLY_COSTS = 40;
    private static final String[] LIKED_ACTIVITIES = {"eat", "hop", "explore", "pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Rabbit(double heightM, double weightKg, String biologicalSex) {
        super(Rabbit.SPECIES, heightM, weightKg, Rabbit.LIFE_EXPECTANCY, biologicalSex, Rabbit.BODY_TEMPERATURE);
    }

    @Override
    public String play() {
        return "This rabbit hops around playfully.";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The rabbit hops toward " + person.getName() + " and nibbles grass happily.";
    }

    @Override
    public String playNoise() {
        return "Squeak";
    }

    @Override
    public String getPetName() {
        return this.species;
    }

    @Override
    public double getRentalCosts() {
        return PLAYFUL_HOURLY_COSTS;
    }

    @Override
    public boolean likesActivity(String activity) {
        return Arrays.asList(LIKED_ACTIVITIES).contains(activity);
    }

    @Override
    public boolean dislikesActivity(String activity) {
        return Arrays.asList(DISLIKED_ACTIVITIES).contains(activity);
    }

    @Override
    public String doActivity(String activity) {
        if ("eat".equals(activity)) {
            this.eat();
            return "The rabbit enjoyed eating carrots.";
        } else if (this.likesActivity(activity)) {
            return "The rabbit really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The rabbit really hated the " + activity + " activity.";
        }
        return "The rabbit felt indifferent about the " + activity + " activity.";
    }
}