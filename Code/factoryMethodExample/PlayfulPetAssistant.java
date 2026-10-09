import java.util.Arrays;
import java.util.List;
import java.util.Random;

abstract class PlayfulPetAssistant {
    protected static final double DEFAULT_RENT_TIME = 1.0;
    protected static final String DEFAULT_TOUR = "all-rounder pack";

    protected Person currentPerson;
    protected double currentRentTime = PlayfulPetAssistant.DEFAULT_RENT_TIME;
    protected static String[] availableActivities = {"eat", "walk", "drink", "nap", "pet", "run", "explore"};
    protected static String[] availableTours = {"all-rounder pack", "deluxe rounder pack"};

    public String[] getActivities() {
        return this.availableActivities;
    }

    public String[] getAvailableTours() {
        return this.availableTours;
    }

    public boolean isValidTour(String tour) {
        return Arrays.asList(this.getAvailableTours()).contains(tour);
    }

    protected String getRandomActivity() {
        List<String> activities = Arrays.asList(this.getActivities());
        int ran = new Random().nextInt(activities.size());
        return activities.get(ran);
    }

    public void setPerson(Person person) {
        this.currentPerson = person;
    }

    public void setHours(double hours) {
        this.currentRentTime = hours;
    }

    public double getCurrentRentTime() {
        return this.currentRentTime;
    }

    public void reset() {
        this.currentPerson = null;
        this.currentRentTime = this.DEFAULT_RENT_TIME;
    }

    public double runAssistanceTour(Person person) {
        return this.runAssistanceTour(person, this.DEFAULT_TOUR);
    }

    public double runAssistanceTour(Person person, String tour) {
        if (!this.isValidTour(tour)) {
            System.out.println("The tour guide does not accept the " + tour + " tour.");
        }

        PlayfulPet playfulPet = this.createPlayfulPet();

        System.out.println("");
        System.out.println("Booting up... Playful Pet Assistance robot at your service.");
        System.out.println("Printing information about the Person to service..." + person);
        System.out.println("");
        System.out.println("Printing information about the Playful Pet - " + playfulPet.getPetName() + " to service..." + playfulPet);

        if ("all-rounder pack".equals(tour) || "deluxe rounder pack".equals(tour)) {
            int count = "all-rounder pack".equals(tour) ? 1 : 3;
            this.genericRounderTour(count, person, playfulPet);
        } else {
            System.out.println("The tour assistant robot for " + playfulPet.getPetName() + " and " + person.getName() + " did nothing.");
        }

        double rentalCosts = playfulPet.getRentalCosts() * this.getCurrentRentTime();
        this.reset();
        return rentalCosts;
    }

    private void genericRounderTour(int activityCount, Person person, PlayfulPet pet) {
        String newLine = System.lineSeparator();
        System.out.println(newLine + "Now starting the generic rounder tour with " + activityCount + " activity(s)");
        System.out.println(person.getName() + " greets " + pet.getPetName() + newLine);
        System.out.println(pet.play() + newLine);
        System.out.println(pet.playNoise() + newLine);
        System.out.println(pet.playWithPerson(person) + newLine);

        for (int i = 0; i < activityCount; i++) {
            String activity = this.getRandomActivity();
            System.out.println(pet.getPetName() + " will now " + activity);
            System.out.println("--------");
            System.out.println(pet.doActivity(activity));
            System.out.println("--------" + newLine);
        }
    }

    public abstract PlayfulPet createPlayfulPet();
}
