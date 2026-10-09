import java.util.Arrays;

class Cat extends Mammal implements PlayfulPet {
    public static final String SPECIES = "Cat";
    public static final double LIFE_EXPECTANCY = 5500;
    public static final double BODY_TEMPERATURE = 37;

    private static final double PLAYFUL_HOURLY_COSTS = 50;
    private static final String[] LIKED_ACTIVITIES = {"eat", "nap", "groom", "drink", "crawl", "explore", "pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Cat(double heightM, double weightKg, String biologicalSex) {
        super(Cat.SPECIES, heightM, weightKg, Cat.LIFE_EXPECTANCY, biologicalSex, Cat.BODY_TEMPERATURE);
    }

    @Override
    public String toString() {
        return super.toString() + " this is a cat";
    }

    public void meow() {
        System.out.println("Meooow");
    }

    @Override
    public String getPetName() {
        return this.species;
    }

    @Override
    public String play() {
        return "This cat starts rolling on the floor, and pretends to play predator";
    }

    @Override
    public String playWithPerson(Person person) {
        String s = "The cat stares at " + person.getName();
        s += ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }

    @Override
    public String playNoise() {
        return "Meow";
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
            return "The cat enjoyed eating food.";
        } else if ("nap".equals(activity)) {
            this.sleep();
            return "The cat took a good nap.";
        } else if (this.likesActivity(activity)) {
            return "Meow. The cat really enjoyed the " + activity + " activity.";
        } else if (this.dislikesActivity(activity)) {
            return "The cat really hated the " + activity + " activity.";
        }
        return "The cat felt indiferent about the " + activity + " activity.";
    }
}
