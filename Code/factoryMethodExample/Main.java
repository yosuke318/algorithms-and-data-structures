public class Main {
    public static void main(String[] args) {
        FairyWorld fairyWorld = new FairyWorld();
        Person jessica = new Person("Jessica", "Roller", 30, 1.65, 95, "female");
        PlayfulDogAssistant dogAssistant = new PlayfulDogAssistant();

        fairyWorld.rentPet(new PlayfulCatAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulDogAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulRabbitAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulPonyAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulHamsterAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulChickenAssistant(), jessica);
        fairyWorld.rentPet(new PlayfulGoatAssistant(), jessica);

        dogAssistant.runAssistanceTour(jessica); // 人だけ
        dogAssistant.runAssistanceTour(jessica, "all-rounder pack"); // 人 + ツアー
        dogAssistant.runAssistanceTour(jessica, "deluxe rounder pack", 3); // 人 + ツアー + ペット数
    }
}
