public class Main {
    public static void main(String[] args) {
        FairyWorld fairyWorld = new FairyWorld();
        Person jessica = new Person("Jessica", "Roller", 30, 1.65, 95, "female");

        fairyWorld.rentPet(new PlayfulCatAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulDogAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulRabbitAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulPonyAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulHamsterAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulChickenAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulGoatAssistant(), jessica);
    }
}
