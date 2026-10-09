class PlayfulDogAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Dog(
                RandomWrapper.getRanDouble(0.4, 1.2),
                RandomWrapper.getRanDouble(10.0, 30.0),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
