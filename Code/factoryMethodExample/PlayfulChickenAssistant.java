class PlayfulChickenAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Chicken(
                RandomWrapper.getRanDouble(0.2, 0.6),
                RandomWrapper.getRanDouble(1.0, 3.5),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
