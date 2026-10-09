import java.util.Arrays;

class Dog extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Dog";
    public static final double LIFE_EXPECTANCY = 12000;
    public static final double BODY_TEMPERATURE = 38.5;

    private static final double PLAYFUL_HOURLY_COSTS = 65;
    private static final String[] LIKED_ACTIVITIES = {"walk", "run", "fetch", "play", "pet", "explore"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Dog(double heightM, double weightKg, String biologicalSex) {
        super(Dog.SPECIES, heightM, weightKg, Dog.LIFE_EXPECTANCY, biologicalSex, Dog.BODY_TEMPERATURE);
    }

    @Override
    public String toString() {
        return super.toString() + " this is a dog";
    }

    public void bark() {
        System.out.println("Woof!");
    }

    @Override
    public String getPetName() {
        return this.species;
    }

    @Override
    public String play() {
        return "This dog runs in circles and happily wags its tail";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The dog bounds toward " + person.getName() + " and starts playing fetch with joyful excitement.";
    }

    @Override
    public String playNoise() {
        return "Woof";
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
        if ("walk".equals(activity) || "run".equals(activity)) {
            return "The dog happily enjoyed the " + activity + " activity.";
        } else if (this.likesActivity(activity)) {
            return "Woof! The dog really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The dog really hated the " + activity + " activity.";
        }
        return "The dog felt indifferent about the " + activity + " activity.";
    }
}
