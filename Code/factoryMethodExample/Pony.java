import java.util.Arrays;

class Pony extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Pony";
    public static final double LIFE_EXPECTANCY = 8000;
    public static final double BODY_TEMPERATURE = 38.0;

    private static final double PLAYFUL_HOURLY_COSTS = 70;
    private static final String[] LIKED_ACTIVITIES = {"eat", "run", "groom", "pet", "explore"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Pony(double heightM, double weightKg, String biologicalSex) {
        super(Pony.SPECIES, heightM, weightKg, Pony.LIFE_EXPECTANCY, biologicalSex, Pony.BODY_TEMPERATURE);
    }

    @Override
    public String play() {
        return "This pony trots around playfully.";
    }

    @Override
    public String playWithPerson(Person person) {
        return "The pony nuzzles " + person.getName() + " and trots beside them in a cheerful little parade.";
    }

    @Override
    public String playNoise() {
        return "Neigh";
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
            return "The pony enjoyed eating hay.";
        } else if (this.likesActivity(activity)) {
            return "The pony really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The pony really hated the " + activity + " activity.";
        }
        return "The pony felt indifferent about the " + activity + " activity.";
    }
}
