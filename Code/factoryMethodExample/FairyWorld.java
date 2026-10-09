import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class FairyWorld {
    private final Map<String, Supplier<PlayfulPetAssistant>> assistants = new HashMap<>();

    public void addPlayfulPetAssistant(String key, Supplier<PlayfulPetAssistant> factory) {
        this.assistants.put(key, factory);
    }

    public void rentPet(String key, Person person) {
        Supplier<PlayfulPetAssistant> factory = this.assistants.get(key);
        if (factory == null) {
            System.out.println("Unknown pet type: " + key);
            return;
        }

        this.rentPet(factory.get(), person);
    }

    public void rentPet(PlayfulPetAssistant assistant, Person person) {
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(person);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
    }
}
