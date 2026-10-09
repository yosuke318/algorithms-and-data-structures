class FairyWorld {
    
    private final Map<String, Supplier<PlayfulPetAssistant>> assistant = new HashMap<>();

    public void addPlayfulPetAssistant(String key, PlayfulPetAssistant assistant){
        assistant.put(key, assistant);
    }
    public void rentPet(PlayfulPetAssistant assistant, Person person) {

        PlayfulPetAssistant assistant = assistant.get(key);
        if (assistant == null) {
            System.out.println("Unknown pet type: " + key);
            return;
        }
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(person);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
    }
}
