import java.util.Arrays;

class Goat extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Goat";
    public static final double LIFE_EXPECTANCY = 9000;
    public static final double BODY_TEMPERATURE = 39.0;

    private static final double PLAYFUL_HOURLY_COSTS = 75;
    private static final String[] LIKED_ACTIVITIES = {"eat", "run", "explore", "pet", "climb"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Goat(double heightM, double weightKg, String biologicalSex) {
        super(Goat.SPECIES, heightM, weightKg, Goat.LIFE_EXPECTANCY, biologicalSex, Goat.BODY_TEMPERATURE);
    }

    @Override
    public String play() {
        return "This goat leaps around the field with playful energy.";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The goat trots over to " + person.getName() + " and pushes its head into a friendly nuzzle.";
    }

    @Override
    public String playNoise() {
        return "Bleat";
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
            return "The goat enjoyed eating grass.";
        } else if (this.likesActivity(activity)) {
            return "The goat really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The goat really hated the " + activity + " activity.";
        }
        return "The goat felt indifferent about the " + activity + " activity.";
    }
}
