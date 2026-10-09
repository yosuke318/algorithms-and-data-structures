import java.util.Arrays;

class Hamster extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Hamster";
    public static final double LIFE_EXPECTANCY = 2500;
    public static final double BODY_TEMPERATURE = 37.5;

    private static final double PLAYFUL_HOURLY_COSTS = 35;
    private static final String[] LIKED_ACTIVITIES = {"eat", "run", "explore", "pet", "nap"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Hamster(double heightM, double weightKg, String biologicalSex) {
        super(Hamster.SPECIES, heightM, weightKg, Hamster.LIFE_EXPECTANCY, biologicalSex, Hamster.BODY_TEMPERATURE);
    }

    @Override
    public String play() {
        return "This hamster whirls around in a tiny wheel with excitement.";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The hamster scurries over to " + person.getName() + " and snatches a sunflower seed with adorable enthusiasm.";
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
            return "The hamster enjoyed a crunchy snack.";
        } else if (this.likesActivity(activity)) {
            return "The hamster really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The hamster really hated the " + activity + " activity.";
        }
        return "The hamster felt indifferent about the " + activity + " activity.";
    }
}
