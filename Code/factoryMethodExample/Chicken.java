import java.util.Arrays;

class Chicken extends Animal implements PlayfulPet {
    public static final String SPECIES = "Chicken";
    public static final double LIFE_EXPECTANCY = 4500;
    public static final double BODY_TEMPERATURE = 41.0;

    private static final double PLAYFUL_HOURLY_COSTS = 30;
    private static final String[] LIKED_ACTIVITIES = {"eat", "explore", "run", "pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Chicken(double heightM, double weightKg, String biologicalSex) {
        super(Chicken.SPECIES, heightM, weightKg, Chicken.LIFE_EXPECTANCY, biologicalSex);
    }

    @Override
    public String play() {
        return "This chicken pecks around with a proud little strut.";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The chicken follows " + person.getName() + " around and pecks at the ground playfully.";
    }

    @Override
    public String playNoise() {
        return "Cluck";
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
            return "The chicken enjoyed a snack.";
        } else if (this.likesActivity(activity)) {
            return "The chicken really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The chicken really hated the " + activity + " activity.";
        }
        return "The chicken felt indifferent about the " + activity + " activity.";
    }
}
